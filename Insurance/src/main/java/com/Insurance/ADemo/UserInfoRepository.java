package com.Insurance.ADemo;

import java.util.List;  

import org.springframework.data.jpa.repository.Query;  
import org.springframework.data.repository.CrudRepository;  
  
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer>{  
    UserInfo findUserInfoById(int id);  
    List<UserInfo> findUserInfoByRole(String role);  
    UserInfo findUserInfoByName(String name);
      
    @Query(value = "select * from t_user limit ?1", nativeQuery =true)  
    List<UserInfo> findAllUsersByCount(int count);  
}  
