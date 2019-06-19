package com.ai.mine.crystal.service.alisr.impl;

import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.crystal.common.DateUtil;
import com.ai.mine.crystal.common.HttpDownloaderUtil;
import com.ai.mine.crystal.dao.mapper.TKedaRecordMapper;
import com.ai.mine.crystal.dao.model.TKedaRecord;
import com.ai.mine.crystal.enums.JobStatusEnum;
import com.ai.mine.crystal.service.alisr.interfaces.IDownloadVideoSV;
import com.ai.mine.crystal.service.common.KedaRequestBody;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class DownloadVideoSVImpl implements IDownloadVideoSV {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String API_GET_VIDEO_PATH = "getDownloadVideoPath";

    @Autowired
    private KedaMessageProcessor kedaMessageProcessor;

    @Autowired
    private TKedaRecordMapper kedaRecordMapper;

    @Override
    public boolean downloadVideoById(String recordId) throws BusinessException {
        logger.info("开始下载案件笔录视频文件，案件笔录ID：{}", recordId);
        Long start = System.currentTimeMillis();
        //Done: 调用接口获取笔录下载地址，并执行下载，规范命名后存储到指定目录。
        //1. 封装接口调用参数
        Map<String, String> getParam = new HashMap<>();
        getParam.put("id",recordId);
        String requestURI = kedaMessageProcessor.parseRequestURI(API_GET_VIDEO_PATH, getParam);
        KedaRequestBody requestBody = new KedaRequestBody(API_GET_VIDEO_PATH, "id");

        //2. 调用科达接口，获取文件下载地址，并更新到笔录表中。
        threadSleep(1000);
        String videoUrl = kedaMessageProcessor.getVideoUrlByAPI(requestURI, requestBody);
        if (StringUtils.isNotBlank(videoUrl)) {
            String fileName = this.saveRecordVideoUrl(recordId, videoUrl);
            if (StringUtils.isNotBlank(fileName)) {
                logger.info("获得视频下载地址完成，接口调用耗时：" + String.valueOf(System.currentTimeMillis() - start) + " ms");
                //3. 执行下载操作，文件下载完成后存储到指定目录
                HttpDownloaderUtil.downloadFile(videoUrl, fileName);

                threadSleep(2000);
                logger.info("视频文件下载到本地，总耗时：" + String.valueOf(System.currentTimeMillis() - start) + " ms");

                //4. 存储完成后，记录笔录视频下载结果
                if (!this.completeRecordVideoDownload(recordId)) {
                    logger.error("修改笔录视频下载状态出现错误，请稍后再试！");
                }

                threadSleep(1000);
                logger.info("视频保存到云存储完成，总耗时：" + String.valueOf(System.currentTimeMillis() - start) + " ms");
                logger.info("视频下载完成，案件笔录ID：{}", recordId);
            } else {
                logger.error("视频地址信息保存到数据库出错：videoUrl = " + videoUrl);
            }
        }

        return false;
    }

    @Override
    public String saveRecordVideoUrl(String recordId, String videoUrl) throws BusinessException {
        if (StringUtils.isBlank(recordId) || StringUtils.isBlank(videoUrl)) {
            throw new BusinessException("笔录ID和笔录视频下载地址不能为空","300106");
        }
        TKedaRecord record = kedaRecordMapper.selectByPrimaryKey(recordId);
        if (record != null) {
            record.setVideoUrl(videoUrl);
            record.setJobStatus(JobStatusEnum.processing.toString());
            record.setJobTime(new Date());
            int res = kedaRecordMapper.updateByPrimaryKeySelective(record);
            if (res == 1) {
                String roomName = StringUtils.isNotBlank(record.getRemoteRoom()) ? record.getRemoteRoom() : record.getLocalRoom();
                String extension = getExtensionFromUrl(videoUrl);
                //文件存储路径示例: /yjcloud/001/20190421/案号_询问室202_1_审讯人.wav
                return "/yjcloud/001" + "/" + DateUtil.getCurrentDate(DateUtil.YYYYMMDD) +
                        "/" + record.getCaseId() +
                        "_" + roomName +
                        "_" + recordId +
                        "_" + record.getAsker() +
                        extension;
            }
        } else {
            logger.warn("笔录ID在数据库中查询不到记录！recordId = " + recordId);
        }
        return "";
    }

    @Override
    public boolean completeRecordVideoDownload(String recordId) throws BusinessException {
        TKedaRecord record = kedaRecordMapper.selectByPrimaryKey(recordId);
        if (record != null) {
            record.setJobTime(new Date());
            record.setJobStatus(JobStatusEnum.complete.toString());

            int res = kedaRecordMapper.updateByPrimaryKeySelective(record);
            return res == 1;
        }
        return false;
    }

    private void threadSleep(int milliscond) {
        try {
            Thread.sleep(milliscond);
        } catch (InterruptedException e) {
            logger.error("threadSleep Exception: \n",e);
        }
    }

    private String getExtensionFromUrl(String videoUrl) {
        //取完整URL链接中最后一个"/"之后的字符串
        String urlFileName = videoUrl.substring(videoUrl.lastIndexOf(47));
        //取文件名后缀
        int pos = urlFileName.lastIndexOf(46);
        if (pos > 0) {
            String ext = videoUrl.substring(pos);
            if (ext.indexOf(63) > 0) {
                //去掉"?"之后的内容
                ext = ext.substring(0, ext.indexOf(63)-1);
            }
            return ext;
        } else {
            return "";
        }
    }
}
