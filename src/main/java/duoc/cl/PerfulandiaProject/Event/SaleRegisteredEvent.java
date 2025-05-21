package duoc.cl.PerfulandiaProject.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRegisteredEvent {
    private int saleId;
    private int clientId;
    private LocalDate saleDate;
    private double total;
    private List<Integer> productIds;
}