package duoc.cl.PerfulandiaProject.Model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Sale {
    private int saleId;
    private String clientId;
    private LocalDate saleDate;
    private double saleTotal;
    private List<SalesLine> salesLine;
}
