package luyuan.tech.com.chaoke.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/6/19
 * @describe:
 */


public class HouseDetailBean {

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
    private int fit_up;
    private int orientation;
    private String floor;
    private int source;
    private int see_type;
    private String lat_pos;
    private String lng_pos;
    private String describe;
    private List<String> pics;
    private List<ConfigureBean> configure;

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

    public static class ConfigureBean {
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