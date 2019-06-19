package com.ai.mine.crystal.common;

import com.ai.mine.common.exception.BusinessException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpDownloaderUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpDownloaderUtil.class);

    /**
     * 从服务器以HTTP协议下载文件，并重命名为指定文件名
     * @param sourceUrl 文件下载URL
     * @param fileName 重命名的文件名
     * @throws BusinessException 业务异常
     */
    public static void downloadFile(String sourceUrl, String fileName) throws BusinessException {
        URL httpUrl;
        try {
            httpUrl = new URL(sourceUrl);
            HttpURLConnection httpConn = (HttpURLConnection) httpUrl.openConnection();
            httpConn.setDoOutput(true); // 使用 URL 连接进行输出
            httpConn.setDoInput(true); // 使用 URL 连接进行输入
            httpConn.setUseCaches(false); // 忽略缓存
            httpConn.setRequestMethod("GET"); // 设置URL请求方法
            httpConn.setRequestProperty("Content-Type", "application/octet-stream");
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpConn.setRequestProperty("Charset", "UTF-8");

            int contentLength = httpConn.getContentLength();
            logger.info("contentLength is :" + contentLength);
            String contentDisposition = URLDecoder.decode(httpConn.getHeaderField("content-Disposition"), "UTF-8");
            fileName = correctFileExtension(contentDisposition, fileName);
            byte[] file = input2byte(httpConn.getInputStream());
            writeBytesToFile(file, fileName);
        } catch (IOException e) {
            logger.error("",e);
            throw new BusinessException("download file throw Exception! please retry later!","900001");
        }

    }

    private static byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 1024)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        return swapStream.toByteArray();
    }

    private static File writeBytesToFile(byte[] b, String outputFile) {
        File file = null;
        FileOutputStream os = null;

        try {
            file = new File(outputFile);
            os = new FileOutputStream(file);
            os.write(b);
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            try {
                if(os != null) {
                    os.close();
                }
            } catch (IOException var12) {
                var12.printStackTrace();
            }

        }

        return file;
    }

    /**
     * 根据服务器返回文件内容描述，修正文件后缀名
     * @param contentDisposition 服务器返回描述信息
     * @param fileName 重命名的文件名
     * @return 修正为正确后缀的文件名
     */
    private static String correctFileExtension(String contentDisposition, String fileName) {
        Pattern pattern = Pattern.compile(".*fileName=(.*)");
        Matcher matcher = pattern.matcher(contentDisposition);
        String originFilename = matcher.group(1);
        String originExtension = FilenameUtils.getExtension(originFilename);
        String extension = FilenameUtils.getExtension(fileName);
        if (StringUtils.isNotBlank(originExtension) && !originExtension.equalsIgnoreCase(extension)) {
            fileName = FilenameUtils.removeExtension(fileName) + originExtension;
        }
        return fileName;
    }

}
