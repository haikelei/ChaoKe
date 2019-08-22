package luyuan.tech.com.chaoke.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/6/19
 * @describe:
 */


public class HouseDetailBean implements Serializable {

    /**
     * id : 1
     * pics : ["http://thirdwx.qlogo.cn/mmopen/vi_32/cDWAkuEJTaSLEgW9A3HIotibYYjFNUqb4aTLvk0dVUXGnIyKn1HicI5UDL8KWtxand35LYDgWA0wUvqEHvlhickHw/132","/static/upload/headimg/headimgurl.png","http://thirdwx.qlogo.cn/mmopen/vi_32/zcFiaYibsHnfCib4fCYRcDWQKIDew7AibtEQAicz0sO6Q2ya6iaxUw5XtvEeCCxNncr8l8Pkq0YbYwWk4E0PHBST1FGw/132"]
     * room_name : 三里屯北小区
     * long_price : 4500.00
     * apartment : 二室
     * area : 45.00
     * fit_up : 3
     * orientation : 1
     * floor : 16
     * source : 6
     * see_type : 2
     * lat_pos : 116.460387
     * lng_pos : 39.944916
     * describe : 非常好的房源哦好好好
     * configure : [{"id":2,"name":"热水器","icon":"renting.liebianzhe.com/static/upload/headimg/headimgurl.png"},{"id":3,"name":"空调","icon":"renting.liebianzhe.com/static/upload/headimg/headimgurl.png"},{"id":4,"name":"电视","icon":"renting.liebianzhe.com/static/upload/headimg/headimgurl.png"}]
     */

    private int id;
    private String room_name;
    private String long_price;
    private String apartment;
    private String area;
    private String area_name;
    private int fit_up;
    private int orientation;
    private String floor;
    private int source;
    private int see_type;
    private Double lat_pos;
    private Double lng_pos;
    private String describe;
    private List<String> pics;
    private List<ConfigureBean> configure;
    private String add_details;
    private long entrust_end;
    private String true_price;
    public String true_price2;

    public String getTrue_price() {
        return true_price;
    }

    public void setTrue_price(String true_price) {
        this.true_price = true_price;
    }

    public long getEntrust_end() {
        return entrust_end;
    }

    public void setEntrust_end(long entrust_end) {
        this.entrust_end = entrust_end;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getAdd_details() {
        return add_details;
    }

    public void setAdd_details(String add_details) {
        this.add_details = add_details;
    }

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

    public String getLong_price() {
        return long_price;
    }

    public void setLong_price(String long_price) {
        this.long_price = long_price;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getFit_up() {
        return fit_up;
    }

    public void setFit_up(int fit_up) {
        this.fit_up = fit_up;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getSee_type() {
        return see_type;
    }

    public void setSee_type(int see_type) {
        this.see_type = see_type;
    }

    public Double getLat_pos() {
        return lat_pos;
    }

    public void setLat_pos(Double lat_pos) {
        this.lat_pos = lat_pos;
    }

    public Double getLng_pos() {
        return lng_pos;
    }

    public void setLng_pos(Double lng_pos) {
        this.lng_pos = lng_pos;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public List<ConfigureBean> getConfigure() {
        return configure;
    }

    public void setConfigure(List<ConfigureBean> configure) {
        this.configure = configure;
    }

    public static class ConfigureBean implements Serializable {
        /**
         * id : 2
         * name : 热水器
         * icon : renting.liebianzhe.com/static/upload/headimg/headimgurl.png
         */

        private int id;
        private String name;
        private String icon;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
