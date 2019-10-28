package com.yaindream.email.webservice.soap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * web service 配置类
 *
 * @author Apple_Coco
 * @version V1.0 2019/10/26 11:33
 */
@Configuration
public class WebServicesConfiguration extends WsConfigurerAdapter {

    /**
     * 设置访问路径：@Bean("my")，案例：http://localhost:8080/soap/email/service.wsdl
     * 其中，wsdl是MessageDispatcherServlet中规定的结尾
     * @param emailXsdSchema @Autowired自动装配下方的emailXsdSchema
     * @return Wsdl11Definition对象
     */
    @Bean("service")
    @Autowired
    public Wsdl11Definition userWsdl11Definition(XsdSchema emailXsdSchema){
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();

        defaultWsdl11Definition.setPortTypeName("EmailServicePort");
        //设置访问路径：@Bean("service")，案例：http://localhost:8080/soap/email/service.wsdl
        defaultWsdl11Definition.setLocationUri("/email");
        //user.xsd中的targetNamespace属性
        defaultWsdl11Definition.setTargetNamespace("http://yaindream.com/schemas");
        defaultWsdl11Definition.setSchema(emailXsdSchema);

        return defaultWsdl11Definition;
    }

    /**
     * 注册email.xsd（Schema文件）对应的java对象
     */
    @Bean
    public XsdSchema userXsdSchema(){
        return new SimpleXsdSchema(new ClassPathResource("email.xsd"));
    }

}
