package duoc.cl.PerfulandiaProject;

import duoc.cl.PerfulandiaProject.Controller.ClientControllerV2;
import duoc.cl.PerfulandiaProject.Service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClientControllerV2.class)
public class ClientTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    void testGetAllClients() throws Exception {
        Mockito.when(clientService.getAllClients()).thenReturn("Lista de clientes");

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(content().string("Lista de clientes"));
    }

    @Test
    void testGetClientById() throws Exception {
        Mockito.when(clientService.getClientById(1)).thenReturn("Cliente 1");

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente 1"));
    }

    @Test
    void testGetClientById_NotFound() throws Exception {
        Mockito.when(clientService.getClientById(999)).thenReturn(null);

        mockMvc.perform(get("/clients/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddClient() throws Exception {
        String json = """
                {
                    "clientName": "Juan",
                    "clientEmail": "juan@mail.com",
                    "clientPassword": "1234",
                    "clientPhone": "12345678",
                    "clientAddress": "Santiago"
                }
                """;

        Mockito.when(clientService.addClient(Mockito.any())).thenReturn("Cliente agregado correctamente");

        mockMvc.perform(post("/clients")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente agregado"));
    }

    @Test
    void testAddClient_InvalidData() throws Exception {
        String json = """
                {
                    "clientName": "",
                    "clientEmail": "no-email",
                    "clientPassword": "",
                    "clientPhone": "abc",
                    "clientAddress": ""
                }
                """;

        Mockito.when(clientService.addClient(Mockito.any())).thenReturn("Cliente ya existe");

        mockMvc.perform(post("/clients")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: datos inválidos"));
    }

    @Test
    void testDeleteClient() throws Exception {
        Mockito.when(clientService.deleteClient(1)).thenReturn("Cliente eliminado correctamente");

        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente eliminado"));
    }

    @Test
    void testUpdateClient() throws Exception {
        String json = """
                {
                    "clientName": "Pedro",
                    "clientEmail": "pedro@mail.com",
                    "clientPassword": "4321",
                    "clientPhone": "87654321",
                    "clientAddress": "Valparaíso"
                }
                """;

        Mockito.when(clientService.updateClient(Mockito.eq(1), Mockito.any()))
                .thenReturn("Cliente actualizado correctamente");

        mockMvc.perform(put("/clients/1")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente actualizado"));
    }
}
