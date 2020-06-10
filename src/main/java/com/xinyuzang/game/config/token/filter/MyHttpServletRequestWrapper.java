package com.xinyuzang.game.config.token.filter;

import com.google.gson.Gson;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoutao
 * @date 2020/6/3
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    public MyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        body = this.getBody(request);
    }

    /**
     * 添加字符串到请求头里面
     * @param field
     * @param str
     */
    public void addString(String field, String str) {

        Gson gson = new Gson();
        Map map = gson.fromJson(new String(body, Charset.forName("UTF-8")), HashMap.class);
        map.put(field, str);
        body = gson.toJson(map, HashMap.class).getBytes(Charset.forName("UTF-8"));
    }

    /**
     * 重写ServletInputStream的getInputStream方法
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                   return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    /**
     * 重写重写ServletInputStream的Reader方法
     * @return
     * @throws IOException
     */
    @Override
    public BufferedReader getReader() throws IOException {

        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    /**
     * 从request中读取流
     * @param request
     * @return
     */
    private byte[] getBody(HttpServletRequest request) {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br =  new BufferedReader(new InputStreamReader(request.getInputStream()))){
            char[] bodyCharBuffer = new char[1024];
            int len;
            while ((len = br.read(bodyCharBuffer)) != -1) {
                sb.append(new String(bodyCharBuffer, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString().getBytes(Charset.forName("UTF-8"));
    }
}
