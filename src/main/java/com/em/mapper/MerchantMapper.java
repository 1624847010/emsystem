package com.em.mapper;

import com.em.vo.Merchant;
import com.em.vo.MerchantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    long countByExample(MerchantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    int deleteByExample(MerchantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    int insert(Merchant record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    int insertSelective(Merchant record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    List<Merchant> selectByExample(MerchantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    int updateByExampleSelective(@Param("record") Merchant record, @Param("example") MerchantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    int updateByExample(@Param("record") Merchant record, @Param("example") MerchantExample example);
}