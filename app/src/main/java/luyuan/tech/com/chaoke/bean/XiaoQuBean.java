package luyuan.tech.com.chaoke.bean;

import java.io.Serializable;

/**
 * @author: lujialei
 * @date: 2019/6/16
 * @describe:
 */


public class XiaoQuBean implements Serializable {

    /**
     * id : 1
     * reside_name : 三里屯北小区
     * aid : 1
     * createtime : 1560302691
     * lat_pos : 116.460387
     * lng_pos : 39.944916
     */

    public String username;
    public String room_name;
    private int id;
    private String reside_name;
    private int aid;
    private String createtime;
    private String lat_pos;
    private String lng_pos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReside_name() {
        return reside_name;
    }

    public void setReside_name(String reside_name) {
        this.reside_name = reside_name;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getLat_pos() {
        return lat_pos;
    }

    public void setLat_pos(String lat_pos) {
        this.lat_pos = lat_pos;
    }

    public String getLng_pos() {
        return lng_pos;
    }

    public void setLng_pos(String lng_pos) {
        this.lng_pos = lng_pos;
    }
}
