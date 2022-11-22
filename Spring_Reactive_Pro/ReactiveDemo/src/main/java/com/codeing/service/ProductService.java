package com.codeing.service;

import com.codeing.dao.ProductDto;
import com.codeing.repository.ProductRepository;
import com.codeing.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux< ProductDto > getAllProduct(){
       return productRepository.findAll().map(AppUtils::convertEntityToDto);
    }

    public Mono<ProductDto>  getProductById(String id){
     return productRepository.findById(id).map(AppUtils::convertEntityToDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDto){
      return   productDto.map(AppUtils::convertDtoToEntity)
                .flatMap(productRepository::save)
                .map(AppUtils::convertEntityToDto);
//     return productRepository.save(AppUtils.convertDtoToEntity(productDto)).map(AppUtils::convertEntityToDto);
    }

    public Mono<ProductDto> upDateProduct(Mono<ProductDto> productDto,String id){
       return productRepository.findById(id)
                .flatMap(p-> productDto.map(AppUtils::convertDtoToEntity))
                .doOnNext(e-> e.setId(id))
                .flatMap(productRepository::save)
                .map(AppUtils::convertEntityToDto);
    }

    public void deleteProduct(String id){
        productRepository.deleteById(id);
    }

}
