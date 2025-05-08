package duoc.cl.PerfulandiaProject.Service;

import duoc.cl.PerfulandiaProject.Model.Product;
import duoc.cl.PerfulandiaProject.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository ProductRepository;

    //Listar
    public String getAllProducts() {
        String output = "";
        for (Product product : ProductRepository.findAll()) {
            output += "Id Producto: "+ product.getProductId() + ", ";
            output += "Nombre: "+ product.getProductName() + ", ";
            output += "Descripcion: "+ product.getProductDescription() + ", ";
            output += "Precio: "+ product.getProductPrice() + ", ";

        }
        if(output.isEmpty()){
            return "No hay productos registrados";
        }else{
            return output;
        }
    }

    //Buscar
    public String getProductById(int id) {
        String output = "";
        if (ProductRepository.existsById(id)) {
            Product buscado = ProductRepository.findById(id).get();
            output += "Id Producto: " + buscado.getProductId() + ", ";
            output += "Nombre: " + buscado.getProductName() + ", ";
            output += "Descripcion: " + buscado.getProductDescription() + ", ";
            output += "Precio: " + buscado.getProductPrice() + ", ";
        } else {
            return "Producto no encontrado";
        }
    }

    //Agregar
    public String addProduct(Product product) {

        if(!ProductRepository.existsById(product.getProductId())){
            ProductRepository.save(product);
            return "Producto agregado correctamente";
        }else{
            return "Producto ya existe";
        }
    }

    //Eliminar
    public String deleteProduct(int id) {
        if(ProductRepository.existsById(id)){
            ProductRepository.deleteById(id);
            return "Producto eliminado correctamente";
        }else{
            return "Producto no encontrado";
        }
    }

    public String updateProduct(int id,Product product) {
        if(ProductRepository.existsById(id)){
            Product buscado=ProductRepository.findById(id).get();
            buscado.setProductName(product.getProductName());
            buscado.setProductDescription(product.getProductDescription());
            buscado.setProductPrice(product.getProductPrice());
            ProductRepository.save(buscado);
            return "Producto actualizado correctamente";
        }else{
            return "Producto no encontrado";
        }
    }
}
