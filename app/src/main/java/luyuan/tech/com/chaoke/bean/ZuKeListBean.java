package luyuan.tech.com.chaoke.bean;

/**
 * @author: lujialei
 * @date: 2019/6/21
 * @describe:
 */


public class ZuKeListBean {

    /**
     * id : 3
     * room : 3
     * office : 1
     * toilet : 2
     * city_name : 海淀区
     * username : 小猪猪
     * phone : 13400000000
     * rent_min : 800
     * rent_max : 1600
     * grade : 1
     */

    private int id;
    private int room;
    private int office;
    private int toilet;
    private String city_name;
    private String username;
    private String phone;
    private String rent_min;
    private String rent_max;
    private int grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public int getToilet() {
        return toilet;
    }

    public void setToilet(int toilet) {
        this.toilet = toilet;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRent_min() {
        return rent_min;
    }

    public void setRent_min(String rent_min) {
        this.rent_min = rent_min;
    }

    public String getRent_max() {
        return rent_max;
    }

    public void setRent_max(String rent_max) {
        this.rent_max = rent_max;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
