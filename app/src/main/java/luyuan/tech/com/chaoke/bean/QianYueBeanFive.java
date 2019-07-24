package luyuan.tech.com.chaoke.bean;

import android.provider.ContactsContract;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/7/24
 * @describe:
 */


public class QianYueBeanFive {
    private String old_id;
    private List<DataBean> data;

    public String getOld_id() {
        return old_id;
    }

    public void setOld_id(String old_id) {
        this.old_id = old_id;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean{
        private String id;
        private String entrust_cycle_begin;
        private String entrust_cycle_end;
        private String free_cycle_begin;
        private String free_cycle_end;
        private String rent_price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEntrust_cycle_begin() {
            return entrust_cycle_begin;
        }

        public void setEntrust_cycle_begin(String entrust_cycle_begin) {
            this.entrust_cycle_begin = entrust_cycle_begin;
        }

        public String getEntrust_cycle_end() {
            return entrust_cycle_end;
        }

        public void setEntrust_cycle_end(String entrust_cycle_end) {
            this.entrust_cycle_end = entrust_cycle_end;
        }

        public String getFree_cycle_begin() {
            return free_cycle_begin;
        }

        public void setFree_cycle_begin(String free_cycle_begin) {
            this.free_cycle_begin = free_cycle_begin;
        }

        public String getFree_cycle_end() {
            return free_cycle_end;
        }

        public void setFree_cycle_end(String free_cycle_end) {
            this.free_cycle_end = free_cycle_end;
        }

        public String getRent_price() {
            return rent_price;
        }

        public void setRent_price(String rent_price) {
            this.rent_price = rent_price;
        }

        public CelueParam toCeLueParam() {
            CelueParam param = new CelueParam();
            param.setMoney(rent_price);
            param.setWeituo_kaishi(entrust_cycle_begin);
            param.setWeituo_jieshu(entrust_cycle_end);
            param.setMianzu_kaishi(free_cycle_begin);
            param.setMianzu_jieshu(free_cycle_end);
            return param;
        }
    }
}
