package com.berg.service;

import com.berg.dto.ProductCreateDto;
import com.berg.dto.ProductReadDto;
import com.berg.mapper.ProductCreateDtoToProductMapper;
import com.berg.mapper.ProductToProductReadDtoMapper;
import com.berg.repositary.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductToProductReadDtoMapper productToProductReadDtoMapper;
    private final ProductCreateDtoToProductMapper productCreateDtoToProductMapper;

    public List<ProductReadDto> findAll() {
        return productRepository.findAll().stream()
                .map(productToProductReadDtoMapper::map)
                .toList();
    }

    public Optional<ProductReadDto> findById(Long id) {
        return productRepository.findById(id)
                .map(productToProductReadDtoMapper::map);
    }

    @Transactional
    public ProductReadDto create(ProductCreateDto product) {
        return Optional.of(product)
                .map(productCreateDtoToProductMapper::map)
                .map(productRepository::save)
                .map(productToProductReadDtoMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ProductReadDto> update(Long id, ProductCreateDto productCreateDto) {
        return productRepository.findById(id)
                .map(product -> productCreateDtoToProductMapper.map(productCreateDto, product))
                .map(productRepository::saveAndFlush)
                .map(productToProductReadDtoMapper::map);
    }

    public boolean delete(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    productRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
