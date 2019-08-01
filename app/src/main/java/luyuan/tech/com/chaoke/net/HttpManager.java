package luyuan.tech.com.chaoke.net;

import com.zhouyou.http.request.GetRequest;
import com.zhouyou.http.request.PostRequest;

/**
 * @author: lujialei
 * @date: 2018/9/29
 * @describe:
 */

public class HttpManager {


    /**
     * get请求
     */
    public static GetRequest get(String url) {
        return new CustomGetRequest(url);
    }

    /**
     * post请求
     */
    public static PostRequest post(String url) {
        return new CustomPostRequest(url);
    }




    /**
     * 登录
     */
    public static final String LOGIN = "/api/login/login";

    /**
     * 收房审批
     */
    public static final String SHOUFANGSHENPI_List = "/api/agent/collect_check";

    /**
     * 小区名称
     */
    public static final String XIAOQUMINGCHENG = "/api/agent/neighbour";

    /**
     * 发布房源第一步
     */
    public static final String FABUONE = "/api/agent/release_first";

    /**
     * 发布房源第二步
     */
    public static final String FABUTWO = "/api/agent/release_second";

    /**
     * 房源列表
     */
    public static final String HOUSE_LIST = "/api/agent/house_list";


    /**
     * 房任务
     */
    public static final String HOUSE_TASK = "/api/agent/house_task";

    /**
     * 客任务
     */
    public static final String CLIENT_TASK = "/api/agent/tenant_task";


    public static final String HOUSE_DETAIL = "/api/agent/house_detail";


    /*
   *出租合同第一步
     */
    public static final String HETONG_ONE = "/api/Contract/lease_contract_first";
    public static final String HETONG_TWO = "/api/Contract/lease_contract_second";
    public static final String HETONG_THREE = "/api/Contract/lease_contract_third";
    public static final String HETONG_TOUR = "/api/Contract/lease_contract_fouth";


    /**
     * 上传图片
     */
//    public static final String IMAGE = "/api/login/upload";


    /**
     * 租客详情
     */
    public static final String ZUKE_DETAIL = "/api/agent/tenant_detail";

    /**
     * 合同详情
     */
    public static final String HETONG_DETAIL = "/api/Contract/lease_contract_detail";

    /**
     * 带看约看
     */
    public static final String DAIKAN_YUEKAN = "/api/agent/tenant_look";


    public static final String GEN_JIN = "/api/agent/tenant_follow";

    /**
     * 租客信息
     */
    public static final String ZUKEXINXI_ONE = "/api/agent/add_rent_first";

    public static final String ZUKEXINXI_TWO = "/api/agent/add_rent_second";

    /**
     * 获取城市
     */
    public static final String CITYS = "/api/agent/all_city";

    public static final String FIND_CITYS = "/api/Clientindex/find_city";

    public static final String QIUZUXUQIU = "/api/Clientuser/rent_seek";


    /**
     * 修改状态
     */
    public static final String CHANGE_STATE = "/api/agent/edit_tenant";

    /**
     * 租客列表
     */
    public static final String ZUKE_LIST = "/api/agent/rent_customer";

    /**
     * 收房审批信息保存
     */
    public static final String SHOUFANG_SHENPI = "/api/agent/check_again";

    /**
     * 保存房源跟进
     */
    public static final String GENJIN_FANGYUAN = "/api/agent/upload_follow";


    /**
     * 房屋签约第一步
     */
    public static final String FANGYUANQIANYUE = "/api/agent/house_signing";

    /**
     * 上传实勘图片
     */
    public static final String SHIKAN_TUPIAN = "/api/agent/upload_pics";


    /**
     * 保存出房交割
     */
    public static final String CHUFANG_JIAOGE = "/api/agent/out_house";

    /**
     * 保存收房交割
     */
    public static final String SHOUFANG_JIAOGE = "/api/agent/collect_house";


    /**
     * 通讯录
     */
    public static final String TONG_XUN_LU = "/api/agent/mail_list";


    /**
     * 首页折线图
     */
    public static final String ZHEXIANTU = "/api/agent/statistics_data";


    /**
     * 消息详情
     */
    public static final String XIAOXI_XIANAGQING = "/api/agent/msg_detail";


    /**
     * 消息列表
     */
    public static final String XIAOXI_LIST = "/api/agent/user_msg";


    /**
     * 筛选获取的城市以及商圈
     */
    public static final String CHENGSHI_LIST = "/api/agent/city_list";


    /**
     * 重置密码
     */
    public static final String CHONGZHIMIMA = "/api/login/edit_pwd";


    /**
     * 验证码
     */
    public static final String YANZHENGMA = "/api/login/sendCode";

    /**
     * 出租合同列表
     */
    public static final String CHUZUHETONG_LIST = "/api/agent/lease_contract";


    /**
     * 委托合同列表
     */
    public static final String WEITUOHETONG_LIST = "/api/agent/entrust_contract";


    /**
     * 出租合同详情
     */
    public static final String CHUZUHETONG_XIANGQING = "/api/agent/lease_detail";

    /**
     * 委托合同详情
     */
    public static final String WEITUOHETONG_XIANGQING = "/api/agent/contract_detail";


    public static final String IMAGE_TOKEN = "/api/Qniu/get_token";

    /**
     * 个人业绩
     */
    public static final String GERENYEJI = "/api/agent/ranking_order";

    /**
     * 收房交割列表
     */
    public static final String SHOUFANGJIAOGE_LIST = "/api/agent/collect_list";


    /**
     * 出房交割列表
     */
    public static final String CHUFANGJIAOGE_LIST = "/api/agent/out_list";

    /**
     * 续签
     */
    public static final String XUANQIAN = "/api/agent/signing_again";

    /**
     * 搜索
     */
    public static final String SEARCH = "/api/agent/do_search";

    /**
     * 修改密码
     */
    public static final String CHANGE_PWD = "/api/login/edit_pwd";

    /**
     * 个人业绩明细
     */
    public static final String YEJIMINGXI = "/api/agent/achievement_detail";

    /**
     * 委托合同正本
     */
    public static final String WEITUOHETONGZHENGBEN = "/api/agent/showSigningContract";

    public static final String CHUZUHETONGZHENGBEN = "/api/agent/showRentContract";

    /**
     * 房屋配置
     */
    public static final String PEIZHI = "/api/agent/house_config";

    /**
     * 委托续签
     */
    public static final String ChuZuXUQIAN = "/api/agent/contract_again";

    /**
     * 获取签约数据
     */
    public static final String QIANYUESHUJU = "/api/agent/getSigningData";


    public static final String QIANYUEFANGYUAN = "/api/agent/takeLookRecord";








}
