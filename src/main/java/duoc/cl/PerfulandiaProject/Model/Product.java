package duoc.cl.PerfulandiaProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private int productPrice;
}
