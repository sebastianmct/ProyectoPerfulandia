package duoc.cl.PerfulandiaProject.Model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int saleId;
    private int clientId; // Changed to int to match diagram and consistency
    private LocalDate saleDate;
    private double saleTotal;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SalesLine> salesLine;
}