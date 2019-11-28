package com.em.mapper;

import com.em.vo.Orderfrom;
import com.em.vo.OrderfromExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderfromMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderfrom
     *
     * @mbg.generated Mon Nov 25 14:59:52 CST 2019
     */
    long countByExample(OrderfromExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderfrom
     *
     * @mbg.generated Mon Nov 25 14:59:52 CST 2019
     */
    int deleteByExample(OrderfromExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderfrom
     *
     * @mbg.generated Mon Nov 25 14:59:52 CST 2019
     */
    int insert(Orderfrom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderfrom
     *
     * @mbg.generated Mon Nov 25 14:59:52 CST 2019
     */
    int insertSelective(Orderfrom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderfrom
     *
     * @mbg.generated Mon Nov 25 14:59:52 CST 2019
     */
    List<Orderfrom> selectByExample(OrderfromExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderfrom
     *
     * @mbg.generated Mon Nov 25 14:59:52 CST 2019
     */
    int updateByExampleSelective(@Param("record") Orderfrom record, @Param("example") OrderfromExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderfrom
     *
     * @mbg.generated Mon Nov 25 14:59:52 CST 2019
     */
    int updateByExample(@Param("record") Orderfrom record, @Param("example") OrderfromExample example);
}