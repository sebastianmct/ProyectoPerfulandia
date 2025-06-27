package duoc.cl.PerfulandiaProject;

import duoc.cl.PerfulandiaProject.Controller.ProductControllerV2;
import duoc.cl.PerfulandiaProject.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductControllerV2.class)
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void testGetAllProducts() throws Exception {
        Mockito.when(productService.getAllProducts()).thenReturn("Lista de productos");

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string("Lista de productos"));
    }

    @Test
    void testGetProductById() throws Exception {
        Mockito.when(productService.getProductById(1)).thenReturn("Producto: Teléfono");

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto: Teléfono"));
    }

    @Test
    void testGetProductById_NotFound() throws Exception {
        Mockito.when(productService.getProductById(999)).thenReturn("Producto no encontrado");

        mockMvc.perform(get("/products/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddProduct() throws Exception {
        String json = """
                {
                    "productName": "Teléfono",
                    "productPrice": 150000,
                    "productDescription": "Smartphone gama media"
                }
                """;

        Mockito.when(productService.addProduct(Mockito.any())).thenReturn("Producto agregado correctamente");

        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto agregado correctamente"));
    }

    @Test
    void testAddProduct_Invalid() throws Exception {
        String json = """
                {
                    "productName": "",
                    "productPrice": -100,
                    "productDescription": ""
                }
                """;

        Mockito.when(productService.addProduct(Mockito.any())).thenReturn("Producto ya existe");

        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: datos inválidos"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        Mockito.when(productService.deleteProduct(1)).thenReturn("Producto eliminado correctamente");

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        String json = """
                {
                    "productName": "Teléfono Actualizado",
                    "productPrice": 180000,
                    "productDescription": "Smartphone actualizado"
                }
                """;

        Mockito.when(productService.updateProduct(Mockito.eq(1), Mockito.any()))
                .thenReturn("Producto actualizado correctamente");

        mockMvc.perform(put("/products/1")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto actualizado"));
    }

    @Test
    void testUpdateProduct_NotFound() throws Exception {
        String json = """
                {
                    "productName": "X",
                    "productPrice": 1000,
                    "productDescription": "desc"
                }
                """;

        Mockito.when(productService.updateProduct(Mockito.eq(999), Mockito.any()))
                .thenReturn("Producto no encontrado");

        mockMvc.perform(put("/products/999")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Producto no encontrado"));
    }
}
