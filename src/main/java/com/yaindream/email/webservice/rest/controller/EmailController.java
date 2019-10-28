package com.yaindream.email.webservice.rest.controller;

import com.alibaba.fastjson.JSON;
import com.yaindream.email.webservice.rest.domain.EmailBatch;
import com.yaindream.email.webservice.utils.EmailUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Apple_Coco
 * @version V1.0 2019/10/26 16:06
 */
@RestController
@RequestMapping("/rest/email")
public class EmailController {

    @GetMapping("/validate")
    public String validateEmailAddress(@RequestParam(value = "url", defaultValue = "") String url) {

        if ("".equals(url)) {
            return "请提供需要验证的邮箱地址，如/rest/email/validate?url=xxx";
        }

        // 进行邮件地址验证
        if (!EmailUtil.validateEmailAddress(url)) {
            return "N";
        }

        return "Y";
    }

    @GetMapping("/get")
    public String sendEmail(@RequestParam(value = "url", defaultValue = "") String url,
                            @RequestParam(value = "payload", defaultValue = "") String payload) {

        System.out.println(url);
        System.out.println(payload);
        System.out.println("Rest get 方法被调用");

        if ("".equals(url) || "".equals(payload)) {
            return "请提供邮箱的url以及消息payload，如/rest/email/get?url=xxx?payload=xxx";
        }

        // 进行邮件地址验证
        if (!EmailUtil.validateEmailAddress(url)) {
            return "请输入正确的邮箱地址";
        }

        // 发送单个邮件
        return EmailUtil.sendEmail(url, payload);
    }

    @PostMapping("/post")
    public String sendEmailBatch(@RequestBody EmailBatch emailBatch) {

        System.out.println(emailBatch);
        System.out.println("Rest post 方法被调用");

        String payload = emailBatch.getPayload();
        List<String> urls = emailBatch.getUrls();
        Map<String, String> map = new HashMap<>(emailBatch.getUrls().size());

        if (urls.size() == 0 || "".equals(payload)) {
            map.put("", "N");
            return JSON.toJSONString(map);
        }

        for (String url : emailBatch.getUrls()) {
            if ("".equals(url)) {
                map.put(" ", "N");
                continue;
            }

            // 进行邮件地址验证
            if (!EmailUtil.validateEmailAddress(url)) {
                map.put(url, "N");
                continue;
            }

            // 发送邮件
            if ("Y".equals(EmailUtil.sendEmail(url, payload))) {
                map.put(url, "Y");
            } else {
                map.put(url, "N");
            }
        }

        return JSON.toJSONString(map);
    }

}
