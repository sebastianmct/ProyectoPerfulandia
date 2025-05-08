package duoc.cl.PerfulandiaProject.Event;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRegisteredEvent {
    private int SaleId;
    private int ClientId;
    private LocalDate SaleDate;
    private double SaleTotal;
}
