package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.PropertyvalueMapper;
import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.pojo.Property;
import com.sarige.tmall.pojo.Propertyvalue;
import com.sarige.tmall.service.PropertyService;
import com.sarige.tmall.service.PropertyValueService;
import com.sarige.tmall.util.example.PropertyvalueExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Resource
    PropertyvalueMapper propertyvalueMapper;
    @Resource
    PropertyService propertyService;

    @Override
    public void init(Product product) {
        List<Property> propertyList = propertyService.list(product.getCid());
        for (Property property : propertyList) {
            Propertyvalue propertyValue = get(product.getId(), property.getId());
            if (propertyValue == null) {
                Propertyvalue newProductValue = new Propertyvalue();
                newProductValue.setProperty(property);
                newProductValue.setPid(product.getId());
                newProductValue.setPtid(property.getId());
                propertyvalueMapper.insert(newProductValue);
            }
        }
    }

    @Override
    public void update(Propertyvalue propertyValue) {
        propertyvalueMapper.updateByPrimaryKeySelective(propertyValue);
    }

    @Override
    public Propertyvalue get(int productId, int propertyId) {
        PropertyvalueExample propertyValueExample = new PropertyvalueExample();
        propertyValueExample.or().andPidEqualTo(productId).andPtidEqualTo(propertyId);
        List<Propertyvalue> propertyValueList = propertyvalueMapper.selectByExample(propertyValueExample);
        if (propertyValueList.isEmpty()) {
            return null;
        } else {
            return propertyValueList.get(0);
        }
    }

    @Override
    public List<Propertyvalue> list(int productId) {
        PropertyvalueExample propertyValueExample = new PropertyvalueExample();
        propertyValueExample.or().andPidEqualTo(productId);
        List<Propertyvalue> propertyValueList = propertyvalueMapper.selectByExample(propertyValueExample);
        for (Propertyvalue propertyValue : propertyValueList) {
            Integer propertyId = propertyValue.getPtid();
            Property property = propertyService.get(propertyId);
            propertyValue.setProperty(property);
        }
        return propertyValueList;
    }
}
