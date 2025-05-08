package duoc.cl.PerfulandiaProject.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedInventoryEvent {
    private int ProductId;
    private int UbicationId;
    private int NewQuantity;
}
