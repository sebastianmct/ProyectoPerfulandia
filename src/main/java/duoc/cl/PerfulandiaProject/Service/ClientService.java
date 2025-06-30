package duoc.cl.PerfulandiaProject.Service;

import duoc.cl.PerfulandiaProject.Model.Client;
import duoc.cl.PerfulandiaProject.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //Listar
    public String getAllClients(){
        String output="";

        for(Client client:clientRepository.findAll()){
            output+="ID Cliente: "+client.getClientId()+"\n";
            output+="Nombre: "+client.getClientName()+"\n";
            output+="Email: "+client.getClientEmail()+"\n";
            output+="Contraseña: "+client.getClientPassword()+"\n";
            output+="Telefono: "+client.getClientPhone()+"\n";
            output+="Direccion: "+client.getClientAddress()+"\n";

        }
        if(output.isEmpty()){
            return "No hay clientes registrados";
        }else{
            return output;
        }
    }

    //Buscar
    public String getClientById(int id){
        String output="";
        if(clientRepository.existsById(id)){
            Client buscado=clientRepository.findById(id).get();
            output+="ID Cliente: "+buscado.getClientId()+"\n";
            output+="Nombre: "+buscado.getClientName()+"\n";
            output+="Email: "+buscado.getClientEmail()+"\n";
            output+="Contraseña: "+buscado.getClientPassword()+"\n";
            output+="Telefono: "+buscado.getClientPhone()+"\n";
            output+="Direccion: "+buscado.getClientAddress()+"\n";
            return output;
        }else{
            return "Cliente no encontrado";
        }
    }

    //Agregar
    public String addClient(Client client) {

        if(!clientRepository.existsById(client.getClientId())){
            clientRepository.save(client);
            return "Cliente agregado correctamente";
        }else{
            return "Cliente ya existe";
        }
    }

    //Eliminar
    public String deleteClient(int id) {
        if(clientRepository.existsById(id)){
            clientRepository.deleteById(id);
            return "Cliente eliminado correctamente";
        }else{
            return "Cliente no encontrado";
        }
    }

    //Actualizar
    public String updateClient(int id,Client client) {
        if(clientRepository.existsById(id)){
            Client buscado=clientRepository.findById(id).get();
            buscado.setClientName(client.getClientName());
            buscado.setClientEmail(client.getClientEmail());
            buscado.setClientPassword(client.getClientPassword());
            buscado.setClientPhone(client.getClientPhone());
            buscado.setClientAddress(client.getClientAddress());
            clientRepository.save(buscado);
            return "Cliente actualizado correctamente";
        }else{
            return "Cliente no encontrado";
        }
    }
}
