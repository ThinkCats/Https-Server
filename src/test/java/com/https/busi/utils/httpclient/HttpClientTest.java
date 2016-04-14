package com.https.busi.utils.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wanglei25 on 2016/4/14.
 */
public class HttpClientTest {

    @Test
    public void testHttpClient(){
        HttpClient httpClient = new HttpsClientUtil().createSSLClient();
        HttpGet get = new HttpGet("https://localhost:8447/doLogic/todo");
        try {
            HttpResponse response = httpClient.execute(get);
            System.out.println("---------------------------------------------------------------------- Result :");
            String result = EntityUtils.toString(response.getEntity());
            System.out.println("my result: "+result);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            get.releaseConnection();
        }
    }
}
