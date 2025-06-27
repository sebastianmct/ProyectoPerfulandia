package duoc.cl.PerfulandiaProject;

import duoc.cl.PerfulandiaProject.Controller.UbicationControllerV2;
import duoc.cl.PerfulandiaProject.Service.UbicationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(UbicationControllerV2.class)
public class UbicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UbicationService ubicationService;

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
        Mockito.when(ubicationService.getUbicationById(999)).thenReturn("Ubicacion no encontrada");

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
                .thenReturn("Ubicación agregada correctamente");

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
                .thenReturn("Ubicación actualizada correctamente");

        mockMvc.perform(put("/ubications/1")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Ubicación actualizada"));
    }

    @Test
    void testDeleteUbication() throws Exception {
        Mockito.when(ubicationService.deleteUbication(1))
                .thenReturn("Ubicación eliminada correctamente");

        mockMvc.perform(delete("/ubications/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ubicación eliminada"));
    }
}
