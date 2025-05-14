package duoc.cl.PerfulandiaProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(StockId.class) // Composite Key
public class Stock {
    @Id
    private int productId;
    @Id
    private int ubicationId;
    private int quantityDisponible; // Changed to quantityDisponible to match diagram
}