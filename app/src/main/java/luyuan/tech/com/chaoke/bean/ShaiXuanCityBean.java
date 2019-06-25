package luyuan.tech.com.chaoke.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/6/25
 * @describe:
 */


public class ShaiXuanCityBean {

    /**
     * city_name : 朝阳区
     * id : 2
     * area_list : [{"area_id":1,"area_name":"三里屯太古里"}]
     */

    private String city_name;
    private int id;
    private List<AreaListBean> area_list;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<AreaListBean> getArea_list() {
        return area_list;
    }

    public void setArea_list(List<AreaListBean> area_list) {
        this.area_list = area_list;
    }

    public static class AreaListBean {
        /**
         * area_id : 1
         * area_name : 三里屯太古里
         */

        private int area_id;
        private String area_name;

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }
    }
}
