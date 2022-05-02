package com.berg.service;

import com.berg.dao.ProductRepository;
import com.berg.dto.ProductCreateDto;
import com.berg.dto.ProductReadDto;
import com.berg.entity.Product;
import com.berg.mapper.ProductCreateMapper;
import com.berg.mapper.ProductReadMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCreateMapper productCreateMapper;
    private final ProductReadMapper productReadMapper;

    public Long create(ProductCreateDto productDto) {
        var product = productCreateMapper.mapFrom(productDto);
        return productRepository.save(product).getId();
    }

    public Optional<ProductReadDto> findById(Long id) {
        return productRepository.findById(id)
                .map(productReadMapper::mapFrom);
    }

    public boolean delete(Long id) {
        var maybeProduct = productRepository.findById(id);
        maybeProduct.ifPresent(p -> productRepository.delete(id));
        return maybeProduct.isPresent();
    }

    public void update(Product product) {
        productRepository.update(product);
    }

    public List<ProductReadDto> findAll() {
        return productRepository.findAll().stream()
                .map(productReadMapper::mapFrom)
                .collect(toList());
    }
}
