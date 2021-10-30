package com.sarige.tmall.mapper;

import com.sarige.tmall.pojo.ProductImage;import com.sarige.tmall.util.example.ProductImageExample;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface ProductImageMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(ProductImage record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(ProductImage record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    ProductImage selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ProductImage record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ProductImage record);

    long countByExample(ProductImageExample example);

    int deleteByExample(ProductImageExample example);

    List<ProductImage> selectByExample(ProductImageExample example);

    int updateByExampleSelective(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    int updateByExample(@Param("record") ProductImage record, @Param("example") ProductImageExample example);
}