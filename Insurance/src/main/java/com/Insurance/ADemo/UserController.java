package com.Insurance.ADemo;

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.data.jpa.repository.Modifying;  
import org.springframework.web.bind.annotation.RequestBody;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;

import com.Insurance.Filter.ResultMsg;
import com.Insurance.Filter.ResultStatusCode;  
  
  
@RestController  
@RequestMapping("user")  
public class UserController {  
    @Autowired  
    private UserInfoRepository userRepositoy;  
      
    @RequestMapping("getuser")  
    public Object getUser(int id)  
    {  
        UserInfo userEntity = userRepositoy.findUserInfoById(id);  
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);  
        return resultMsg;  
    }  
      
    @RequestMapping("getusers")  
    public Object getUsers(String role)  
    {  
        List<UserInfo> userEntities = userRepositoy.findUserInfoByRole(role);  
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntities);  
        return resultMsg;  
    }  
      
    @Modifying  
    @RequestMapping("adduser")  
    public Object addUser(@RequestBody UserInfo userEntity)  
    {  
        userRepositoy.save(userEntity);  
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);  
        return resultMsg;  
    }  
      
    @Modifying  
    @RequestMapping("updateuser")  
    public Object updateUser(@RequestBody UserInfo userEntity)  
    {  
        UserInfo user = userRepositoy.findUserInfoById(userEntity.getId());  
        if (user != null)  
        {  
            user.setName(userEntity.getName());  
            userRepositoy.save(user);  
        }  
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), null);  
        return resultMsg;  
    }  
      
    @Modifying  
    @RequestMapping("deleteuser")  
    public Object deleteUser(int id)  
    {  
        userRepositoy.delete(id);  
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), null);  
        return resultMsg;  
    }  
}  
