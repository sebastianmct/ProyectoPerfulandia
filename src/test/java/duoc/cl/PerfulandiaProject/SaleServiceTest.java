package duoc.cl.PerfulandiaProject;

import duoc.cl.PerfulandiaProject.Event.SaleRegisteredEvent;
import duoc.cl.PerfulandiaProject.Model.Sale;
import duoc.cl.PerfulandiaProject.Model.SalesLine;
import duoc.cl.PerfulandiaProject.Repository.SaleRepository;
import duoc.cl.PerfulandiaProject.Service.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private SaleService saleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSales_ReturnsSalesDetails() {
        Sale sale = new Sale();
        sale.setSaleId(1);
        sale.setClientId(100);
        sale.setSaleDate(LocalDate.of(2025, 6, 27));
        sale.setSaleTotal(3000.0);

        when(saleRepository.findAll()).thenReturn(Collections.singletonList(sale));

        String result = saleService.getAllSales();

        assertTrue(result.contains("ID Venta: 1"));
        assertTrue(result.contains("ID Cliente: 100"));
        assertTrue(result.contains("Fecha: 2025-06-27"));
        assertTrue(result.contains("Total: $3000.0"));
    }

    @Test
    void testGetSaleById_Found() {
        Sale sale = new Sale();
        sale.setSaleId(5);
        sale.setClientId(55);
        sale.setSaleDate(LocalDate.now());
        sale.setSaleTotal(1234.56);

        when(saleRepository.findById(5)).thenReturn(Optional.of(sale));

        String result = saleService.getSaleById(5);

        assertTrue(result.contains("ID Venta: 5"));
        assertTrue(result.contains("ID Cliente: 55"));
        assertTrue(result.contains("Total: $1234.56"));
    }

    @Test
    void testAddSale_PublishesEvent() {
        Sale sale = new Sale();
        sale.setSaleId(1);
        sale.setClientId(99);
        sale.setSaleDate(LocalDate.of(2025, 6, 27));
        sale.setSaleTotal(2000.0);

        SalesLine line1 = new SalesLine();
        line1.setProductId(10);
        line1.setQuantity(2);
        line1.setUnitePrice(500.0);
        line1.setSubtotal(1000.0);
        line1.setSale(sale);

        SalesLine line2 = new SalesLine();
        line2.setProductId(11);
        line2.setQuantity(1);
        line2.setUnitePrice(1000.0);
        line2.setSubtotal(1000.0);
        line2.setSale(sale);

        sale.setSalesLine(Arrays.asList(line1, line2));

        when(saleRepository.save(sale)).thenReturn(sale);

        String result = saleService.addSale(sale);

        assertEquals("Venta registrada correctamente y evento disparado", result);
        verify(publisher, times(1)).publishEvent(any(SaleRegisteredEvent.class));
    }

    @Test
    void testDeleteSale_Success() {
        when(saleRepository.existsById(7)).thenReturn(true);

        String result = saleService.deleteSale(7);

        assertEquals("Venta eliminada correctamente", result);
        verify(saleRepository).deleteById(7);
    }
}
