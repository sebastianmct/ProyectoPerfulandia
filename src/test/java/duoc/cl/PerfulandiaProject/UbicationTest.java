package duoc.cl.PerfulandiaProject;

import duoc.cl.PerfulandiaProject.Service.UbicationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UbicationTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UbicationService ubicationService;

    @Test
    void testGetAllUbications() throws Exception {
        Mockito.when(ubicationService.getAllUbications()).thenReturn("Ubicaciones");

        mockMvc.perform(get("/ubications"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ubicaciones"));
    }

    @Test
    void testGetUbicationById() throws Exception {
        Mockito.when(ubicationService.getUbicationById(1)).thenReturn("Bodega Central");

        mockMvc.perform(get("/ubications/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Bodega Central"));
    }

    @Test
    void testGetUbicationById_NotFound() throws Exception {
        Mockito.when(ubicationService.getUbicationById(999)).thenReturn(null);

        mockMvc.perform(get("/ubications/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddUbication() throws Exception {
        String json = """
                {
                    "ubicationName": "Sucursal Norte"
                }
                """;

        Mockito.when(ubicationService.addUbication(Mockito.any()))
                .thenReturn("Ubicación agregada");

        mockMvc.perform(post("/ubications")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Ubicación agregada"));
    }

    @Test
    void testUpdateUbication() throws Exception {
        String json = """
                {
                    "ubicationName": "Sucursal Actualizada"
                }
                """;

        Mockito.when(ubicationService.updateUbication(Mockito.eq(1), Mockito.any()))
                .thenReturn("Ubicación actualizada");

        mockMvc.perform(put("/ubications/1")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Ubicación actualizada"));
    }
}
