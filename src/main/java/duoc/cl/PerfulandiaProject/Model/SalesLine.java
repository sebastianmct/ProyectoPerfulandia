package duoc.cl.PerfulandiaProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SalesLine {
    private int productId;
    private int quantity;
    private double unitePrice;
    private double totalPrice;
}
