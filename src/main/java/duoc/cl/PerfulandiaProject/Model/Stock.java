package duoc.cl.PerfulandiaProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Stock {
    private int productId;
    private String ubicationId;
    private int quantity;
}
