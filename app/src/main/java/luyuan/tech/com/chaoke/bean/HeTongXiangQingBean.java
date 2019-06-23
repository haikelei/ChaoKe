package luyuan.tech.com.chaoke.bean;

import java.io.Serializable;

/**
 * @author: lujialei
 * @date: 2019/6/22
 * @describe:
 */


public class HeTongXiangQingBean implements Serializable {

    /**
     * wuye_address : 北京市朝阳区
     * landlady_name : 小马
     * landlady_phone : 18700000000
     * signing_time : 2019-06-19
     * code : XXXXXX(二维码地址)
     */

    private String wuye_address;
    private String landlady_name;
    private String landlady_phone;
    private String signing_time;
    private String code;

    public String getWuye_address() {
        return wuye_address;
    }

    public void setWuye_address(String wuye_address) {
        this.wuye_address = wuye_address;
    }

    public String getLandlady_name() {
        return landlady_name;
    }

    public void setLandlady_name(String landlady_name) {
        this.landlady_name = landlady_name;
    }

    public String getLandlady_phone() {
        return landlady_phone;
    }

    public void setLandlady_phone(String landlady_phone) {
        this.landlady_phone = landlady_phone;
    }

    public String getSigning_time() {
        return signing_time;
    }

    public void setSigning_time(String signing_time) {
        this.signing_time = signing_time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
