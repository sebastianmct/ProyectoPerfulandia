package duoc.cl.PerfulandiaProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Client {
    private int clientId;
    private String clientName;
    private String clientEmail;
    private String clientPassword;
    private String clientPhone;
    private String clientAddress;
}
