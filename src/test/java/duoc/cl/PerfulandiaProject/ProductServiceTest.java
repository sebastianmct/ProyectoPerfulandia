package duoc.cl.PerfulandiaProject;

import duoc.cl.PerfulandiaProject.Model.Product;
import duoc.cl.PerfulandiaProject.Repository.ProductRepository;
import duoc.cl.PerfulandiaProject.Service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product1 = new Product();
        product1.setProductId(1);
        product1.setProductName("Perfume A");
        product1.setProductDescription("Descripcion A");
        product1.setProductPrice(10000);
    }

    @Test
    void testGetAllProducts_WithProducts() {
        List<Product> products = Arrays.asList(product1);
        when(productRepository.findAll()).thenReturn(products);

        String result = productService.getAllProducts();

        assertTrue(result.contains("ID Producto: 1"));
        assertTrue(result.contains("Nombre: Perfume A"));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById_Found() {
        when(productRepository.existsById(1)).thenReturn(true);
        when(productRepository.findById(1)).thenReturn(Optional.of(product1));

        String result = productService.getProductById(1);

        assertTrue(result.contains("ID Producto: 1"));
        assertTrue(result.contains("Nombre: Perfume A"));
        verify(productRepository).existsById(1);
        verify(productRepository).findById(1);
    }

    @Test
    void testAddProduct_Success() {
        when(productRepository.existsById(product1.getProductId())).thenReturn(false);

        String result = productService.addProduct(product1);

        assertEquals("Producto agregado correctamente", result);
        verify(productRepository).existsById(product1.getProductId());
        verify(productRepository).save(product1);
    }

    @Test
    void testDeleteProduct_Success() {
        when(productRepository.existsById(1)).thenReturn(true);

        String result = productService.deleteProduct(1);

        assertEquals("Producto eliminado correctamente", result);
        verify(productRepository).existsById(1);
        verify(productRepository).deleteById(1);
    }
}
