package com.ai.mine.crystal.dao.mapper;

import com.ai.mine.crystal.dao.model.TUserExtension;
import com.ai.mine.crystal.dao.model.TUserExtensionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserExtensionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_extension
     *
     * @mbg.generated Tue Oct 30 16:23:58 CST 2018
     */
    long countByExample(TUserExtensionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_extension
     *
     * @mbg.generated Tue Oct 30 16:23:58 CST 2018
     */
    int deleteByExample(TUserExtensionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_extension
     *
     * @mbg.generated Tue Oct 30 16:23:58 CST 2018
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_extension
     *
     * @mbg.generated Tue Oct 30 16:23:58 CST 2018
     */
    int insert(TUserExtension record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_extension
     *
     * @mbg.generated Tue Oct 30 16:23:58 CST 2018
     */
    int insertSelective(TUserExtension record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_extension
     *
     * @mbg.generated Tue Oct 30 16:23:58 CST 2018
     */
    List<TUserExtension> selectByExample(TUserExtensionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_extension
     *
     * @mbg.generated Tue Oct 30 16:23:58 CST 2018
     */
    TUserExtension selectByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_extension
     *
     * @mbg.generated Tue Oct 30 16:23:58 CST 2018
     */
    int updateByExampleSelective(@Param("record") TUserExtension record, @Param("example") TUserExtensionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_extension
     *
     * @mbg.generated Tue Oct 30 16:23:58 CST 2018
     */
    int updateByExample(@Param("record") TUserExtension record, @Param("example") TUserExtensionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_extension
     *
     * @mbg.generated Tue Oct 30 16:23:58 CST 2018
     */
    int updateByPrimaryKeySelective(TUserExtension record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_extension
     *
     * @mbg.generated Tue Oct 30 16:23:58 CST 2018
     */
    int updateByPrimaryKey(TUserExtension record);
}