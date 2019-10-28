package com.yaindream.email.webservice.rest.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author Apple_Coco
 * @version V1.0 2019/10/26 16:10
 */
public class EmailBatch {

    private List<String> urls = new ArrayList<>();
    private String payload;

    public EmailBatch() {
    }

    public EmailBatch(List<String> urls, String payload) {
        this.urls = urls;
        this.payload = payload;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "EmailBatch{" +
                "urls=" + urls +
                ", payload='" + payload + '\'' +
                '}';
    }
}
