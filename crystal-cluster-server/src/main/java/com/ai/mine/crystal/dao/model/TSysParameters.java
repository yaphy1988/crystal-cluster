package com.ai.mine.crystal.dao.model;

import java.util.Date;

public class TSysParameters {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_parameters.param_code
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    private String paramCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_parameters.param_name
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    private String paramName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_parameters.param_value
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    private String paramValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_parameters.description
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_parameters.create_time
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_parameters.update_time
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_parameters.param_code
     *
     * @return the value of t_sys_parameters.param_code
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_parameters.param_code
     *
     * @param paramCode the value for t_sys_parameters.param_code
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_parameters.param_name
     *
     * @return the value of t_sys_parameters.param_name
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_parameters.param_name
     *
     * @param paramName the value for t_sys_parameters.param_name
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_parameters.param_value
     *
     * @return the value of t_sys_parameters.param_value
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_parameters.param_value
     *
     * @param paramValue the value for t_sys_parameters.param_value
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_parameters.description
     *
     * @return the value of t_sys_parameters.description
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_parameters.description
     *
     * @param description the value for t_sys_parameters.description
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_parameters.create_time
     *
     * @return the value of t_sys_parameters.create_time
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_parameters.create_time
     *
     * @param createTime the value for t_sys_parameters.create_time
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_parameters.update_time
     *
     * @return the value of t_sys_parameters.update_time
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_parameters.update_time
     *
     * @param updateTime the value for t_sys_parameters.update_time
     *
     * @mbg.generated Thu Jun 13 22:56:15 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}