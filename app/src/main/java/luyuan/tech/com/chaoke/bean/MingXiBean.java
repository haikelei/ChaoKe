package luyuan.tech.com.chaoke.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/7/13
 * @describe:
 */


public class MingXiBean {

    /**
     * month_data : [{"id":32,"createtime":"2019-07-03 15:48","wuye_address":"渝北区加州花园B1栋6-5"}]
     * month_count : 1
     * years_count : 1
     */

    private int month_count;
    private int years_count;
    private List<MonthDataBean> month_data;

    public int getMonth_count() {
        return month_count;
    }

    public void setMonth_count(int month_count) {
        this.month_count = month_count;
    }

    public int getYears_count() {
        return years_count;
    }

    public void setYears_count(int years_count) {
        this.years_count = years_count;
    }

    public List<MonthDataBean> getMonth_data() {
        return month_data;
    }

    public void setMonth_data(List<MonthDataBean> month_data) {
        this.month_data = month_data;
    }

    public static class MonthDataBean {
        /**
         * id : 32
         * createtime : 2019-07-03 15:48
         * wuye_address : 渝北区加州花园B1栋6-5
         */

        private int id;
        private String createtime;
        private String wuye_address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getWuye_address() {
            return wuye_address;
        }

        public void setWuye_address(String wuye_address) {
            this.wuye_address = wuye_address;
        }
    }
}
