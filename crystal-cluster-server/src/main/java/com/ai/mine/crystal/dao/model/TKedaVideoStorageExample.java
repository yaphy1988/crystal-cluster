package com.ai.mine.crystal.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TKedaVideoStorageExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    public TKedaVideoStorageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andVideoStorageIdIsNull() {
            addCriterion("video_storage_id is null");
            return (Criteria) this;
        }

        public Criteria andVideoStorageIdIsNotNull() {
            addCriterion("video_storage_id is not null");
            return (Criteria) this;
        }

        public Criteria andVideoStorageIdEqualTo(Long value) {
            addCriterion("video_storage_id =", value, "videoStorageId");
            return (Criteria) this;
        }

        public Criteria andVideoStorageIdNotEqualTo(Long value) {
            addCriterion("video_storage_id <>", value, "videoStorageId");
            return (Criteria) this;
        }

        public Criteria andVideoStorageIdGreaterThan(Long value) {
            addCriterion("video_storage_id >", value, "videoStorageId");
            return (Criteria) this;
        }

        public Criteria andVideoStorageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("video_storage_id >=", value, "videoStorageId");
            return (Criteria) this;
        }

        public Criteria andVideoStorageIdLessThan(Long value) {
            addCriterion("video_storage_id <", value, "videoStorageId");
            return (Criteria) this;
        }

        public Criteria andVideoStorageIdLessThanOrEqualTo(Long value) {
            addCriterion("video_storage_id <=", value, "videoStorageId");
            return (Criteria) this;
        }

        public Criteria andVideoStorageIdIn(List<Long> values) {
            addCriterion("video_storage_id in", values, "videoStorageId");
            return (Criteria) this;
        }

        public Criteria andVideoStorageIdNotIn(List<Long> values) {
            addCriterion("video_storage_id not in", values, "videoStorageId");
            return (Criteria) this;
        }

        public Criteria andVideoStorageIdBetween(Long value1, Long value2) {
            addCriterion("video_storage_id between", value1, value2, "videoStorageId");
            return (Criteria) this;
        }

        public Criteria andVideoStorageIdNotBetween(Long value1, Long value2) {
            addCriterion("video_storage_id not between", value1, value2, "videoStorageId");
            return (Criteria) this;
        }

        public Criteria andStoragePathIsNull() {
            addCriterion("storage_path is null");
            return (Criteria) this;
        }

        public Criteria andStoragePathIsNotNull() {
            addCriterion("storage_path is not null");
            return (Criteria) this;
        }

        public Criteria andStoragePathEqualTo(String value) {
            addCriterion("storage_path =", value, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStoragePathNotEqualTo(String value) {
            addCriterion("storage_path <>", value, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStoragePathGreaterThan(String value) {
            addCriterion("storage_path >", value, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStoragePathGreaterThanOrEqualTo(String value) {
            addCriterion("storage_path >=", value, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStoragePathLessThan(String value) {
            addCriterion("storage_path <", value, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStoragePathLessThanOrEqualTo(String value) {
            addCriterion("storage_path <=", value, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStoragePathLike(String value) {
            addCriterion("storage_path like", value, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStoragePathNotLike(String value) {
            addCriterion("storage_path not like", value, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStoragePathIn(List<String> values) {
            addCriterion("storage_path in", values, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStoragePathNotIn(List<String> values) {
            addCriterion("storage_path not in", values, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStoragePathBetween(String value1, String value2) {
            addCriterion("storage_path between", value1, value2, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStoragePathNotBetween(String value1, String value2) {
            addCriterion("storage_path not between", value1, value2, "storagePath");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameIsNull() {
            addCriterion("storage_filename is null");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameIsNotNull() {
            addCriterion("storage_filename is not null");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameEqualTo(String value) {
            addCriterion("storage_filename =", value, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameNotEqualTo(String value) {
            addCriterion("storage_filename <>", value, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameGreaterThan(String value) {
            addCriterion("storage_filename >", value, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameGreaterThanOrEqualTo(String value) {
            addCriterion("storage_filename >=", value, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameLessThan(String value) {
            addCriterion("storage_filename <", value, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameLessThanOrEqualTo(String value) {
            addCriterion("storage_filename <=", value, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameLike(String value) {
            addCriterion("storage_filename like", value, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameNotLike(String value) {
            addCriterion("storage_filename not like", value, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameIn(List<String> values) {
            addCriterion("storage_filename in", values, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameNotIn(List<String> values) {
            addCriterion("storage_filename not in", values, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameBetween(String value1, String value2) {
            addCriterion("storage_filename between", value1, value2, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andStorageFilenameNotBetween(String value1, String value2) {
            addCriterion("storage_filename not between", value1, value2, "storageFilename");
            return (Criteria) this;
        }

        public Criteria andVideoUrlIsNull() {
            addCriterion("video_url is null");
            return (Criteria) this;
        }

        public Criteria andVideoUrlIsNotNull() {
            addCriterion("video_url is not null");
            return (Criteria) this;
        }

        public Criteria andVideoUrlEqualTo(String value) {
            addCriterion("video_url =", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotEqualTo(String value) {
            addCriterion("video_url <>", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlGreaterThan(String value) {
            addCriterion("video_url >", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("video_url >=", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlLessThan(String value) {
            addCriterion("video_url <", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlLessThanOrEqualTo(String value) {
            addCriterion("video_url <=", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlLike(String value) {
            addCriterion("video_url like", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotLike(String value) {
            addCriterion("video_url not like", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlIn(List<String> values) {
            addCriterion("video_url in", values, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotIn(List<String> values) {
            addCriterion("video_url not in", values, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlBetween(String value1, String value2) {
            addCriterion("video_url between", value1, value2, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotBetween(String value1, String value2) {
            addCriterion("video_url not between", value1, value2, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNull() {
            addCriterion("record_id is null");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("record_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecordIdEqualTo(String value) {
            addCriterion("record_id =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(String value) {
            addCriterion("record_id <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(String value) {
            addCriterion("record_id >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(String value) {
            addCriterion("record_id >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(String value) {
            addCriterion("record_id <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(String value) {
            addCriterion("record_id <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLike(String value) {
            addCriterion("record_id like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotLike(String value) {
            addCriterion("record_id not like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<String> values) {
            addCriterion("record_id in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<String> values) {
            addCriterion("record_id not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(String value1, String value2) {
            addCriterion("record_id between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(String value1, String value2) {
            addCriterion("record_id not between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andCaseIdIsNull() {
            addCriterion("case_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseIdIsNotNull() {
            addCriterion("case_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseIdEqualTo(String value) {
            addCriterion("case_id =", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotEqualTo(String value) {
            addCriterion("case_id <>", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThan(String value) {
            addCriterion("case_id >", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThanOrEqualTo(String value) {
            addCriterion("case_id >=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThan(String value) {
            addCriterion("case_id <", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThanOrEqualTo(String value) {
            addCriterion("case_id <=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLike(String value) {
            addCriterion("case_id like", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotLike(String value) {
            addCriterion("case_id not like", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdIn(List<String> values) {
            addCriterion("case_id in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotIn(List<String> values) {
            addCriterion("case_id not in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdBetween(String value1, String value2) {
            addCriterion("case_id between", value1, value2, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotBetween(String value1, String value2) {
            addCriterion("case_id not between", value1, value2, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseNameIsNull() {
            addCriterion("case_name is null");
            return (Criteria) this;
        }

        public Criteria andCaseNameIsNotNull() {
            addCriterion("case_name is not null");
            return (Criteria) this;
        }

        public Criteria andCaseNameEqualTo(String value) {
            addCriterion("case_name =", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameNotEqualTo(String value) {
            addCriterion("case_name <>", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameGreaterThan(String value) {
            addCriterion("case_name >", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("case_name >=", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameLessThan(String value) {
            addCriterion("case_name <", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameLessThanOrEqualTo(String value) {
            addCriterion("case_name <=", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameLike(String value) {
            addCriterion("case_name like", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameNotLike(String value) {
            addCriterion("case_name not like", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameIn(List<String> values) {
            addCriterion("case_name in", values, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameNotIn(List<String> values) {
            addCriterion("case_name not in", values, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameBetween(String value1, String value2) {
            addCriterion("case_name between", value1, value2, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameNotBetween(String value1, String value2) {
            addCriterion("case_name not between", value1, value2, "caseName");
            return (Criteria) this;
        }

        public Criteria andAskerIsNull() {
            addCriterion("asker is null");
            return (Criteria) this;
        }

        public Criteria andAskerIsNotNull() {
            addCriterion("asker is not null");
            return (Criteria) this;
        }

        public Criteria andAskerEqualTo(String value) {
            addCriterion("asker =", value, "asker");
            return (Criteria) this;
        }

        public Criteria andAskerNotEqualTo(String value) {
            addCriterion("asker <>", value, "asker");
            return (Criteria) this;
        }

        public Criteria andAskerGreaterThan(String value) {
            addCriterion("asker >", value, "asker");
            return (Criteria) this;
        }

        public Criteria andAskerGreaterThanOrEqualTo(String value) {
            addCriterion("asker >=", value, "asker");
            return (Criteria) this;
        }

        public Criteria andAskerLessThan(String value) {
            addCriterion("asker <", value, "asker");
            return (Criteria) this;
        }

        public Criteria andAskerLessThanOrEqualTo(String value) {
            addCriterion("asker <=", value, "asker");
            return (Criteria) this;
        }

        public Criteria andAskerLike(String value) {
            addCriterion("asker like", value, "asker");
            return (Criteria) this;
        }

        public Criteria andAskerNotLike(String value) {
            addCriterion("asker not like", value, "asker");
            return (Criteria) this;
        }

        public Criteria andAskerIn(List<String> values) {
            addCriterion("asker in", values, "asker");
            return (Criteria) this;
        }

        public Criteria andAskerNotIn(List<String> values) {
            addCriterion("asker not in", values, "asker");
            return (Criteria) this;
        }

        public Criteria andAskerBetween(String value1, String value2) {
            addCriterion("asker between", value1, value2, "asker");
            return (Criteria) this;
        }

        public Criteria andAskerNotBetween(String value1, String value2) {
            addCriterion("asker not between", value1, value2, "asker");
            return (Criteria) this;
        }

        public Criteria andPersionIdIsNull() {
            addCriterion("persion_id is null");
            return (Criteria) this;
        }

        public Criteria andPersionIdIsNotNull() {
            addCriterion("persion_id is not null");
            return (Criteria) this;
        }

        public Criteria andPersionIdEqualTo(String value) {
            addCriterion("persion_id =", value, "persionId");
            return (Criteria) this;
        }

        public Criteria andPersionIdNotEqualTo(String value) {
            addCriterion("persion_id <>", value, "persionId");
            return (Criteria) this;
        }

        public Criteria andPersionIdGreaterThan(String value) {
            addCriterion("persion_id >", value, "persionId");
            return (Criteria) this;
        }

        public Criteria andPersionIdGreaterThanOrEqualTo(String value) {
            addCriterion("persion_id >=", value, "persionId");
            return (Criteria) this;
        }

        public Criteria andPersionIdLessThan(String value) {
            addCriterion("persion_id <", value, "persionId");
            return (Criteria) this;
        }

        public Criteria andPersionIdLessThanOrEqualTo(String value) {
            addCriterion("persion_id <=", value, "persionId");
            return (Criteria) this;
        }

        public Criteria andPersionIdLike(String value) {
            addCriterion("persion_id like", value, "persionId");
            return (Criteria) this;
        }

        public Criteria andPersionIdNotLike(String value) {
            addCriterion("persion_id not like", value, "persionId");
            return (Criteria) this;
        }

        public Criteria andPersionIdIn(List<String> values) {
            addCriterion("persion_id in", values, "persionId");
            return (Criteria) this;
        }

        public Criteria andPersionIdNotIn(List<String> values) {
            addCriterion("persion_id not in", values, "persionId");
            return (Criteria) this;
        }

        public Criteria andPersionIdBetween(String value1, String value2) {
            addCriterion("persion_id between", value1, value2, "persionId");
            return (Criteria) this;
        }

        public Criteria andPersionIdNotBetween(String value1, String value2) {
            addCriterion("persion_id not between", value1, value2, "persionId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated do_not_delete_during_merge Thu Jun 13 14:37:16 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_keda_video_storage
     *
     * @mbg.generated Thu Jun 13 14:37:16 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}