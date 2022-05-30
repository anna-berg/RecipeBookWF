package com.berg.integration.dao;

import com.berg.entity.Product;
import com.berg.integration.IntegrationTestBase;
import com.berg.util.EntityHelper;
import com.berg.util.TestDataImporter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class ProductDaoTest extends IntegrationTestBase {

     private final EntityManager entityManager;
     private final ProductRepository productRepository;
     private Product product;

     @BeforeEach
     public void initDb() {
          TestDataImporter.importData(entityManager);
          product = EntityHelper.createProduct("product name", "product type");
          entityManager.persist(product);
     }

     @Test
     void saveProductTest(){
          var savedProduct = Optional.ofNullable(entityManager.find(Product.class, product.getId()));
          Assertions.assertNotNull(savedProduct);
          savedProduct.ifPresent(product1 -> assertThat(product1.getName()).isEqualTo(product.getName()));
          savedProduct.ifPresent(product1 -> assertThat(product1.getType()).isEqualTo(product.getType()));
     }

     @Test
     void deleteProductTest(){
          var id = product.getId();
          assertSame(product, entityManager.find(Product.class, id));
          productRepository.delete(id);
          var actualProduct = entityManager.find(Product.class, id);
          assertNull(actualProduct);
     }

     @Test
     void updateProductTest(){
//          var product = productRepository.findById(2L).get();
          product.setName("test name");
          product.setType("test type");
          productRepository.update(product);
          entityManager.flush();
          var actualProduct = Optional.ofNullable(entityManager.find(Product.class, product.getId()));
          assertTrue(actualProduct.isPresent());
          actualProduct.ifPresent(product1 -> assertThat(product1.getName()).isEqualTo(product.getName()));
          actualProduct.ifPresent(product1 -> assertThat(product1.getType()).isEqualTo(product.getType()));
     }

     @Test
     void findByIdTest(){
          var actualProduct = productRepository.findById(product.getId()).orElse(null);
          assertThat(actualProduct).isNotNull();
          assertThat(actualProduct.getId()).isEqualTo(product.getId());
          assertThat(actualProduct.getName()).isEqualTo(product.getName());
     }
}
