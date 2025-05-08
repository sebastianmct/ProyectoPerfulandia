package duoc.cl.PerfulandiaProject.Repository;

import duoc.cl.PerfulandiaProject.Model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    // Obtener todos los productos
    public String getAllProducts() {
        String output = "";
        for (Product temp : products) {
            output += "Id Producto: " + temp.getProductId() + "\n";
            output += "Nombre: " + temp.getProductName() + "\n";
            output += "Descripción: " + temp.getProductDescription() + "\n";
            output += "Precio: $" + temp.getProductPrice() + "\n\n";
        }
        if (output.isEmpty()) {
            return "No se encuentran productos";
        } else {
            return output;
        }
    }

    // Buscar producto por ID
    public String getProductById(int id) {
        String output = "";
        for (Product temp : products) {
            if (temp.getProductId() == id) {
                output += "Id Producto: " + temp.getProductId() + "\n";
                output += "Nombre: " + temp.getProductName() + "\n";
                output += "Descripción: " + temp.getProductDescription() + "\n";
                output += "Precio: $" + temp.getProductPrice() + "\n\n";
                return output;
            }
        }
        return "Producto no encontrado";
    }

    // Agregar un producto
    public String addProduct(Product product) {
        products.add(product);
        return "Producto agregado con éxito";
    }

    // Eliminar un producto
    public String removeProduct(int id) {
        for (Product temp : products) {
            if (temp.getProductId() == id) {
                products.remove(temp);
                return "Producto eliminado con éxito";
            }
        }
        return "Producto no encontrado";
    }

    // Actualizar un producto
    public String updateProduct(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            Product temp = products.get(i);
            if (temp.getProductId() == id) {
                products.set(i, product);
                return "Producto actualizado con éxito";
            }
        }
        return "Producto no encontrado";
    }
}
