package duoc.cl.PerfulandiaProject.Repository;


import duoc.cl.PerfulandiaProject.Model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository {
    private List<Client> clients = new ArrayList<>();

    // Obtener todos los clientes
    public String getAllClients() {
        String output = "";
        for (Client temp : clients) {
            output += "Id Cliente: " + temp.getClientId() + "\n";
            output += "Nombre: " + temp.getClientName() + "\n";
            output += "Email: " + temp.getClientEmail() + "\n";
            output += "Contraseña: " + temp.getClientPassword() + "\n\n";
            output += "Telefono: " + temp.getClientPhone() + "\n\n";
            output += "Direccion: " + temp.getClientAddress() + "\n\n";
        }
        if (output.isEmpty()) {
            return "No se encuentran clientes";
        } else {
            return output;
        }
    }

    // Buscar cliente por ID
    public String getClientById(int id) {
        String output = "";
        for (Client temp : clients) {
            if (temp.getClientId() == id) {
                output += "Id Cliente: " + temp.getClientId() + "\n";
                output += "Nombre: " + temp.getClientName() + "\n";
                output += "Email: " + temp.getClientEmail() + "\n";
                output += "Contraseña: " + temp.getClientPassword() + "\n\n";
                output += "Telefono: " + temp.getClientPhone() + "\n\n";
                output += "Direccion: " + temp.getClientAddress() + "\n\n";
                return output;
            }
        }
        return "Cliente no encontrado";
    }

    // Agregar un cliente
    public String addClient(Client client) {
        clients.add(client);
        return "Cliente agregado con éxito";
    }

    // Eliminar un Cliente
    public String removeClient(int id) {
        for (Client temp : clients) {
            if (temp.getClientId() == id) {
                clients.remove(temp);
                return "Cliente eliminado con éxito";
            }
        }
        return "Cliente no encontrado";
    }

    // Actualizar un Cliente
    public String updateClient(int id, Client client) {
        for (int i = 0; i < clients.size(); i++) {
            Client temp = clients.get(i);
            if (temp.getClientId() == id) {
                clients.set(i, client);
                return "Cliente actualizado con éxito";
            }
        }
        return "Cliente no encontrado";
    }
}
