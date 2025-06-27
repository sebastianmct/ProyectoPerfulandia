package duoc.cl.PerfulandiaProject.Service;

import duoc.cl.PerfulandiaProject.Model.Product;
import duoc.cl.PerfulandiaProject.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Listar
    public String getAllProducts(){
        String output="";

        for(Product product:productRepository.findAll()){
            output+="ID Producto: "+product.getProductId()+"\n";
            output+="Nombre: "+product.getProductName()+"\n";
            output+="Descripcion: "+product.getProductDescription()+"\n";
            output+="Precio: $"+product.getProductPrice()+"\n";
        }
        if(output.isEmpty()){
            return "No hay productos registrados";
        }else{
            return output;
        }
    }

    //Buscar
    public String getProductById(int id){
        String output="";
        if(productRepository.existsById(id)){
            Product buscado=productRepository.findById(id).get();
            output+="ID Producto: "+buscado.getProductId()+"\n";
            output+="Nombre: "+buscado.getProductName()+"\n";
            output+="Descripcion: "+buscado.getProductDescription()+"\n";
            output+="Precio: $"+buscado.getProductPrice()+"\n";
            return output;
        }else{
            return "Producto no encontrado";
        }
    }

    //Agregar
    public String addProduct(Product product) {

        if(!productRepository.existsById(product.getProductId())){
            productRepository.save(product);
            return "Producto agregado correctamente";
        }else{
            return "Producto ya existe";
        }
    }

    //Eliminar
    public String deleteProduct(int id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return "Producto eliminado correctamente";
        }else{
            return "Producto no encontrado";
        }
    }

    //Actualizar
    public String updateProduct(int id,Product product) {
        if(productRepository.existsById(id)){
            Product buscado=productRepository.findById(id).get();
            buscado.setProductName(product.getProductName());
            buscado.setProductDescription(product.getProductDescription());
            buscado.setProductPrice(product.getProductPrice());
            productRepository.save(buscado);
            return "Producto actualizado correctamente";
        }else{
            return "Producto no encontrado";
        }
    }
}
