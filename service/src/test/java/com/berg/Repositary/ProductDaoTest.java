package com.berg.Repositary;

import com.berg.entity.Product;
import com.berg.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
class ProductDaoTest extends IntegrationTestBase {

    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private Product product;

//    @BeforeEach
//    public void initDb() {
//        TestDataImporter.importData(entityManager);
//        product = EntityHelper.createProduct("product name", "product type");
//        entityManager.persist(product);
//    }
//
//    @Test
//    void saveProductTest() {
//        var savedProduct = Optional.ofNullable(entityManager.find(Product.class, product.getId()));
//        assertNotNull(savedProduct);
//        savedProduct.ifPresent(product1 -> assertThat(product1.getName()).isEqualTo(product.getName()));
//        savedProduct.ifPresent(product1 -> assertThat(product1.getType()).isEqualTo(product.getType()));
//    }
//
//    @Test
//    void deleteProductTest() {
//        var id = product.getId();
//        assertSame(product, entityManager.find(Product.class, id));
//        productRepository.delete(id);
//        var actualProduct = entityManager.find(Product.class, id);
//        assertNull(actualProduct);
//    }
//
//    @Test
//    void updateProductTest() {
////          var product = productRepository.findById(2L).get();
//        product.setName("test name");
//        product.setType("test type");
//        productRepository.update(product);
//        entityManager.flush();
//        var actualProduct = Optional.ofNullable(entityManager.find(Product.class, product.getId()));
//        assertTrue(actualProduct.isPresent());
//        actualProduct.ifPresent(product1 -> assertThat(product1.getName()).isEqualTo(product.getName()));
//        actualProduct.ifPresent(product1 -> assertThat(product1.getType()).isEqualTo(product.getType()));
//    }
//
//    @Test
//    void findByIdTest() {
//        var actualProduct = productRepository.findById(product.getId()).orElse(null);
//        assertThat(actualProduct).isNotNull();
//        assertThat(actualProduct.getId()).isEqualTo(product.getId());
//        assertThat(actualProduct.getName()).isEqualTo(product.getName());
//    }
}
