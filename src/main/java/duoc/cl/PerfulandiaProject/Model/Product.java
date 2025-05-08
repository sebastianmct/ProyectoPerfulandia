package duoc.cl.PerfulandiaProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Product {
    private int ProductId;
    private String ProductName;
    private String ProductDescription;
    private int ProductPrice;
}
