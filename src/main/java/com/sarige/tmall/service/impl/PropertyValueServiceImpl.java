package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.PropertyValueMapper;
import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.pojo.Property;
import com.sarige.tmall.pojo.PropertyValue;
import com.sarige.tmall.service.PropertyService;
import com.sarige.tmall.service.PropertyValueService;
import com.sarige.tmall.util.example.PropertyValueExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Resource
    PropertyValueMapper propertyValueMapper;
    @Resource
    PropertyService propertyService;

    @Override
    public void init(Product product) {
        List<Property> propertyList = propertyService.list(product.getCategoryId());
        for (Property property : propertyList) {
            PropertyValue propertyValue = get(product.getId(), property.getId());
            if (propertyValue == null) {
                PropertyValue newProductValue = new PropertyValue();
                newProductValue.setProperty(property);
                newProductValue.setProductId(product.getId());
                newProductValue.setPropertyId(property.getId());
                propertyValueMapper.insert(newProductValue);
            }
        }
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }

    @Override
    public PropertyValue get(int productId, int propertyId) {
        PropertyValueExample propertyValueExample = new PropertyValueExample();
        propertyValueExample.or().andProductIdEqualTo(productId).andPropertyIdEqualTo(propertyId);
        List<PropertyValue> propertyValueList = propertyValueMapper.selectByExample(propertyValueExample);
        if (propertyValueList.isEmpty()) {
            return null;
        } else {
            return propertyValueList.get(0);
        }
    }

    @Override
    public List<PropertyValue> list(int productId) {
        PropertyValueExample propertyValueExample = new PropertyValueExample();
        propertyValueExample.or().andProductIdEqualTo(productId);
        List<PropertyValue> propertyValueList = propertyValueMapper.selectByExample(propertyValueExample);
        for (PropertyValue propertyValue : propertyValueList) {
            Integer propertyId = propertyValue.getPropertyId();
            Property property = propertyService.get(propertyId);
            propertyValue.setProperty(property);
        }
        return propertyValueList;
    }
}
