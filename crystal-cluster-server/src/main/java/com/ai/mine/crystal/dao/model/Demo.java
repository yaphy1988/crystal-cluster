package com.ai.mine.crystal.dao.model;

import java.util.Date;

public class Demo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo.id
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo.name
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo.create_time
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo.memo
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    private String memo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo.id
     *
     * @return the value of demo.id
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo.id
     *
     * @param id the value for demo.id
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo.name
     *
     * @return the value of demo.name
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo.name
     *
     * @param name the value for demo.name
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo.create_time
     *
     * @return the value of demo.create_time
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo.create_time
     *
     * @param createTime the value for demo.create_time
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo.memo
     *
     * @return the value of demo.memo
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo.memo
     *
     * @param memo the value for demo.memo
     *
     * @mbg.generated Wed Jun 27 18:04:09 CST 2018
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}