package duoc.cl.PerfulandiaProject.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SaleRegisteredEvent {
    private int saleId;
    private int clientId;
    private LocalDate saleDate;
    private double saleTotal;
}
