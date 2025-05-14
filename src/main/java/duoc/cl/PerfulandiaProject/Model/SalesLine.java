package duoc.cl.PerfulandiaProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalesLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesLineId; // Changed to a more appropriate name

    @ManyToOne
    @JoinColumn(name = "sale_id") // Foreign key to Sale
    private Sale sale;

    private int productId;
    private int quantity;
    private double unitePrice; // Changed to unitePrice to match diagram
    private double subtotal;  // Changed to subtotal to match diagram
}