package com.em.vo;

import java.io.Serializable;

public class Address implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.user_id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.contacts
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private String contacts;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.phone
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private long phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.sex
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private Integer sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.address
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.house_number
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private String houseNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.flag
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private Integer flag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.area_code
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private Integer areaCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table address
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.id
     *
     * @return the value of address.id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.id
     *
     * @param id the value for address.id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.user_id
     *
     * @return the value of address.user_id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.user_id
     *
     * @param userId the value for address.user_id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.contacts
     *
     * @return the value of address.contacts
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.contacts
     *
     * @param contacts the value for address.contacts
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.phone
     *
     * @return the value of address.phone
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public long getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.phone
     *
     * @param phone the value for address.phone
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setPhone(long phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.sex
     *
     * @return the value of address.sex
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.sex
     *
     * @param sex the value for address.sex
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.address
     *
     * @return the value of address.address
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.address
     *
     * @param address the value for address.address
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.house_number
     *
     * @return the value of address.house_number
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.house_number
     *
     * @param houseNumber the value for address.house_number
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.flag
     *
     * @return the value of address.flag
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.flag
     *
     * @param flag the value for address.flag
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.area_code
     *
     * @return the value of address.area_code
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public Integer getAreaCode() {
        return areaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.area_code
     *
     * @param areaCode the value for address.area_code
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }
}