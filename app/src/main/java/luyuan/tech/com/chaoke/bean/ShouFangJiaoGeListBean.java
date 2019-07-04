package luyuan.tech.com.chaoke.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: lujialei
 * @date: 2019/7/4
 * @describe:
 */


public class ShouFangJiaoGeListBean implements Parcelable {
    private String id;
    private String wuye_address;
    private String createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWuye_address() {
        return wuye_address;
    }

    public void setWuye_address(String wuye_address) {
        this.wuye_address = wuye_address;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.wuye_address);
        dest.writeString(this.createtime);
    }

    public ShouFangJiaoGeListBean() {
    }

    protected ShouFangJiaoGeListBean(Parcel in) {
        this.id = in.readString();
        this.wuye_address = in.readString();
        this.createtime = in.readString();
    }

    public static final Parcelable.Creator<ShouFangJiaoGeListBean> CREATOR = new Parcelable.Creator<ShouFangJiaoGeListBean>() {
        @Override
        public ShouFangJiaoGeListBean createFromParcel(Parcel source) {
            return new ShouFangJiaoGeListBean(source);
        }

        @Override
        public ShouFangJiaoGeListBean[] newArray(int size) {
            return new ShouFangJiaoGeListBean[size];
        }
    };
}
