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
    private int SaleId;
    private String ClientId;
    private LocalDate SaleDate;
    private double Total;
    private List<SalesLine> SalesLine;
}
