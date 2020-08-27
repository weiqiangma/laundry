package com.mawkun.laundry.base.data;

import com.mawkun.laundry.base.entity.Admin;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
public class UserSession {

    private Long id;

    private Long shopId;

    private String userName;

    private String password;

    private Integer level;

    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserSession(String token, Admin admin) {
        this.token = token;
        this.id = admin.getId();
        this.shopId = admin.getShopId();
        this.userName = admin.getUserName();
        this.password = admin.getPassword();
        this.level = admin.getLevel();
    }

    public UserSession(){}
}
