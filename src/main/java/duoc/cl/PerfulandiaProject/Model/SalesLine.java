package duoc.cl.PerfulandiaProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesLine {
    private int ProductId;
    private int Quantity;
    private double UnitePrice;
    private double TotalPrice;
}
