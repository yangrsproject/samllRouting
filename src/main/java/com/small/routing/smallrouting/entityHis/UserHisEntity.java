package com.small.routing.smallrouting.entityHis;

import java.sql.Timestamp;

public class UserHisEntity {
    /**
     * 用户id
     */
    private String user_id;
    /**
     * 用户唯一标识
     */
    private String open_id;
    /**
     * 用户昵称
     */
    private String nike_name;
    /**
     * 用户性别
     */
    private int gender;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 用户所在省份
     */
    private String province;
    /**
     * 用户所在国家
     */
    private String country;
    /**
     * 数据状态
     */
    private String data_State;
    /**
     * 操作时间
     */
    private Timestamp op_time;

    public UserHisEntity() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getNike_name() {
        return nike_name;
    }

    public void setNike_name(String nike_name) {
        this.nike_name = nike_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getData_State() {
        return data_State;
    }

    public void setData_State(String data_State) {
        this.data_State = data_State;
    }

    public Timestamp getOp_time() {
        return op_time;
    }

    public void setOp_time(Timestamp op_time) {
        this.op_time = op_time;
    }
}
