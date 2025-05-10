package duoc.cl.PerfulandiaProject.Event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SaleEventListener {

    @EventListener
    public void handleSaleRegistered(SaleRegisteredEvent event) {
        System.out.println("Evento recibido: Venta registrada para el cliente ID: " + event.getClientId());
        // Aquí puedes agregar lógica para reducir inventario, enviar email, etc.
    }
}
