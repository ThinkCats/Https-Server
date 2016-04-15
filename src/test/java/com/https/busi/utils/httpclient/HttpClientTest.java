package com.https.busi.utils.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by wanglei25 on 2016/4/14.
 */
public class HttpClientTest {


    @Test
    public void testMain(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter a number(0: Get Https 1:Get Http):");
        int s = scanner.nextInt();
        HttpClient httpClient = null;
        switch (s){
            case 0:
                httpClient = new HttpsClientUtil().createSSLClient();
                break;
            case 1:
                httpClient = HttpClients.createDefault();
                break;
            default:
                System.out.println("Wrong Number !!");
        }
        if (httpClient != null){
            testHttpsClient(httpClient);
        }
    }

    public void testHttpsClient(HttpClient httpClient){
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
