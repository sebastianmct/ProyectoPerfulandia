package duoc.cl.PerfulandiaProject.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List; // Import List

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRegisteredEvent {
    private int saleId;
    private int clientId;
    private LocalDate saleDate;
    private double total;  // Changed to total to match diagram
    private List<Integer> productIds; // Add list of product IDs in the event
}