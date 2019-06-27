package luyuan.tech.com.chaoke.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2019/6/26
 * @describe:
 */


public class ChuZuDetailBean {

    /**
     * base_msg : {"address":"当前物业地址，当前物业地址，当前物业地址，当前物业地址，当前物业地址，当前物业地址","mode":"整租","cate":"新签","createtime":"2019-06-18","state":"无效"}
     * contract_msg : {"lessee_starttime":"2019/06/20","lessee_endtime":"2020/06/20","first_paytime":"2019/06/19","subtime":"2022/08/20","pay_type":"一次结清","payway":"年付","taxation":"租客","company":"巢客","identifier":"HZCK-ZK12000","side_letter":"补充条款，补充条款，补充条款，补充条款，补充条款，补充条款，补充条款"}
     * contract_bill : {"deposit":[{"price":"8000","pay_state":1,"cost_type":2,"billstart_time":"2019/06/20","billend_time":"2020/06/20"}],"first_rent":[{"price":"96000","pay_state":1,"cost_type":1,"billstart_time":"2019/06/20","billend_time":"2020/06/20","id":1}],"rent_list":[]}
     * lessee : {"username":"你爸爸","phone":"18988888888","sex":"女","age":18,"lessee_starttime":"2019/06/20"}
     * entrust : {"lessee_starttime":"2019/06/20","lessee_endtime":"2020/06/20","identifier":"HZCK-ZK12000","side_letter":"补充条款，补充条款，补充条款，补充条款，补充条款，补充条款，补充条款"}
     * role : [{"username":"小马","phone":"小马"},{"username":"你爸爸","phone":"18988888888"},{"username":"打爆你狗头","phone":"18723836080"}]
     */

    private BaseMsgBean base_msg;
    private ContractMsgBean contract_msg;
    private ContractBillBean contract_bill;
    private LesseeBean lessee;
    private EntrustBean entrust;
    private List<RoleBean> role;

    public BaseMsgBean getBase_msg() {
        return base_msg;
    }

    public void setBase_msg(BaseMsgBean base_msg) {
        this.base_msg = base_msg;
    }

    public ContractMsgBean getContract_msg() {
        return contract_msg;
    }

    public void setContract_msg(ContractMsgBean contract_msg) {
        this.contract_msg = contract_msg;
    }

    public ContractBillBean getContract_bill() {
        return contract_bill;
    }

    public void setContract_bill(ContractBillBean contract_bill) {
        this.contract_bill = contract_bill;
    }

    public LesseeBean getLessee() {
        return lessee;
    }

    public void setLessee(LesseeBean lessee) {
        this.lessee = lessee;
    }

    public EntrustBean getEntrust() {
        return entrust;
    }

    public void setEntrust(EntrustBean entrust) {
        this.entrust = entrust;
    }

    public List<RoleBean> getRole() {
        return role;
    }

    public void setRole(List<RoleBean> role) {
        this.role = role;
    }

    public static class BaseMsgBean {
        /**
         * address : 当前物业地址，当前物业地址，当前物业地址，当前物业地址，当前物业地址，当前物业地址
         * mode : 整租
         * cate : 新签
         * createtime : 2019-06-18
         * state : 无效
         */

        private String address;
        private String mode;
        private String cate;
        private String createtime;
        private String state;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getCate() {
            return cate;
        }

        public void setCate(String cate) {
            this.cate = cate;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    public static class ContractMsgBean {
        /**
         * lessee_starttime : 2019/06/20
         * lessee_endtime : 2020/06/20
         * first_paytime : 2019/06/19
         * subtime : 2022/08/20
         * pay_type : 一次结清
         * payway : 年付
         * taxation : 租客
         * company : 巢客
         * identifier : HZCK-ZK12000
         * side_letter : 补充条款，补充条款，补充条款，补充条款，补充条款，补充条款，补充条款
         */

        private String lessee_starttime;
        private String lessee_endtime;
        private String first_paytime;
        private String subtime;
        private String pay_type;
        private String payway;
        private String taxation;
        private String company;
        private String identifier;
        private String side_letter;

        public String getLessee_starttime() {
            return lessee_starttime;
        }

        public void setLessee_starttime(String lessee_starttime) {
            this.lessee_starttime = lessee_starttime;
        }

        public String getLessee_endtime() {
            return lessee_endtime;
        }

        public void setLessee_endtime(String lessee_endtime) {
            this.lessee_endtime = lessee_endtime;
        }

        public String getFirst_paytime() {
            return first_paytime;
        }

        public void setFirst_paytime(String first_paytime) {
            this.first_paytime = first_paytime;
        }

        public String getSubtime() {
            return subtime;
        }

        public void setSubtime(String subtime) {
            this.subtime = subtime;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getPayway() {
            return payway;
        }

        public void setPayway(String payway) {
            this.payway = payway;
        }

        public String getTaxation() {
            return taxation;
        }

        public void setTaxation(String taxation) {
            this.taxation = taxation;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getSide_letter() {
            return side_letter;
        }

        public void setSide_letter(String side_letter) {
            this.side_letter = side_letter;
        }
    }

    public static class ContractBillBean {
        private List<DepositBean> deposit;
        private List<FirstRentBean> first_rent;
        private List<?> rent_list;

        public List<DepositBean> getDeposit() {
            return deposit;
        }

        public void setDeposit(List<DepositBean> deposit) {
            this.deposit = deposit;
        }

        public List<FirstRentBean> getFirst_rent() {
            return first_rent;
        }

        public void setFirst_rent(List<FirstRentBean> first_rent) {
            this.first_rent = first_rent;
        }

        public List<?> getRent_list() {
            return rent_list;
        }

        public void setRent_list(List<?> rent_list) {
            this.rent_list = rent_list;
        }

        public static class DepositBean {
            /**
             * price : 8000
             * pay_state : 1
             * cost_type : 2
             * billstart_time : 2019/06/20
             * billend_time : 2020/06/20
             */

            private String price;
            private int pay_state;
            private int cost_type;
            private String billstart_time;
            private String billend_time;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getPay_state() {
                return pay_state;
            }

            public void setPay_state(int pay_state) {
                this.pay_state = pay_state;
            }

            public int getCost_type() {
                return cost_type;
            }

            public void setCost_type(int cost_type) {
                this.cost_type = cost_type;
            }

            public String getBillstart_time() {
                return billstart_time;
            }

            public void setBillstart_time(String billstart_time) {
                this.billstart_time = billstart_time;
            }

            public String getBillend_time() {
                return billend_time;
            }

            public void setBillend_time(String billend_time) {
                this.billend_time = billend_time;
            }
        }

        public static class FirstRentBean {
            /**
             * price : 96000
             * pay_state : 1
             * cost_type : 1
             * billstart_time : 2019/06/20
             * billend_time : 2020/06/20
             * id : 1
             */

            private String price;
            private int pay_state;
            private int cost_type;
            private String billstart_time;
            private String billend_time;
            private int id;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getPay_state() {
                return pay_state;
            }

            public void setPay_state(int pay_state) {
                this.pay_state = pay_state;
            }

            public int getCost_type() {
                return cost_type;
            }

            public void setCost_type(int cost_type) {
                this.cost_type = cost_type;
            }

            public String getBillstart_time() {
                return billstart_time;
            }

            public void setBillstart_time(String billstart_time) {
                this.billstart_time = billstart_time;
            }

            public String getBillend_time() {
                return billend_time;
            }

            public void setBillend_time(String billend_time) {
                this.billend_time = billend_time;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }

    public static class LesseeBean {
        /**
         * username : 你爸爸
         * phone : 18988888888
         * sex : 女
         * age : 18
         * lessee_starttime : 2019/06/20
         */

        private String username;
        private String phone;
        private String sex;
        private int age;
        private String lessee_starttime;

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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getLessee_starttime() {
            return lessee_starttime;
        }

        public void setLessee_starttime(String lessee_starttime) {
            this.lessee_starttime = lessee_starttime;
        }
    }

    public static class EntrustBean {
        /**
         * lessee_starttime : 2019/06/20
         * lessee_endtime : 2020/06/20
         * identifier : HZCK-ZK12000
         * side_letter : 补充条款，补充条款，补充条款，补充条款，补充条款，补充条款，补充条款
         */

        private String lessee_starttime;
        private String lessee_endtime;
        private String identifier;
        private String side_letter;

        public String getLessee_starttime() {
            return lessee_starttime;
        }

        public void setLessee_starttime(String lessee_starttime) {
            this.lessee_starttime = lessee_starttime;
        }

        public String getLessee_endtime() {
            return lessee_endtime;
        }

        public void setLessee_endtime(String lessee_endtime) {
            this.lessee_endtime = lessee_endtime;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getSide_letter() {
            return side_letter;
        }

        public void setSide_letter(String side_letter) {
            this.side_letter = side_letter;
        }
    }

    public static class RoleBean {
        /**
         * username : 小马
         * phone : 小马
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
}
