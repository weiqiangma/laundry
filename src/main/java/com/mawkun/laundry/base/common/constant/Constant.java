package com.mawkun.laundry.base.common.constant;

//系统常量
public interface Constant {
    int STATUS_NO = 0;
    int STATUS_YES = 1;

    //=============================管理员类型=============================//
    int ADMIN_TYPE_SUPER = 1;   //主管理员
    int ADMIN_TYPE_COMMON = 2;  //普通管理员

    //=============================用户类型=============================//
    int USER_TYPE_CUSTOMER = 1;     //顾客
    int USER_TYPE_DISTRIBUTOR = 2;  //配送员
    int USER_TYPE_ADMIN = 3;        //管理员

    //=============================用户类型=============================//
    int ORDER_DELIVERY_SEND = 0;    //客户送至门店
    int ORDER_DELIVERY_GET = 1;     //配送员上门取货

    //=============================订单状态=============================//
    int ORDER_STATUS_PAID = 1;          //待支付
    int ORDER_STATUS_WAITING_REAP = 2;  //待收货
    int ORDER_STATUS_SURE_REAP = 3;     //确认收货
    int ORDER_STATUS_CLEANING = 4;      //清洗中
    int ORDER_STATUS_WAITING_TAKE = 5;  //待取货
    int ORDER_STATUS_SURE_TAKE = 6;     //已完成
    int ORDER_STATUS_INVALID = 7;       //无效订单

    //=============================系统参数状态=============================//
    int SYS_PARAM_OPEN = 1;     //开启
    int SYS_PARAM_CLOSE = 0;    //关闭
    //=============================系统用户状态=============================
    //用户登录和请求token权限返回状态
    int LOGIN_NOTFIND = 40000;
    //用户被锁定
    int LOGIN_LOCKED = 40001;
    //token失效
    int LOGIN_TIME_OUT = 40002;
    //没有权限,操作失败
    int LOGIN_AUTHORITY = 40003;
    //没有token授权码
    int LOGIN_TOKEN_EMPTY = 40005;

    //=============================支付状态=============================
    //状态10:支付成功,20:等待退款,30:退款失败,40:退款成功 50:拒绝退款
    int PAY_STATU_SUCCESS = 10;
    int PAY_STATU_REFUND_WAIT = 20;
    int PAY_STATU_REFUND_FAIL = 30;
    int PAY_STATU_REFUND_SUCCESS = 30;
    int PAY_STATU_REFUND_REJECT = 50;

    //=============================缴费_人员状态=============================
    //状态0:未交费,1:无需缴费,2:缴费成功
    int PROJECT_PAY_STATU_NOT = 0;
    int PROJECT_PAY_STATU_NOT_NEED = 1;
    int PROJECT_PAY_STATU_SUCCESS = 2;

    //=============================门店状态=============================
    int SHOP_LEVEL_FIRST = 0;   //总店
    int SHOP_LEVEL_SECOND = 1;  //分店

    int SHOP_STATUS_NORMAL = 0; //正常
    int SHOP_STATUS_LOCK = 1; //锁定
    int SHOP_STATUS_DELETE = 2; //删除
}
