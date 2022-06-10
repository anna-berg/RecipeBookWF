package com.berg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
//
//    private final ProductRepository productRepository;
//    private final ProductCreateMapper productCreateMapper;
//    private final ProductReadMapper productReadMapper;
//
//    public Long create(ProductCreateDto productDto) {
//        var product = productCreateMapper.mapFrom(productDto);
//        return productRepository.save(product).getId();
//    }
//
//    public Optional<ProductReadDto> findById(Long id) {
//        return productRepository.findById(id)
//                .map(productReadMapper::mapFrom);
//    }
//
//    public boolean delete(Long id) {
//        var maybeProduct = productRepository.findById(id);
//        maybeProduct.ifPresent(p -> productRepository.delete(id));
//        return maybeProduct.isPresent();
//    }
//
//    public void update(Product product) {
//        productRepository.update(product);
//    }
//
//    public List<ProductReadDto> findAll() {
//        return productRepository.findAll().stream()
//                .map(productReadMapper::mapFrom)
//                .collect(toList());
//    }
}
