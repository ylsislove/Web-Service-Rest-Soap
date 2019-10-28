package com.yaindream.email.webservice.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 发送邮件的工具类
 *
 * @author Apple_Coco
 * @version V1.0 2019/10/26 11:58
 */
public class EmailUtil {

    public static boolean validateEmailAddress(String url) {
        if (null==url || "".equals(url)){
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(url);
        return m.matches();
    }

    public static String sendEmail(String url, String payload) {
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4Fmw8QUAxEBHWHQNW3Yh", "FxWk3KeZJgCWbQSMaSRAHXUhRCLYeM");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("admin@email.yaindream.com");
            request.setFromAlias("Yain");
            request.setAddressType(1);
            request.setTagName("SA");
            request.setReplyToAddress(true);
            request.setToAddress(url);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
//            request.setToAddress("576880405@qq.com,1767508581@qq.com,yinzhicheng@cug.edu.cn,2381679817@qq.com," +
//                    "476206397@qq.com,1260010777@qq.com,1758322248@qq.com");
            request.setSubject("小宇邮件服务");
            request.setHtmlBody(payload);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);

        } catch (ClientException e) {
            //捕获错误异常码
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
            return "ErrCode : " + e.getErrCode();
        }
//        return "邮件发送成功";
        return "Y";
    }

}
