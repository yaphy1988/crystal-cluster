package com.ai.mine.crystal.dao.mapper;

import com.ai.mine.crystal.dao.model.TKedaRecord;
import com.ai.mine.crystal.dao.model.TKedaRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TKedaRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_record
     *
     * @mbg.generated Thu Jun 13 15:34:29 CST 2019
     */
    long countByExample(TKedaRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_record
     *
     * @mbg.generated Thu Jun 13 15:34:29 CST 2019
     */
    int deleteByExample(TKedaRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_record
     *
     * @mbg.generated Thu Jun 13 15:34:29 CST 2019
     */
    int deleteByPrimaryKey(String recordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_record
     *
     * @mbg.generated Thu Jun 13 15:34:29 CST 2019
     */
    int insert(TKedaRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_record
     *
     * @mbg.generated Thu Jun 13 15:34:29 CST 2019
     */
    int insertSelective(TKedaRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_record
     *
     * @mbg.generated Thu Jun 13 15:34:29 CST 2019
     */
    List<TKedaRecord> selectByExample(TKedaRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_record
     *
     * @mbg.generated Thu Jun 13 15:34:29 CST 2019
     */
    TKedaRecord selectByPrimaryKey(String recordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_record
     *
     * @mbg.generated Thu Jun 13 15:34:29 CST 2019
     */
    int updateByExampleSelective(@Param("record") TKedaRecord record, @Param("example") TKedaRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_record
     *
     * @mbg.generated Thu Jun 13 15:34:29 CST 2019
     */
    int updateByExample(@Param("record") TKedaRecord record, @Param("example") TKedaRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_record
     *
     * @mbg.generated Thu Jun 13 15:34:29 CST 2019
     */
    int updateByPrimaryKeySelective(TKedaRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_record
     *
     * @mbg.generated Thu Jun 13 15:34:29 CST 2019
     */
    int updateByPrimaryKey(TKedaRecord record);
}