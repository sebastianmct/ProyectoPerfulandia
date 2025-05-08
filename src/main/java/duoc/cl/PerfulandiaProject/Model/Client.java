package duoc.cl.PerfulandiaProject.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private int ClientId;
    private String ClientName;
    private String ClientEmail;
    private String ClientPassword;
    private String ClientPhone;
    private String ClientAddress;
}
