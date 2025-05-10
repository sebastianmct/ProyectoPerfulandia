package duoc.cl.PerfulandiaProject.Repository;

import duoc.cl.PerfulandiaProject.Model.Product;
import duoc.cl.PerfulandiaProject.Model.Sale;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SaleRepository {

    private List<Sale> sales = new ArrayList<>();

    //Obtener todas las ventas
    public String getAllSales() {
        String output = "";
        for (Sale sale : sales) {
            output += "Id Venta: " + sale.getSaleId() + "\n";
            output += "Cliente: " + sale.getClientId() + "\n";
            output += "Fecha: " + sale.getSaleDate() + "\n";
            output += "Total: $" + sale.getSaleTotal() + "\n\n";
        }
        if (output.isEmpty()) {
            return "No se encuentran ventas";
        }else {
            return output;
        }
    }

    //Buscar venta por id
    public String getSaleById(int id) {
        String output = "";
        for (Sale sale : sales) {
            if (sale.getSaleId() == id) {
                output += "Id Venta: " + sale.getSaleId() + "\n";
                output += "Cliente: " + sale.getClientId() + "\n";
                output += "Fecha: " + sale.getSaleDate() + "\n";
                output += "Total: $" + sale.getSaleTotal() + "\n\n";
                return output;
            }
        }
        return "Venta no encontrada";
    }

    //Agregar Venta
    public String addSale(Sale sale) {
        sales.add(sale);
        return "Venta agregada con éxito";
    }

    //ELiminar venta
    public String deleteSale(int id) {
        for (Sale temp : sales) {
            if (temp.getSaleId() == id) {
                sales.remove(temp);
                return "Venta eliminada con éxito";
            }
        }
        return "Venta no encontrada";
    }

    //Actualizar venta por ID
    public String updateSale(int id, Sale newSale) {
        for (int i = 0; i < sales.size(); i++) {
            if (sales.get(i).getSaleId() == id) {
                sales.set(i, newSale);
                return "Venta Actualizada con éxito";
            }
        }
        return "Venta no encontrada";
    }
}
