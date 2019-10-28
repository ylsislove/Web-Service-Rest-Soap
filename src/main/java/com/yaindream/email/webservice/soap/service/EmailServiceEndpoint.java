package com.yaindream.email.webservice.soap.service;

import com.yaindream.email.webservice.soap.xml.email.EmailRequest;
import com.yaindream.email.webservice.soap.xml.email.EmailResponse;
import com.yaindream.email.webservice.utils.EmailUtil;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * soap web service的访问端口
 *
 * @author Apple_Coco
 * @version V1.0 2019/10/26 11:40
 */
@Endpoint
public class EmailServiceEndpoint {

    @PayloadRoot(namespace = "http://yaindream.com/schemas", localPart = "EmailRequest")
    @ResponsePayload
    public EmailResponse emailService(@RequestPayload EmailRequest emailRequest){

        String url = emailRequest.getUrl();
        String payload = emailRequest.getPayload();

        EmailResponse emailResponse = new EmailResponse();

        if ("".equals(url) || "".equals(payload)) {
            // 请提供邮箱的url以及消息content，如/email?url=xxx?content=xxx
            emailResponse.setResult("请提供邮箱的url以及消息content，如/email?url=xxx?payload=xxx");
            return emailResponse;
        }

        // 进行邮件地址验证
        if (!EmailUtil.validateEmailAddress(url)) {
            // 请输入正确的邮箱地址
            emailResponse.setResult("请输入正确的邮箱地址");
            return emailResponse;
        }

        // 发送单个邮件
        emailResponse.setResult(EmailUtil.sendEmail(url, payload));
        return emailResponse;
    }

}
