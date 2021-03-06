package com.sarige.tmall.mapper;

import com.sarige.tmall.pojo.ProductImage;
import com.sarige.tmall.util.example.ProductImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductImageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbg.generated
     */
    long countByExample(ProductImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbg.generated
     */
    int deleteByExample(ProductImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbg.generated
     */
    int insert(ProductImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbg.generated
     */
    int insertSelective(ProductImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbg.generated
     */
    List<ProductImage> selectByExample(ProductImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbg.generated
     */
    ProductImage selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ProductImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ProductImage record);
}