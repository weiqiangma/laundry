package com.mawkun.laundry.base.common.Constant;

//系统常量
public interface Constant {
    int STATUS_NO  = 0;
    int STATUS_YES = 1;

    //=============================用户类型=============================//
    //学生
    int USER_TYPE_STUDENT           = 1;
    //老师
    int USER_TYPE_TEACHER           = 2;
    //家长
    int USER_TYPE_PARENT            = 3;
    //访客
    int USER_TYPE_GUEST             = 4;
    //居民(需要进出学校运动的人)
    int USER_TYPE_RESIDENT          = 7;

    //userParent表：订阅短信 0 未订  1订购
    int USERPARENT_NOT_ORDERSMS     =0 ;
    int USERPARENT_ORDERSMS         =1 ;

    // userParent表：主家长  0 非主家 1主家
    int USERPARENT_NOT_MASTER        =0 ;
    int USERPARENT_IS_MASTER         =1 ;

    // 是否为班主任  0 非班主任 1班主任
    int TEACHER_NOT_MASTER          =0 ;
    int TEACHER_IS_MASTER           =1 ;

    // 用户请假 开始请假事件 1 结束请假事件 2
    int USER_LEAVE_START            = 1 ;
    int USER_LEAVE_END              = 2 ;

    //=============================用户子类型=============================//
    int USER_TEA_SK_NORMAL          = 201;//普通教职工
    int USER_TEA_SK_PARENT          = 202;//教职工+家长
    int USER_TEA_SK_GARDENER        = 205;//教职工+园丁
    int USER_TEA_SK_SERVICE         = 206;//教职工+后勤
    int USER_TEA_SK_SECURITY        = 207;//教职工+安保
    int USER_TEA_SK_CORP            = 208;//教职工+编外人员(公司人员)
    int USER_TEA_SK_ADMIN           = 299;//教职工+管理员

    int USER_PAR_SK_FATHER          = 301;//爸爸
    int USER_PAR_SK_MOTHER          = 302;//妈妈
    int USER_PAR_SK_DAD_FATHER      = 303;//爷爷
    int USER_PAR_SK_DAD_MOTHER      = 304;//奶奶
    int USER_PAR_SK_MUM_FATHER      = 305;//外公
    int USER_PAR_SK_MUM_MOTHER      = 306;//外婆
    int USER_PAR_SK_BROTHER         = 307;//哥哥
    int USER_PAR_SK_SISTER          = 308;//姐姐
    int USER_PAR_SK_UNCLE           = 309;//叔叔
    int USER_PAR_SK_AUNT            = 310;//阿姨
    int USER_PAR_SK_NEIGHBOR        = 311;//邻居
    int USER_PAR_SK_OTHER           = 399;//其他
    //-------------------------------------END----------------------------

    //用户是否为管理员  0:不是(默认) 1:是
    int USER_IS_ADMIN_NO            = 0;
    int USER_IS_ADMIN_YES           = 1;

    //用户状态 0:正常 1：冻结(删除) 2未授权  3锁定
    int USER_STATUS_ACTIVE          = 0;
    int USER_STATUS_DEL             = 1;
    int USER_STATUS_NOT_AUTH        = 2;
    int USER_STATUS_LOCK            = 3;

    //=============================用户性别=============================
    // M男；F女；N未知
    String SEX_MAM                  = "M";
    String SEX_FEMALE               = "F";
    String SEX_NO                   = "N";


    //最新家校组织  1校区 2学段 3年级 4班级 5学生-家长-老师组织 51学生 52家长 53 老师 10 内部通讯录
    int NEW_RANK_SCHOOL                 = 1;
    int NEW_RANK_PHASE                  = 2;
    int NEW_RANK_GRADE                  = 3;
    int NEW_RANK_CLASS                  = 4;
    int NEW_RANK_STU_PAR_TEA            = 5;
    int NEW_RANK_STUDENT                = 51;
    int NEW_RANK_PARENT                 = 52;
    int NEW_RANK_TEACHER                = 53;
    int NEW_RANK_INTERIOR               = 10;

    // 钉钉组织状态 0未授权 1授权 -1异常状态  -2 删除
    int DING_STATUS_NOT_AUTH            = 0;
    int DING_STATUS_AUTH                = 1;
    int DING_STATUS_ERROR               =-1;
    int DING_STATUS_DEL                 =-2;

    // 内部通讯录库1  家校通讯录2
    int DING_INTERNAL                   = 1;
    int DING_JXT                        = 2;


    //钉钉消息通知 1文本 2图片 3语音 4文件  5链接  6OA  7markdown 8卡片
    int MSG_TYPE_TEXT                   = 1;
    int MSG_TYPE_IMAGE                  = 2;
    int MSG_TYPE_VOICE                  = 3;
    int MSG_TYPE_FULE                   = 4;
    int MSG_TYPE_LINK                   = 5;
    int MSG_TYPE_OA                     = 6;
    int MSG_TYPE_MARKDOWN               = 7;
    int MSG_TYPE_ACTIONCARD             = 8;

    //钉钉最顶层的组织
    int HIGHEST_ORG                     = 1;

    //消息推送类型: 1:离校,2:进校,3:一键通知,4:短信消息,5:同步头像到闸机异常消息,6:验证码
    int LEAVE_SCHOOL_MSG                = 1;
    int INTO_SCHOOLL_MSG                = 2;
    int NOTICE_MSG                      = 3;
    int SEND_MSG                        = 4;
    int SYNC_ERR                        = 5;
    int CODE_MSG                        = 6;

    //消息类型  1钉钉推送的消息  2短信推送的消息
    int DING_MSG_PUSH                   =1;
    int NOTE_MSG_PUSH                   =2;

    // org_config的通用配置 0未授权(否) 1授权(是)
    int CONFIG_AUTH_NO                  = 0;
    int CONFIG_AUTH_YES                 = 1;


    //钉钉工作通知消息 0:未开始，1:处理中，2:处理完毕 -1:撤回
    int MESSAGE_STATUS_NOT_SEND         = 0;
    int MESSAGE_STATUS_SENDING          = 1;
    int MESSAGE_STATUS_SEND_SUCCESS     = 2;
    int MESSAGE_STATUS_WITHDRAW         = -1;


    //进校
    int ATTENDANCE_DIRECTION_INTO       = 0;
    //离校
    int ATTENDANCE_DIRECTION_OUT        = 1;

    //考勤时间段,早晨
    int ATTENDANCE_TYPE_MORNING         = 1;
    //考勤时间段,中午
    int ATTENDANCE_TYPE_AFTERNOON       = 2;
    //考勤时间段,晚上
    int ATTENDANCE_TYPE_NIGHT           = 3;

    //
    String[] STATUS_ENUM = new String[]{"正常","迟到","早退","缺卡","请假"};
    //考勤异常(程序错误)
    int ATTENDANCE_RESULT_ERROR       = -1;
    //考勤正常
    int ATTENDANCE_RESULT_SUCCESS     = 0;
    //考勤迟到
    int ATTENDANCE_RESULT_CD          = 1;
    //考勤早退
    int ATTENDANCE_RESULT_ZT          = 2;
    //考勤缺卡
    int ATTENDANCE_RESULT_QK          = 3;
    //考勤请假
    int ATTENDANCE_RESULT_QJ          = 4;

    //考勤尚未创建
    int USER_CARD_STATUS_UNBIND         = 0;
    //考勤卡已经建立
    int USER_CARD_STATUS_BINGED         = 1;
    //人脸卡尚未创建
    int USER_FACE_STATUS_UNBIND         = 0;
    //人脸卡已经创建
    int USER_FACE_STATUS_BINGED         = 1;

    //绑定卡失败的
    int BING_ERROR                      =-1;

    // 0不是访客时间 1访客时间
    int GATE_CONFIG_NOT_HAVA_OPEN       = 0;
    int GATE_CONFIG_HAVA_OPEN           = 1;

    //开放时间状态： 0开放 1关闭
    int GATE_CONFIG_STATUS_USE          = 0;
    int GATE_CONFIG_STATUS_NOT_USE      = 1;

    //闸机在线状态 ：1离线  2在线
    int GATE_ONLINE_STATUS_OFF           =1;
    int GATE_ONLINE_STATUS_ON            =2;

    //##################### 访客与二维码 #########################
    //事件状态： 0新建 1访客完善信息 -1访客未到  3访客到确认
    int EVENT_STATUS_CREAT              = 0;
    int EVENT_STATUS_FILL_DATA          = 1;
    int EVENT_STATUS_NOT_COME           = -1;
    int EVENT_STATUS_GUEST_COME         = 3;

    //访客状态  0默认  1删除
    int GUEST_STATUS_DEFAULT             = 0;
    int GUEST_STATUS_DELETE              = 1;


    //二维码类型 1老师创建 2(管理员创建)永久型
    int QRCODE_TYPE_TEACHER             = 1;
    int QRCODE_TYPE_ADMIN               = 2;

    //二维码状态 0有效 1无效
    int QRCODE_STATUS_NORMAL            = 0;
    int QRCODE_STATUS_INVAILD           = 1;

    //=============================系统用户状态=============================
    //用户登录和请求token权限返回状态
    int LOGIN_NOTFIND       = 40000;
    //用户被锁定
    int LOGIN_LOCKED        = 40001;
    //token失效
    int LOGIN_TIME_OUT      = 40002;
    //没有权限,操作失败
    int LOGIN_AUTHORITY     = 40003;
    //没有token授权码
    int LOGIN_TOKEN_EMPTY   = 40005;
    //=============================SMS短信状态=============================
    int SMS_STATUS_SUCCESS          =1;//SMS短信已发送
    int SMS_STATUS_RETRY            =2;//SMS短信未送达 处于重试状态
    int SMS_STATUS_FAIL             =3;//SMS短信重试2次后 失败

    //=============================阿里云短信模版编号=============================
    String SMS_TEMP_IDCODE = "SMS_178450241";//验证码模版

    //=============================支付状态=============================
    //状态10:支付成功,20:等待退款,30:退款失败,40:退款成功 50:拒绝退款
    int PAY_STATU_SUCCESS           =10;
    int PAY_STATU_REFUND_WAIT       =20;
    int PAY_STATU_REFUND_FAIL       =30;
    int PAY_STATU_REFUND_SUCCESS    =30;
    int PAY_STATU_REFUND_REJECT     =50;
    //=============================打印状态=============================
    //状态0;未打印  1已打印
    int PRINT_STATU_NOT = 0;
    int PRINT_STATU_SUCCESS = 1;

    //打印类型 0访客模式   1无访客身份证模式
    int PRINT_TYPE_GUEST = 0;
    int PRINT_TYPE_NOGUEST = 1;

    //打印机状态 0启用 1禁用
    int PRINT_CONFIG_STATU_ON = 0;
    int PRINT_CONFIG_STATU_OFF = 1;

    //缴费项目状态 0正常 1删除
    int PROJECT_STATUS_NORMAL=      0;
    int PROJECT_STATUS_DELETE=      1;

    //=============================缴费_人员状态=============================
    //状态0:未交费,1:无需缴费,2:缴费成功
    int PROJECT_PAY_STATU_NOT       =0;
    int PROJECT_PAY_STATU_NOT_NEED  =1;
    int PROJECT_PAY_STATU_SUCCESS   =2;

    //=============================  是否推送消息 0不推送消息（默认） 1推送消息   =========================
    int ORG_CONFIG_CLOSE_PUSH_MSG       = 0;
    int PROJECT_PAY_OPEN_NOT_NEED       = 1;

    //=============================请假记录状态 0申请中 1通过 2驳回=============================
    int USER_LEAVE_STATU_ON       = 0;
    int USER_LEAVE_STATU_PASS     = 1;
    int USER_LEAVE_STATU_REJECT   = 2;

    String TOKEN_TYPE_API = "Authorization";
    String TOKEN_TYPE_ADM = "adminToken";

    String[] WEEK_NAMES = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    // 通用配置 正常0 冻结/隐藏1  删除9
    int NORMAL      =0;
    int freeze      =1;
    int DEL         =9;

    //=============================钉钉考勤字段=============================
    //状态1：加班，2：出差，3：请假
    int WORK_OVERTIME = 1;
    int BUSINESS_TRIP = 2;
    int ASK_FOR_LEAVE = 3;

}
