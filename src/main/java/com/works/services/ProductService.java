package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;
    final CacheManager cacheManager;

    public ResponseEntity save(Product product) {
        productRepository.save(product);
        cacheManager.getCache("product").clear();
        return Util.success(product);
    }

    @Cacheable("product")
    public ResponseEntity list() {
        List<Product> list = productRepository.findAll();
        return Util.success( list );
    }

    public ResponseEntity single( String stPid ) {
        try {
            long pid = Long.parseLong(stPid);
            Optional<Product> optionalProduct = productRepository.findById(pid);
            if (optionalProduct.isPresent()) {
                return Util.success(optionalProduct.get());
            }
        }catch (Exception ex) { }
        return Util.fail("Not Found", HttpStatus.BAD_REQUEST);
    }


}
