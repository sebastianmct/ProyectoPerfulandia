package duoc.cl.PerfulandiaProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockId implements java.io.Serializable {
    private int productId;
    private int ubicationId;

    // hashCode() and equals() are crucial for composite keys
    @Override
    public int hashCode() {
        return Objects.hash(productId, ubicationId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StockId other = (StockId) obj;
        return productId == other.productId && ubicationId == other.ubicationId;
    }
}