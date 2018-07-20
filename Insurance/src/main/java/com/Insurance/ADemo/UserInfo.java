package com.Insurance.ADemo;
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.Table;  
import javax.validation.constraints.NotNull;  
  
@Entity  
@Table(name="t_user")  
public class UserInfo {  
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private int id;  
    @NotNull  
    private String name;  
      
    private String password;  
      
    private String salt;  
      
    private String role;  
  
    public int getId() {  
        return id;  
    }  
  
    public void setId(int id) {  
        this.id = id;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    public String getSalt() {  
        return salt;  
    }  
  
    public void setSalt(String salt) {  
        this.salt = salt;  
    }  
  
    public String getRole() {  
        return role;  
    }  
  
    public void setRole(String role) {  
        this.role = role;  
    }  
}  
