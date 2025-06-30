package duoc.cl.PerfulandiaProject;

import duoc.cl.PerfulandiaProject.Model.Client;
import duoc.cl.PerfulandiaProject.Repository.ClientRepository;
import duoc.cl.PerfulandiaProject.Service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientService clientService;

    Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client(1, "Juan", "juan@mail.com", "1234", "12345678", "Calle Falsa 123");
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(List.of(client));

        String result = clientService.getAllClients();

        assertTrue(result.contains("Juan"));
    }

    @Test
    void testGetClientByIdFound() {
        when(clientRepository.existsById(1)).thenReturn(true);
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));

        String result = clientService.getClientById(1);

        assertTrue(result.contains("juan@mail.com"));
    }

    @Test
    void testAddClientSuccess() {
        when(clientRepository.existsById(client.getClientId())).thenReturn(false);

        String result = clientService.addClient(client);

        assertEquals("Cliente agregado correctamente", result);
    }

    @Test
    void testDeleteClientSuccess() {
        when(clientRepository.existsById(1)).thenReturn(true);
        doNothing().when(clientRepository).deleteById(1);

        String result = clientService.deleteClient(1);

        assertEquals("Cliente eliminado correctamente", result);
    }
}
