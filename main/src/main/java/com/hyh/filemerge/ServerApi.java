package com.hyh.filemerge;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ServerApi {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void request(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder.build();

        RequestBody requestBody = RequestBody.create(JSON, "");
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .post(requestBody)
                .build();
        client.newCall(request);
    }
}
