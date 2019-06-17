package luyuan.tech.com.chaoke.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/6/15
 * @describe:
 */


public class ShouFangShenPiBean {

    /**
     * data : [{"room_name":"三里屯北校区","createtime":"2019/06/13 09:02"},{"room_name":"三里屯北校区","createtime":"2019/06/13 09:02"}]
     * count : 2
     */

    private int count;
    private List<DataBean> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * room_name : 三里屯北校区
         * createtime : 2019/06/13 09:02
         */

        private String room_name;
        private String createtime;

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
