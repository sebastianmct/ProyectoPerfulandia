package duoc.cl.PerfulandiaProject.Service;

import duoc.cl.PerfulandiaProject.Model.Product;
import duoc.cl.PerfulandiaProject.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public String addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    public String deleteProduct(int id) {
        return productRepository.removeProduct(id);
    }

    public String getAllProducts() {
        return productRepository.getAllProducts();
    }

    public String getProductById(int id) {
        return productRepository.getProductById(id);
    }

    public String updateProduct(int id, Product product) {
        return productRepository.updateProduct(id, product);
    }
}
