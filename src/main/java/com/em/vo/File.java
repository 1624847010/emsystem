package com.em.vo;

import java.io.Serializable;

public class File implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file.id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file.file_url
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private String fileUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file.file_id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private String fileId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file.business_id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private Integer businessId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file.business_type
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private Integer businessType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table file
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file.id
     *
     * @return the value of file.id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file.id
     *
     * @param id the value for file.id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file.file_url
     *
     * @return the value of file.file_url
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file.file_url
     *
     * @param fileUrl the value for file.file_url
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file.file_id
     *
     * @return the value of file.file_id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file.file_id
     *
     * @param fileId the value for file.file_id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file.business_id
     *
     * @return the value of file.business_id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file.business_id
     *
     * @param businessId the value for file.business_id
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file.business_type
     *
     * @return the value of file.business_type
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public Integer getBusinessType() {
        return businessType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file.business_type
     *
     * @param businessType the value for file.business_type
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
}