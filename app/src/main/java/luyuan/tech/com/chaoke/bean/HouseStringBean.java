package luyuan.tech.com.chaoke.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author: lujialei
 * @date: 2019/6/17
 * @describe:
 */


public class HouseStringBean implements Serializable, Parcelable {

    /**
     * id : 1
     * room_name : 三里屯北小区
     * cover : http://thirdwx.qlogo.cn/mmopen/vi_32/cDWAkuEJTaSLEgW9A3HIotibYYjFNUqb4aTLvk0dVUXGnIyKn1HicI5UDL8KWtxand35LYDgWA0wUvqEHvlhickHw/132
     * long_price : 4500.00
     * region : 朝阳区
     * type : 3
     * orientation : 1
     * apartment : 二室
     * fit_up : 3
     * area : 45.00
     */

    private int id;
    private String room_name;
    private String cover;
    private String long_price;
    private String region;
    private String type;
    private String orientation;
    private String apartment;
    private String fit_up;
    private String area;
    public boolean checked;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLong_price() {
        return long_price;
    }

    public void setLong_price(String long_price) {
        this.long_price = long_price;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getFit_up() {
        return fit_up;
    }

    public void setFit_up(String fit_up) {
        this.fit_up = fit_up;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.room_name);
        dest.writeString(this.cover);
        dest.writeString(this.long_price);
        dest.writeString(this.region);
        dest.writeString(this.type);
        dest.writeString(this.orientation);
        dest.writeString(this.apartment);
        dest.writeString(this.fit_up);
        dest.writeString(this.area);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
    }

    public HouseStringBean() {
    }

    protected HouseStringBean(Parcel in) {
        this.id = in.readInt();
        this.room_name = in.readString();
        this.cover = in.readString();
        this.long_price = in.readString();
        this.region = in.readString();
        this.type = in.readString();
        this.orientation = in.readString();
        this.apartment = in.readString();
        this.fit_up = in.readString();
        this.area = in.readString();
        this.checked = in.readByte() != 0;
    }

    public static final Creator<HouseStringBean> CREATOR = new Creator<HouseStringBean>() {
        @Override
        public HouseStringBean createFromParcel(Parcel source) {
            return new HouseStringBean(source);
        }

        @Override
        public HouseStringBean[] newArray(int size) {
            return new HouseStringBean[size];
        }
    };
}
