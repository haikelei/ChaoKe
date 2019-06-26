package luyuan.tech.com.chaoke.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/6/26
 * @describe:
 */


public class ChuZuHeTongBean {

    private List<ListBean> list;
    private List<ListBean> list1;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<ListBean> getList1() {
        return list1;
    }

    public void setList1(List<ListBean> list1) {
        this.list1 = list1;
    }

    public static class ListBean implements Parcelable {
        /**
         * id : 1
         * address : 当前物业地址，当前物业地址，当前物业地址，当前物业地址，当前物业地址，当前物业地址
         * createtime : 2019/06/18
         * state : 1
         */

        private int id;
        private String address;
        private String createtime;
        private int state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.address);
            dest.writeString(this.createtime);
            dest.writeInt(this.state);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.id = in.readInt();
            this.address = in.readString();
            this.createtime = in.readString();
            this.state = in.readInt();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

}
