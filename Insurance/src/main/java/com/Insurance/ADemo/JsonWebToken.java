package com.Insurance.ADemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;

import com.Insurance.Filter.Audience;
import com.Insurance.Filter.ResultMsg;
import com.Insurance.Filter.ResultStatusCode;
import com.Insurance.Utils.JwtHelper;
import com.Insurance.Utils.SecurityHelper;  
  

  
@RestController  
public class JsonWebToken {  
    @Autowired  
    private UserInfoRepository userRepositoy;  
      
    @Autowired  
    private Audience audienceEntity;  
    @Autowired
	private RedisTemplate<String, String> redisTemplate;
      
    @RequestMapping("oauth/token")  
    public Object getAccessToken(@RequestBody LoginPara loginPara)  
    {  
        ResultMsg resultMsg;  
        try  
        {  
            if(loginPara.getClientId() == null   
                     || (loginPara.getClientId().compareTo(audienceEntity.getClientId()) != 0))  
            {  
                resultMsg = new ResultMsg(ResultStatusCode.INVALID_CLIENTID.getErrcode(),   
                        ResultStatusCode.INVALID_CLIENTID.getErrmsg(), null);  
                return resultMsg;  
            }  
              
            //验证码校验 
            String captchaCode = loginPara.getCaptchaCode();
            try {
            	if (captchaCode == null)
            	{
            		throw new Exception();
            	}
            	String captchaValue =  redisTemplate.opsForValue().get(captchaCode);
            	if (captchaValue == null)
            	{
            		throw new Exception();
            	}
            	redisTemplate.delete(captchaCode);
            	
            	if (captchaValue.compareTo(loginPara.getCaptchaValue()) != 0)
            	{
            		throw new Exception();
            	}
            } catch (Exception e) {
            	resultMsg = new ResultMsg(ResultStatusCode.INVALID_CAPTCHA.getErrcode(), 
            			ResultStatusCode.INVALID_CAPTCHA.getErrmsg(), null);
            	return resultMsg;
            }
              
              
            //验证用户名密码  
            UserInfo user = userRepositoy.findUserInfoByName(loginPara.getUserName());  
            if (user == null)  
            {  
                resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(),  
                        ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);  
                return resultMsg;  
            }  
            else  
            {       
            	String md5Password = SecurityHelper.getMD5(loginPara.getPassword()+user.getSalt());  
                  
                if (md5Password.compareTo(user.getPassword()) != 0)  
                {  
                    resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(),  
                            ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);  
                    return resultMsg;  
                }  
            }  
              
            //拼装accessToken  
            String accessToken = JwtHelper.createJWT(loginPara.getUserName(), String.valueOf(user.getName()),  
                    user.getRole(), audienceEntity.getClientId(), audienceEntity.getName(),  
                    audienceEntity.getExpiresSecond() * 1000, audienceEntity.getBase64Secret());  
              
            //返回accessToken  
            AccessToken accessTokenEntity = new AccessToken();  
            accessTokenEntity.setAccess_token(accessToken);  
            accessTokenEntity.setExpires_in(audienceEntity.getExpiresSecond());  
            accessTokenEntity.setToken_type("bearer");  
            resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(),   
                    ResultStatusCode.OK.getErrmsg(), accessTokenEntity);  
            return resultMsg;  
              
        }  
        catch(Exception ex)  
        {  
            resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrcode(),   
                    ResultStatusCode.SYSTEM_ERR.getErrmsg(), null);  
            return resultMsg;  
        }  
    }  
    
}  