package com.ai.mine.crystal.common;

import com.ai.mine.common.utils.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestUtil extends HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static JSONObject postJson(String uri, Object paramObj) throws Exception {
        Assert.notNull(uri, "请求地址不能为空！");
        HttpURLConnection connection = null;
        JSONObject result = null;

        try {
            URL url = new URL(uri);
            if (uri.startsWith("https")) {
                connection = (HttpsURLConnection)url.openConnection();
            } else {
                connection = (HttpURLConnection)url.openConnection();
            }

            connection.setRequestMethod(Method.POST.name());
            connection.setConnectTimeout(15 * 1000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.connect();
            String responseStr;
            responseStr = JSONObject.toJSONString(paramObj);
            if (!StringUtils.isEmpty(responseStr)) {
                DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                dos.write(responseStr.getBytes("UTF-8"));
                dos.flush();
                dos.close();
            }

            if (connection.getResponseCode() == 200) {
                responseStr = readStream(connection.getInputStream());
                if (logger.isDebugEnabled()) {
                    logger.debug("connection.getContentType() :" + connection.getContentType());
                }

                if (logger.isDebugEnabled()) {
                    logger.debug(responseStr);
                }

                result = JSONObject.parseObject(responseStr);
            }
        } catch (Exception e) {
            logger.error("发送Http请求出现错误，uri=" + uri + ", paramObject=" + JSONObject.toJSONString(paramObj), e);
            throw e;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }

        return result;
    }

    private static String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }

}
