package com.codeing.utils;

import com.codeing.dao.ProductDto;
import com.codeing.model.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static Product convertDtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    public static ProductDto convertEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

}
