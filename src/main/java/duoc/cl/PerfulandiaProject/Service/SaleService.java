package duoc.cl.PerfulandiaProject.Service;

import duoc.cl.PerfulandiaProject.Event.SaleRegisteredEvent;
import duoc.cl.PerfulandiaProject.Model.Sale;
import duoc.cl.PerfulandiaProject.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private SaleRepository saleRepository;

    public String registerSale(Sale sale) {
        saleRepository.addSale(sale); // Guarda la venta
        SaleRegisteredEvent event = new SaleRegisteredEvent(
                sale.getSaleId(),
                Integer.parseInt(sale.getClientId()),
                sale.getSaleDate(),
                sale.getSaleTotal()
        );
        publisher.publishEvent(event);
        return "Venta registrada y evento disparado.";
    }

    public String getAllSales() {
        return saleRepository.getAllSales();
    }

    public String getSaleById(int id) {
        return saleRepository.getSaleById(id);
    }

    public String deleteSale(int id) {
        String result = saleRepository.deleteSale(id);
        return result;
    }

    public String updateSale(int id, Sale sale) {
        String result = saleRepository.updateSale(id, sale);
        return result;
    }

}
