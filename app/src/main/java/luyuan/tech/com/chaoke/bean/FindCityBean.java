package luyuan.tech.com.chaoke.bean;

import com.contrarywind.interfaces.IPickerViewData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/8/1
 * @describe:
 */


public class FindCityBean implements IPickerViewData {

    /**
     * id : 2
     * city_name : 朝阳区
     * createtime : 1560302691
     * parent_id : 1
     * default : 1
     * updatetime : null
     * area_list : [{"id":1,"area_name":"三里屯太古里","cid":2,"createtime":"1560302691"}]
     */

    private int id;
    private String city_name;
    private String createtime;
    private int parent_id;
    @SerializedName("default")
    private int defaultX;
    private Object updatetime;
    private List<AreaListBean> area_list;

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getDefaultX() {
        return defaultX;
    }

    public void setDefaultX(int defaultX) {
        this.defaultX = defaultX;
    }

    public Object getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Object updatetime) {
        this.updatetime = updatetime;
    }

    public List<AreaListBean> getArea_list() {
        return area_list;
    }

    public void setArea_list(List<AreaListBean> area_list) {
        this.area_list = area_list;
    }

    @Override
    public String getPickerViewText() {
        return city_name;
    }

    public static class AreaListBean implements IPickerViewData {
        /**
         * id : 1
         * area_name : 三里屯太古里
         * cid : 2
         * createtime : 1560302691
         */

        private int id;
        private String area_name;
        private int cid;
        private String createtime;

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

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        @Override
        public String getPickerViewText() {
            return area_name;
        }
    }

    public static AreaListBean newChildBean(){
        AreaListBean childCityBean = new AreaListBean();
        childCityBean.setArea_name("");
        childCityBean.setId(-1);
        return childCityBean;
    }
}
