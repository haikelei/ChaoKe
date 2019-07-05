package luyuan.tech.com.chaoke.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/6/27
 * @describe:
 */


public class WeiTuoHeTongDetailBean {

    /**
     * contract_data : {"id":1,"wuye_address":"北京市朝阳区","rent_num":"010001","from_by":1,"signing_type":"梵蒂冈地方","identifier":"HZCK-WT12000","signing_time":"2019-06-19","over_time":"2021-06-19"}
     * renovation : {"house_type":"不知道","reform_data":"风格豆腐干地方很高的","contract_type":"不知道是什么","renovation_begin":"2018-09-16","renovation_end":"2018-12-12"}
     * entrust : {"payment_msg":0,"payment_day":1,"rent_day":1,"entrust_begin":"2019-02-20","entrust_end":"2020-02-20"}
     * rent : {"rent_price":"2300.00","first_pay":"2019-07-15","is_parking":1,"rent_begin":"2019-07-01","rent_end":"2019-07-15"}
     * strategy : [{"entrust_cycle_begin":"2019-06-20","entrust_cycle_end":"1970-01-01","free_cycle_begin":"2020-06-20","free_cycle_end":"1970-01-01","rent_price":"2300.00"},{"entrust_cycle_begin":"2020-06-21","entrust_cycle_end":"1970-01-01","free_cycle_begin":"2021-06-21","free_cycle_end":"1970-01-01","rent_price":"2300.00"}]
     * property : {"property_address":"发动机工况到付即可","area":52,"apartment":"2室一厅1卫","share_desc":"什么情况","used_type":1,"is_loan":2}
     * property1 : {"people_type":0,"card_type":1,"username":"哈皮","card_num":"123456789","card_begin":"2014-09-19","card_end":"2019-09-19","card_zpic":"http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png","card_fpic":"http://renting.liebianzhe.comhttp://thirdwx.qlogo.cn/mmopen/vi_32/zcFiaYibsHnfCib4fCYRcDWQKIDew7AibtEQAicz0sO6Q2ya6iaxUw5XtvEeCCxNncr8l8Pkq0YbYwWk4E0PHBST1FGw/132","property_type":3,"property_num":"315315615185","num_pic":"http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png","home_pic":"http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png","attach_pic":"http://renting.liebianzhe.comhttp://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png","old_pic":"http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png","household_pic":"http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png","other_pic":""}
     * property2 : {"people_type":0,"card_type":1,"username":"哈皮","card_num":"123456789","card_begin":"2014-09-19","card_end":"2019-09-19","card_zpic":"http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png","card_fpic":"http://renting.liebianzhe.comhttp://thirdwx.qlogo.cn/mmopen/vi_32/zcFiaYibsHnfCib4fCYRcDWQKIDew7AibtEQAicz0sO6Q2ya6iaxUw5XtvEeCCxNncr8l8Pkq0YbYwWk4E0PHBST1FGw/132","property_num":"315315615185"}
     * out_people : {"card_type":1,"username":"傻呗","card_num":"123459698562625","phone":"18725125125","email_address":"1755212151@qq.com","comm_address":"gfdgdfhdfhfdggdf"}
     * common_people : {"card_type":1,"username":"傻呗","card_num":"123459698562625","phone":"18725125125","email_address":"1755212151@qq.com","comm_address":"gfdgdfhdfhfdggdf"}
     * payee : {"people_type":0,"card_type":1,"username":"各大公司的","card_num":"524124521112105","number_type":2,"number_num":"21452152","mechanism":"工商银行","branch":"更开放大陆鬼地方鬼地方"}
     * urgent_people : {"username":"股份大股东","phone":"15900000000"}
     */

    private ContractDataBean contract_data;
    private RenovationBean renovation;
    private EntrustBean entrust;
    private RentBean rent;
    private PropertyBean property;
    private Property1Bean property1;
    private Property2Bean property2;
    private OutPeopleBean out_people;
    private CommonPeopleBean common_people;
    private PayeeBean payee;
    private UrgentPeopleBean urgent_people;
    private List<StrategyBean> strategy;

    public ContractDataBean getContract_data() {
        return contract_data;
    }

    public void setContract_data(ContractDataBean contract_data) {
        this.contract_data = contract_data;
    }

    public RenovationBean getRenovation() {
        return renovation;
    }

    public void setRenovation(RenovationBean renovation) {
        this.renovation = renovation;
    }

    public EntrustBean getEntrust() {
        return entrust;
    }

    public void setEntrust(EntrustBean entrust) {
        this.entrust = entrust;
    }

    public RentBean getRent() {
        return rent;
    }

    public void setRent(RentBean rent) {
        this.rent = rent;
    }

    public PropertyBean getProperty() {
        return property;
    }

    public void setProperty(PropertyBean property) {
        this.property = property;
    }

    public Property1Bean getProperty1() {
        return property1;
    }

    public void setProperty1(Property1Bean property1) {
        this.property1 = property1;
    }

    public Property2Bean getProperty2() {
        return property2;
    }

    public void setProperty2(Property2Bean property2) {
        this.property2 = property2;
    }

    public OutPeopleBean getOut_people() {
        return out_people;
    }

    public void setOut_people(OutPeopleBean out_people) {
        this.out_people = out_people;
    }

    public CommonPeopleBean getCommon_people() {
        return common_people;
    }

    public void setCommon_people(CommonPeopleBean common_people) {
        this.common_people = common_people;
    }

    public PayeeBean getPayee() {
        return payee;
    }

    public void setPayee(PayeeBean payee) {
        this.payee = payee;
    }

    public UrgentPeopleBean getUrgent_people() {
        return urgent_people;
    }

    public void setUrgent_people(UrgentPeopleBean urgent_people) {
        this.urgent_people = urgent_people;
    }

    public List<StrategyBean> getStrategy() {
        return strategy;
    }

    public void setStrategy(List<StrategyBean> strategy) {
        this.strategy = strategy;
    }

    public static class ContractDataBean {
        /**
         * id : 1
         * wuye_address : 北京市朝阳区
         * rent_num : 010001
         * from_by : 1
         * signing_type : 梵蒂冈地方
         * identifier : HZCK-WT12000
         * signing_time : 2019-06-19
         * over_time : 2021-06-19
         */

        private int id;
        private String wuye_address;
        private String rent_num;
        private int from_by;
        private String signing_type;
        private String identifier;
        private String signing_time;
        private String over_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWuye_address() {
            return wuye_address;
        }

        public void setWuye_address(String wuye_address) {
            this.wuye_address = wuye_address;
        }

        public String getRent_num() {
            return rent_num;
        }

        public void setRent_num(String rent_num) {
            this.rent_num = rent_num;
        }

        public int getFrom_by() {
            return from_by;
        }

        public void setFrom_by(int from_by) {
            this.from_by = from_by;
        }

        public String getSigning_type() {
            return signing_type;
        }

        public void setSigning_type(String signing_type) {
            this.signing_type = signing_type;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getSigning_time() {
            return signing_time;
        }

        public void setSigning_time(String signing_time) {
            this.signing_time = signing_time;
        }

        public String getOver_time() {
            return over_time;
        }

        public void setOver_time(String over_time) {
            this.over_time = over_time;
        }
    }

    public static class RenovationBean {
        /**
         * house_type : 不知道
         * reform_data : 风格豆腐干地方很高的
         * contract_type : 不知道是什么
         * renovation_begin : 2018-09-16
         * renovation_end : 2018-12-12
         */

        private String house_type;
        private String reform_data;
        private String contract_type;
        private String renovation_begin;
        private String renovation_end;

        public String getHouse_type() {
            return house_type;
        }

        public void setHouse_type(String house_type) {
            this.house_type = house_type;
        }

        public String getReform_data() {
            return reform_data;
        }

        public void setReform_data(String reform_data) {
            this.reform_data = reform_data;
        }

        public String getContract_type() {
            return contract_type;
        }

        public void setContract_type(String contract_type) {
            this.contract_type = contract_type;
        }

        public String getRenovation_begin() {
            return renovation_begin;
        }

        public void setRenovation_begin(String renovation_begin) {
            this.renovation_begin = renovation_begin;
        }

        public String getRenovation_end() {
            return renovation_end;
        }

        public void setRenovation_end(String renovation_end) {
            this.renovation_end = renovation_end;
        }
    }

    public static class EntrustBean {
        /**
         * payment_msg : 0
         * payment_day : 1
         * rent_day : 1
         * entrust_begin : 2019-02-20
         * entrust_end : 2020-02-20
         */

        private int payment_msg;
        private int payment_day;
        private int rent_day;
        private String entrust_begin;
        private String entrust_end;

        public int getPayment_msg() {
            return payment_msg;
        }

        public void setPayment_msg(int payment_msg) {
            this.payment_msg = payment_msg;
        }

        public int getPayment_day() {
            return payment_day;
        }

        public void setPayment_day(int payment_day) {
            this.payment_day = payment_day;
        }

        public int getRent_day() {
            return rent_day;
        }

        public void setRent_day(int rent_day) {
            this.rent_day = rent_day;
        }

        public String getEntrust_begin() {
            return entrust_begin;
        }

        public void setEntrust_begin(String entrust_begin) {
            this.entrust_begin = entrust_begin;
        }

        public String getEntrust_end() {
            return entrust_end;
        }

        public void setEntrust_end(String entrust_end) {
            this.entrust_end = entrust_end;
        }
    }

    public static class RentBean {
        /**
         * rent_price : 2300.00
         * first_pay : 2019-07-15
         * is_parking : 1
         * rent_begin : 2019-07-01
         * rent_end : 2019-07-15
         */

        private String rent_price;
        private String first_pay;
        private int is_parking;
        private String rent_begin;
        private String rent_end;

        public String getRent_price() {
            return rent_price;
        }

        public void setRent_price(String rent_price) {
            this.rent_price = rent_price;
        }

        public String getFirst_pay() {
            return first_pay;
        }

        public void setFirst_pay(String first_pay) {
            this.first_pay = first_pay;
        }

        public int getIs_parking() {
            return is_parking;
        }

        public void setIs_parking(int is_parking) {
            this.is_parking = is_parking;
        }

        public String getRent_begin() {
            return rent_begin;
        }

        public void setRent_begin(String rent_begin) {
            this.rent_begin = rent_begin;
        }

        public String getRent_end() {
            return rent_end;
        }

        public void setRent_end(String rent_end) {
            this.rent_end = rent_end;
        }
    }

    public static class PropertyBean {
        /**
         * property_address : 发动机工况到付即可
         * area : 52
         * apartment : 2室一厅1卫
         * share_desc : 什么情况
         * used_type : 1
         * is_loan : 2
         */

        private String property_address;
        private int area;
        private String apartment;
        private String share_desc;
        private int used_type;
        private int is_loan;

        public String getProperty_address() {
            return property_address;
        }

        public void setProperty_address(String property_address) {
            this.property_address = property_address;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public String getApartment() {
            return apartment;
        }

        public void setApartment(String apartment) {
            this.apartment = apartment;
        }

        public String getShare_desc() {
            return share_desc;
        }

        public void setShare_desc(String share_desc) {
            this.share_desc = share_desc;
        }

        public int getUsed_type() {
            return used_type;
        }

        public void setUsed_type(int used_type) {
            this.used_type = used_type;
        }

        public int getIs_loan() {
            return is_loan;
        }

        public void setIs_loan(int is_loan) {
            this.is_loan = is_loan;
        }
    }

    public static class Property1Bean {
        /**
         * people_type : 0
         * card_type : 1
         * username : 哈皮
         * card_num : 123456789
         * card_begin : 2014-09-19
         * card_end : 2019-09-19
         * card_zpic : http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png
         * card_fpic : http://renting.liebianzhe.comhttp://thirdwx.qlogo.cn/mmopen/vi_32/zcFiaYibsHnfCib4fCYRcDWQKIDew7AibtEQAicz0sO6Q2ya6iaxUw5XtvEeCCxNncr8l8Pkq0YbYwWk4E0PHBST1FGw/132
         * property_type : 3
         * property_num : 315315615185
         * num_pic : http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png
         * home_pic : http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png
         * attach_pic : http://renting.liebianzhe.comhttp://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png
         * old_pic : http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png
         * household_pic : http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png
         * other_pic :
         */

        private int people_type;
        private int card_type;
        private String username;
        private String card_num;
        private String card_begin;
        private String card_end;
        private String card_zpic;
        private String card_fpic;
        private int property_type;
        private String property_num;
        private String num_pic;
        private String home_pic;
        private String attach_pic;
        private String old_pic;
        private String household_pic;
        private List<String> other_pic;

        public int getPeople_type() {
            return people_type;
        }

        public void setPeople_type(int people_type) {
            this.people_type = people_type;
        }

        public int getCard_type() {
            return card_type;
        }

        public void setCard_type(int card_type) {
            this.card_type = card_type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public String getCard_begin() {
            return card_begin;
        }

        public void setCard_begin(String card_begin) {
            this.card_begin = card_begin;
        }

        public String getCard_end() {
            return card_end;
        }

        public void setCard_end(String card_end) {
            this.card_end = card_end;
        }

        public String getCard_zpic() {
            return card_zpic;
        }

        public void setCard_zpic(String card_zpic) {
            this.card_zpic = card_zpic;
        }

        public String getCard_fpic() {
            return card_fpic;
        }

        public void setCard_fpic(String card_fpic) {
            this.card_fpic = card_fpic;
        }

        public int getProperty_type() {
            return property_type;
        }

        public void setProperty_type(int property_type) {
            this.property_type = property_type;
        }

        public String getProperty_num() {
            return property_num;
        }

        public void setProperty_num(String property_num) {
            this.property_num = property_num;
        }

        public String getNum_pic() {
            return num_pic;
        }

        public void setNum_pic(String num_pic) {
            this.num_pic = num_pic;
        }

        public String getHome_pic() {
            return home_pic;
        }

        public void setHome_pic(String home_pic) {
            this.home_pic = home_pic;
        }

        public String getAttach_pic() {
            return attach_pic;
        }

        public void setAttach_pic(String attach_pic) {
            this.attach_pic = attach_pic;
        }

        public String getOld_pic() {
            return old_pic;
        }

        public void setOld_pic(String old_pic) {
            this.old_pic = old_pic;
        }

        public String getHousehold_pic() {
            return household_pic;
        }

        public void setHousehold_pic(String household_pic) {
            this.household_pic = household_pic;
        }

        public List<String>  getOther_pic() {
            return other_pic;
        }

        public void setOther_pic(List<String> other_pic) {
            this.other_pic = other_pic;
        }
    }

    public static class Property2Bean {
        /**
         * people_type : 0
         * card_type : 1
         * username : 哈皮
         * card_num : 123456789
         * card_begin : 2014-09-19
         * card_end : 2019-09-19
         * card_zpic : http://renting.liebianzhe.com/static/upload/20190318/e46ac25556b7ca5dc56315b9ec9829c2.png
         * card_fpic : http://renting.liebianzhe.comhttp://thirdwx.qlogo.cn/mmopen/vi_32/zcFiaYibsHnfCib4fCYRcDWQKIDew7AibtEQAicz0sO6Q2ya6iaxUw5XtvEeCCxNncr8l8Pkq0YbYwWk4E0PHBST1FGw/132
         * property_num : 315315615185
         */

        private int people_type;
        private int card_type;
        private String username;
        private String card_num;
        private String card_begin;
        private String card_end;
        private String card_zpic;
        private String card_fpic;
        private String property_num;

        public int getPeople_type() {
            return people_type;
        }

        public void setPeople_type(int people_type) {
            this.people_type = people_type;
        }

        public int getCard_type() {
            return card_type;
        }

        public void setCard_type(int card_type) {
            this.card_type = card_type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public String getCard_begin() {
            return card_begin;
        }

        public void setCard_begin(String card_begin) {
            this.card_begin = card_begin;
        }

        public String getCard_end() {
            return card_end;
        }

        public void setCard_end(String card_end) {
            this.card_end = card_end;
        }

        public String getCard_zpic() {
            return card_zpic;
        }

        public void setCard_zpic(String card_zpic) {
            this.card_zpic = card_zpic;
        }

        public String getCard_fpic() {
            return card_fpic;
        }

        public void setCard_fpic(String card_fpic) {
            this.card_fpic = card_fpic;
        }

        public String getProperty_num() {
            return property_num;
        }

        public void setProperty_num(String property_num) {
            this.property_num = property_num;
        }
    }

    public static class OutPeopleBean {
        /**
         * card_type : 1
         * username : 傻呗
         * card_num : 123459698562625
         * phone : 18725125125
         * email_address : 1755212151@qq.com
         * comm_address : gfdgdfhdfhfdggdf
         */

        private int card_type;
        private String username;
        private String card_num;
        private String phone;
        private String email_address;
        private String comm_address;

        public int getCard_type() {
            return card_type;
        }

        public void setCard_type(int card_type) {
            this.card_type = card_type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail_address() {
            return email_address;
        }

        public void setEmail_address(String email_address) {
            this.email_address = email_address;
        }

        public String getComm_address() {
            return comm_address;
        }

        public void setComm_address(String comm_address) {
            this.comm_address = comm_address;
        }
    }

    public static class CommonPeopleBean {
        /**
         * card_type : 1
         * username : 傻呗
         * card_num : 123459698562625
         * phone : 18725125125
         * email_address : 1755212151@qq.com
         * comm_address : gfdgdfhdfhfdggdf
         */

        private int card_type;
        private String username;
        private String card_num;
        private String phone;
        private String email_address;
        private String comm_address;

        public int getCard_type() {
            return card_type;
        }

        public void setCard_type(int card_type) {
            this.card_type = card_type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail_address() {
            return email_address;
        }

        public void setEmail_address(String email_address) {
            this.email_address = email_address;
        }

        public String getComm_address() {
            return comm_address;
        }

        public void setComm_address(String comm_address) {
            this.comm_address = comm_address;
        }
    }

    public static class PayeeBean {
        /**
         * people_type : 0
         * card_type : 1
         * username : 各大公司的
         * card_num : 524124521112105
         * number_type : 2
         * number_num : 21452152
         * mechanism : 工商银行
         * branch : 更开放大陆鬼地方鬼地方
         */

        private int people_type;
        private int card_type;
        private String username;
        private String card_num;
        private int number_type;
        private String number_num;
        private String mechanism;
        private String branch;

        public int getPeople_type() {
            return people_type;
        }

        public void setPeople_type(int people_type) {
            this.people_type = people_type;
        }

        public int getCard_type() {
            return card_type;
        }

        public void setCard_type(int card_type) {
            this.card_type = card_type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public int getNumber_type() {
            return number_type;
        }

        public void setNumber_type(int number_type) {
            this.number_type = number_type;
        }

        public String getNumber_num() {
            return number_num;
        }

        public void setNumber_num(String number_num) {
            this.number_num = number_num;
        }

        public String getMechanism() {
            return mechanism;
        }

        public void setMechanism(String mechanism) {
            this.mechanism = mechanism;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }
    }

    public static class UrgentPeopleBean {
        /**
         * username : 股份大股东
         * phone : 15900000000
         */

        private String username;
        private String phone;

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
    }

    public static class StrategyBean {
        /**
         * entrust_cycle_begin : 2019-06-20
         * entrust_cycle_end : 1970-01-01
         * free_cycle_begin : 2020-06-20
         * free_cycle_end : 1970-01-01
         * rent_price : 2300.00
         */

        private String entrust_cycle_begin;
        private String entrust_cycle_end;
        private String free_cycle_begin;
        private String free_cycle_end;
        private String rent_price;

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
    }
}
