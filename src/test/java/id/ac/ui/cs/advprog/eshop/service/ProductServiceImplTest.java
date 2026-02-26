package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product);
        Product savedProduct = productService.create(product);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProduct() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> allProducts = productService.findAll();
        assertFalse(allProducts.isEmpty());
        assertEquals(1, allProducts.size());
    }

    @Test
    void testFindByIdProduct() {
        when(productRepository.findById(product.getProductId())).thenReturn(product);
        Product foundProduct = productService.findById(product.getProductId());
        assertEquals(product.getProductId(), foundProduct.getProductId());
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.update(product)).thenReturn(product);
        Product updatedProduct = productService.update(product.getProductId(), product);
        assertEquals(product.getProductId(), updatedProduct.getProductId());
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete(product.getProductId());
        productService.delete(product.getProductId());
        verify(productRepository, times(1)).delete(product.getProductId());
    }
}