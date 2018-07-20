package com.Insurance;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Insurance.Filter.Audience;
import com.Insurance.Filter.HTTPBasicAuthorizeAttribute;
import com.Insurance.Filter.HTTPBearerAuthorizeAttribute;
@Controller
@SpringBootApplication
@EnableConfigurationProperties(Audience.class) 
//@Configuration
public class InsuranceApplication {

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean  
    public FilterRegistrationBean  basicFilterRegistrationBean() {  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        HTTPBasicAuthorizeAttribute httpBasicFilter = new HTTPBasicAuthorizeAttribute();  
        registrationBean.setFilter(httpBasicFilter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/user/getuser");  
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    }  
      
    @Bean  
    public FilterRegistrationBean jwtFilterRegistrationBean(){  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        HTTPBearerAuthorizeAttribute httpBearerFilter = new HTTPBearerAuthorizeAttribute();  
        registrationBean.setFilter(httpBearerFilter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/user/getusers");  
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    }  
    
public static void main(String[] args)
{
	SpringApplication.run(InsuranceApplication.class, args);
}

}
