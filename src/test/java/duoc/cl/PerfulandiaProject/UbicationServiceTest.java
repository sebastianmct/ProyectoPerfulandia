package duoc.cl.PerfulandiaProject;

import duoc.cl.PerfulandiaProject.Model.Ubication;
import duoc.cl.PerfulandiaProject.Repository.UbicationRepository;
import duoc.cl.PerfulandiaProject.Service.UbicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UbicationServiceTest {

    @Mock
    private UbicationRepository ubicationRepository;

    @InjectMocks
    private UbicationService ubicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUbications_WithData() {
        List<Ubication> list = Arrays.asList(
                new Ubication(1, "Bodega"),
                new Ubication(2, "Tienda")
        );

        when(ubicationRepository.findAll()).thenReturn(list);

        String result = ubicationService.getAllUbications();

        assertTrue(result.contains("ID Ubicacion: 1"));
        assertTrue(result.contains("Nombre: Bodega"));
        assertTrue(result.contains("ID Ubicacion: 2"));
        assertTrue(result.contains("Nombre: Tienda"));
    }

    @Test
    void testGetAllUbications_NoData() {
        when(ubicationRepository.findAll()).thenReturn(Collections.emptyList());

        String result = ubicationService.getAllUbications();

        assertEquals("No hay ubicaciones registradas", result);
    }

    @Test
    void testGetUbicationById_Found() {
        Ubication u = new Ubication(1, "Bodega");
        when(ubicationRepository.findById(1)).thenReturn(Optional.of(u));

        String result = ubicationService.getUbicationById(1);

        assertTrue(result.contains("ID Ubicacion: 1"));
        assertTrue(result.contains("Nombre: Bodega"));
    }

    @Test
    void testGetUbicationById_NotFound() {
        when(ubicationRepository.findById(1)).thenReturn(Optional.empty());

        String result = ubicationService.getUbicationById(1);

        assertEquals("Ubicacion no encontrada", result);
    }

    @Test
    void testAddUbication() {
        Ubication u = new Ubication(0, "Nueva Ubicacion");
        when(ubicationRepository.save(u)).thenReturn(u);

        String result = ubicationService.addUbication(u);

        assertEquals("Ubicacion agregada correctamente", result);
        verify(ubicationRepository).save(u);
    }

    @Test
    void testUpdateUbication_Found() {
        Ubication existing = new Ubication(1, "Bodega");
        Ubication updated = new Ubication(1, "Bodega Actualizada");

        when(ubicationRepository.existsById(1)).thenReturn(true);
        when(ubicationRepository.findById(1)).thenReturn(Optional.of(existing));
        when(ubicationRepository.save(any(Ubication.class))).thenReturn(updated);

        String result = ubicationService.updateUbication(1, updated);

        assertEquals("Ubicacion actualizada correctamente", result);
        assertEquals("Bodega Actualizada", existing.getUbicationName());
        verify(ubicationRepository).save(existing);
    }

    @Test
    void testUpdateUbication_NotFound() {
        when(ubicationRepository.existsById(1)).thenReturn(false);

        Ubication u = new Ubication(1, "Cualquier Nombre");

        String result = ubicationService.updateUbication(1, u);

        assertEquals("Ubicacion no encontrada", result);
        verify(ubicationRepository, never()).save(any());
    }

    @Test
    void testDeleteUbication_Found() {
        when(ubicationRepository.existsById(1)).thenReturn(true);

        String result = ubicationService.deleteUbication(1);

        assertEquals("Ubicacion eliminada correctamente", result);
        verify(ubicationRepository).deleteById(1);
    }

    @Test
    void testDeleteUbication_NotFound() {
        when(ubicationRepository.existsById(1)).thenReturn(false);

        String result = ubicationService.deleteUbication(1);

        assertEquals("Ubicacion no encontrada", result);
        verify(ubicationRepository, never()).deleteById(anyInt());
    }
}
