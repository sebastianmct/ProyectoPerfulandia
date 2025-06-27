package duoc.cl.PerfulandiaProject;

import duoc.cl.PerfulandiaProject.Controller.StockControllerV2;
import duoc.cl.PerfulandiaProject.Model.Stock;
import duoc.cl.PerfulandiaProject.Service.StockService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StockControllerV2.class)
public class StockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StockService stockService;

    @Test
    void testGetStock() throws Exception {
        Mockito.when(stockService.getStock(1, 1)).thenReturn("Cantidad disponible: 50");

        mockMvc.perform(get("/stock/1/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cantidad disponible: 50"));
    }

    @Test
    void testGetStock_NotFound() throws Exception {
        Mockito.when(stockService.getStock(999, 999)).thenReturn("Stock no encontrado");

        mockMvc.perform(get("/stock/999/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateStock() throws Exception {
        String json = """
                {
                    "productId": 1,
                    "ubicationId": 1,
                    "quantityDisponible": 50
                }
                """;

        Mockito.when(stockService.createStock(Mockito.any())).thenReturn("Stock creado correctamente");

        mockMvc.perform(post("/stock")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Stock creado"));
    }

    @Test
    void testCreateStock_Invalid() throws Exception {
        String json = """
                {
                    "productId": 1,
                    "ubicationId": 1,
                    "quantityDisponible": -10
                }
                """;

        Mockito.when(stockService.createStock(Mockito.any())).thenReturn("Error: cantidad inválida");

        mockMvc.perform(post("/stock")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: cantidad inválida"));
    }

    @Test
    void testUpdateStock() throws Exception {
        String json = """
                {
                    "productId": 1,
                    "ubicationId": 1,
                    "quantityDisponible": 100
                }
                """;

        Mockito.when(stockService.updateStock(Mockito.eq(1), Mockito.eq(1), Mockito.any()))
                .thenReturn("Stock actualizado correctamente");

        mockMvc.perform(put("/stock/1/1")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Stock actualizado"));
    }

    @Test
    void testDeleteStock() throws Exception {
        Mockito.when(stockService.deleteStock(1, 1)).thenReturn("Stock eliminado correctamente");

        mockMvc.perform(delete("/stock/1/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Stock eliminado"));
    }
}
