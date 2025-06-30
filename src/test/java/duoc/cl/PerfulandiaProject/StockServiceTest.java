package duoc.cl.PerfulandiaProject;

import duoc.cl.PerfulandiaProject.Model.Stock;
import duoc.cl.PerfulandiaProject.Model.StockId;
import duoc.cl.PerfulandiaProject.Repository.StockRepository;
import duoc.cl.PerfulandiaProject.Service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStock_Found() {
        StockId id = new StockId(1, 2);
        Stock stock = new Stock(1, 2, 50);

        when(stockRepository.findById(id)).thenReturn(Optional.of(stock));

        String result = stockService.getStock(1, 2);

        assertEquals("Cantidad disponible: 50", result);
    }

    @Test
    void testGetStock_NotFound() {
        StockId id = new StockId(1, 2);

        when(stockRepository.findById(id)).thenReturn(Optional.empty());

        String result = stockService.getStock(1, 2);

        assertEquals("Stock no encontrado", result);
    }

    @Test
    void testCreateStock_Success() {
        Stock stock = new Stock(1, 3, 100);
        StockId id = new StockId(1, 3);

        when(stockRepository.existsById(id)).thenReturn(false);

        String result = stockService.createStock(stock);

        assertEquals("Stock creado correctamente", result);
        verify(stockRepository).save(stock);
    }

    @Test
    void testCreateStock_AlreadyExists() {
        Stock stock = new Stock(1, 3, 100);
        StockId id = new StockId(1, 3);

        when(stockRepository.existsById(id)).thenReturn(true);

        String result = stockService.createStock(stock);

        assertEquals("El stock para ese producto y ubicación ya existe", result);
        verify(stockRepository, never()).save(any());
    }

    @Test
    void testUpdateStock_Success() {
        Stock stock = new Stock(1, 2, 80);
        StockId id = new StockId(1, 2);

        when(stockRepository.existsById(id)).thenReturn(true);

        String result = stockService.updateStock(1, 2, stock);

        assertEquals("Stock actualizado correctamente", result);
        verify(stockRepository).save(stock);
        // Also verify productId and ubicationId were set correctly:
        assertEquals(1, stock.getProductId());
        assertEquals(2, stock.getUbicationId());
    }

    @Test
    void testUpdateStock_NotFound() {
        Stock stock = new Stock(1, 2, 80);
        StockId id = new StockId(1, 2);

        when(stockRepository.existsById(id)).thenReturn(false);

        String result = stockService.updateStock(1, 2, stock);

        assertEquals("Stock no encontrado", result);
        verify(stockRepository, never()).save(any());
    }

    @Test
    void testDeleteStock_Success() {
        StockId id = new StockId(1, 2);

        when(stockRepository.existsById(id)).thenReturn(true);

        String result = stockService.deleteStock(1, 2);

        assertEquals("Stock eliminado correctamente", result);
        verify(stockRepository).deleteById(id);
    }

    @Test
    void testDeleteStock_NotFound() {
        StockId id = new StockId(1, 2);

        when(stockRepository.existsById(id)).thenReturn(false);

        String result = stockService.deleteStock(1, 2);

        assertEquals("Stock no encontrado", result);
        verify(stockRepository, never()).deleteById(any());
    }
}
