package com.mawkun.laundry.base.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mawkun.laundry.base.common.constant.Constant;
import com.mawkun.laundry.base.entity.Admin;
import com.mawkun.laundry.base.entity.User;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSession {

    private Long id;

    private Long shopId;

    private String userName;

    private String realName;

    private String password;

    private String mobile;

    private Double sumOfMoney;

    private String address;

    private Integer integral;

    private Integer kind;

    private Integer level;

    private String token;

    public UserSession(){}

    public UserSession(String token, Admin admin) {
        this.token = token;
        this.id = admin.getId();
        this.shopId = admin.getShopId();
        this.userName = admin.getUserName();
        this.password = admin.getPassword();
        this.level = admin.getLevel();
        this.kind = Constant.USER_TYPE_ADMIN;
    }

    public UserSession(String token, User user) {
        this.userName = user.getUserName();
        this.realName = user.getRealName();
        this.mobile = user.getMobile();
        this.address = user.getAddress();
        this.sumOfMoney = user.getSumOfMoney();
        this.integral = user.getIntegral();
        this.kind = user.getKind();
    }

    public boolean isAdmin() {
        return this.kind == Constant.USER_TYPE_ADMIN;
    }

    public boolean isSuperAdmin() {
        return (this.isAdmin() && this.shopId == 0 && this.level == 1);
    }

    public boolean isCustomer() {
        return this.getKind() == Constant.USER_TYPE_CUSTOMER;
    }

    public boolean isDistributor() {
        return this.getKind() == Constant.USER_TYPE_DISTRIBUTOR;
    }
}
