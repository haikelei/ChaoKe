package luyuan.tech.com.chaoke.bean;

import java.io.Serializable;

/**
 * @author: lujialei
 * @date: 2019/6/17
 * @describe:
 */


public class HouseBean implements Serializable{

    /**
     * id : 1
     * room_name : 三里屯北小区
     * cover : http://thirdwx.qlogo.cn/mmopen/vi_32/cDWAkuEJTaSLEgW9A3HIotibYYjFNUqb4aTLvk0dVUXGnIyKn1HicI5UDL8KWtxand35LYDgWA0wUvqEHvlhickHw/132
     * long_price : 4500.00
     * region : 朝阳区
     * type : 3
     * orientation : 1
     * apartment : 二室
     * fit_up : 3
     * area : 45.00
     */

    private int id;
    private String room_name;
    private String cover;
    private String long_price;
    private String region;
    private int type;
    private int orientation;
    private String apartment;
    private int fit_up;
    private String area;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLong_price() {
        return long_price;
    }

    public void setLong_price(String long_price) {
        this.long_price = long_price;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public int getFit_up() {
        return fit_up;
    }

    public void setFit_up(int fit_up) {
        this.fit_up = fit_up;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
