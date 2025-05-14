package duoc.cl.PerfulandiaProject.Service;

import duoc.cl.PerfulandiaProject.Event.SaleRegisteredEvent;
import duoc.cl.PerfulandiaProject.Model.Sale;
import duoc.cl.PerfulandiaProject.Model.SalesLine;
import duoc.cl.PerfulandiaProject.Repository.SaleRepository;
import duoc.cl.PerfulandiaProject.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private StockRepository stockRepository;

    // Listar
    public String getAllSales() {
        StringBuilder output = new StringBuilder();
        for (Sale sale : saleRepository.findAll()) {
            output.append("ID Venta: ").append(sale.getSaleId()).append("\n")
                    .append("ID Cliente: ").append(sale.getClientId()).append("\n")
                    .append("Fecha: ").append(sale.getSaleDate()).append("\n")
                    .append("Total: $").append(sale.getSaleTotal()).append("\n\n");
        }
        return output.isEmpty() ? "No hay ventas registradas" : output.toString();
    }

    // Buscar
    public String getSaleById(int id) {
        Optional<Sale> buscada = saleRepository.findById(id);
        if (buscada.isPresent()) {
            Sale sale = buscada.get();
            return "ID Venta: " + sale.getSaleId() + "\n"
                    + "ID Cliente: " + sale.getClientId() + "\n"
                    + "Fecha: " + sale.getSaleDate() + "\n"
                    + "Total: $" + sale.getSaleTotal() + "\n";
        } else {
            return "Venta no encontrada";
        }
    }

    // Agregar
    public String addSale(Sale sale) {
        Sale nuevaVenta = saleRepository.save(sale);
        List<Integer> productIds = new ArrayList<>();
        for (SalesLine line : nuevaVenta.getSalesLine()) {
            productIds.add(line.getProductId());
        }
        // Disparar evento
        SaleRegisteredEvent event = new SaleRegisteredEvent(
                nuevaVenta.getSaleId(),
                nuevaVenta.getClientId(),
                nuevaVenta.getSaleDate(),
                nuevaVenta.getSaleTotal(),
                productIds // Include product IDs in the event
        );
        publisher.publishEvent(event);

        return "Venta registrada correctamente y evento disparado";
    }

    // Eliminar
    public String deleteSale(int id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return "Venta eliminada correctamente";
        } else {
            return "Venta no encontrada";
        }
    }

    // Actualizar
    public String updateSale(int id, Sale sale) {
        if (saleRepository.existsById(id)) {
            Sale actual = saleRepository.findById(id).get();
            actual.setClientId(sale.getClientId());
            actual.setSaleDate(sale.getSaleDate());
            actual.setSaleTotal(sale.getSaleTotal());
            actual.setSalesLine(sale.getSalesLine());

            saleRepository.save(actual);
            return "Venta actualizada correctamente";
        } else {
            return "Venta no encontrada";
        }
    }
}