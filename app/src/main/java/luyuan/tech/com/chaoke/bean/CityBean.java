package luyuan.tech.com.chaoke.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/6/20
 * @describe:
 */


public class CityBean implements IPickerViewData {

    /**
     * id : 1
     * city_name : 北京市
     * child_city : [{"id":2,"city_name":"朝阳区","child_area":[{"id":1,"area_name":"三里屯太古里"}]},{"id":3,"city_name":"海淀区","child_area":[{"id":2,"area_name":"富力城"}]}]
     */

    private int id;
    private String city_name;
    private List<ChildCityBean> child_city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public List<ChildCityBean> getChild_city() {
        return child_city;
    }

    public void setChild_city(List<ChildCityBean> child_city) {
        this.child_city = child_city;
    }

    @Override
    public String getPickerViewText() {
        return city_name;
    }

    public static class ChildCityBean implements IPickerViewData {
        /**
         * id : 2
         * city_name : 朝阳区
         * child_area : [{"id":1,"area_name":"三里屯太古里"}]
         */

        private int id;
        private String city_name;
        private List<ChildAreaBean> child_area;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public List<ChildAreaBean> getChild_area() {
            return child_area;
        }

        public void setChild_area(List<ChildAreaBean> child_area) {
            this.child_area = child_area;
        }

        @Override
        public String getPickerViewText() {
            return city_name;
        }

        public static class ChildAreaBean implements IPickerViewData {
            /**
             * id : 1
             * area_name : 三里屯太古里
             */

            private int id;
            private String area_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }

            @Override
            public String getPickerViewText() {
                return area_name;
            }
        }
    }
    public  static  ChildCityBean newChildBean(){
        ChildCityBean childCityBean = new ChildCityBean();
        childCityBean.setCity_name("");
        childCityBean.setId(-1);
        ChildCityBean.ChildAreaBean childAreaBean = new ChildCityBean.ChildAreaBean();
        childAreaBean.setArea_name("");
        childAreaBean.setId(-1);
        ArrayList<ChildCityBean.ChildAreaBean> list = new ArrayList<>();
        list.add(childAreaBean);
        childCityBean.setChild_area(list);
        return childCityBean;
    }

    public  static ChildCityBean.ChildAreaBean newAreaBean(){
        ChildCityBean.ChildAreaBean childAreaBean = new ChildCityBean.ChildAreaBean();
        childAreaBean.setArea_name("");
        childAreaBean.setId(-1);
        return childAreaBean;
    }
}
