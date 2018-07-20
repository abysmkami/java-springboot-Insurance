package com.Insurance.Filter;
import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.Insurance.Utils.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;    
  
public class HTTPBearerAuthorizeAttribute implements Filter{  
    @Autowired  
    private Audience audienceEntity;  
  
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
        // TODO Auto-generated method stub  
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,  
                filterConfig.getServletContext());  
          
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  
            throws IOException, ServletException {  
        // TODO Auto-generated method stub  
          
        ResultMsg resultMsg;  
        HttpServletRequest httpRequest = (HttpServletRequest)request;  
        String auth = httpRequest.getHeader("Authorization");  
        if ((auth != null) && (auth.length() > 7))  
        {  
            String HeadStr = auth.substring(0, 6).toLowerCase();  
            if (HeadStr.compareTo("bearer") == 0)  
            {  
                  
                auth = auth.substring(7, auth.length());   
                if (JwtHelper.parseJWT(auth, audienceEntity.getBase64Secret()) != null)  
                {  
                    chain.doFilter(request, response);  
                    return;  
                }  
            }  
        }  
          
        HttpServletResponse httpResponse = (HttpServletResponse) response;  
        httpResponse.setCharacterEncoding("UTF-8");    
        httpResponse.setContentType("application/json; charset=utf-8");   
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  
  
        ObjectMapper mapper = new ObjectMapper();  
          
        resultMsg = new ResultMsg(ResultStatusCode.INVALID_TOKEN.getErrcode(), ResultStatusCode.INVALID_TOKEN.getErrmsg(), null);  
        httpResponse.getWriter().write(mapper.writeValueAsString(resultMsg));  
        return;  
    }  
  
    @Override  
    public void destroy() {  
        // TODO Auto-generated method stub  
          
    }  
} 
