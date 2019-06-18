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
    public static final String SHOUFANGSHENPI = "/api/agent/collect_check";

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



}
