package duoc.cl.PerfulandiaProject;

import duoc.cl.PerfulandiaProject.Controller.SaleControllerV2;
import duoc.cl.PerfulandiaProject.Service.SaleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SaleControllerV2.class)
public class SaleTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaleService saleService;

    @Test
    void testGetAllSales() throws Exception {
        Mockito.when(saleService.getAllSales()).thenReturn("Ventas");

        mockMvc.perform(get("/sales"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ventas"));
    }

    @Test
    void testGetSaleById() throws Exception {
        Mockito.when(saleService.getSaleById(1)).thenReturn("Venta 1");

        mockMvc.perform(get("/sales/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Venta 1"));
    }

    @Test
    void testGetSaleById_NotFound() throws Exception {
        Mockito.when(saleService.getSaleById(999)).thenReturn(null);

        mockMvc.perform(get("/sales/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddSale() throws Exception {
        String json = """
                {
                    "clientId": 1,
                    "saleDate": "2024-06-22",
                    "saleTotal": 10000.0
                }
                """;

        Mockito.when(saleService.addSale(Mockito.any())).thenReturn("Venta registrada correctamente y evento disparado");

        mockMvc.perform(post("/sales")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Venta agregada"));
    }

    @Test
    void testAddSale_Invalid() throws Exception {
        String json = """
            {
                "clientId": 0,
                "saleDate": "2024-06-22",
                "saleTotal": -1000.0
            }
            """;

        Mockito.when(saleService.addSale(Mockito.any())).thenReturn("Error: datos inválidos");

        mockMvc.perform(post("/sales")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: datos inválidos"));
    }


    @Test
    void testUpdateSale() throws Exception {
        String json = """
                {
                    "clientId": 2,
                    "saleDate": "2024-07-01",
                    "saleTotal": 15000.0
                }
                """;

        Mockito.when(saleService.updateSale(Mockito.eq(1), Mockito.any())).thenReturn("Venta actualizada correctamente");

        mockMvc.perform(put("/sales/1")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Venta actualizada"));
    }

    @Test
    void testDeleteSale() throws Exception {
        Mockito.when(saleService.deleteSale(1)).thenReturn("Venta eliminada correctamente");

        mockMvc.perform(delete("/sales/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Venta eliminada"));
    }
}
