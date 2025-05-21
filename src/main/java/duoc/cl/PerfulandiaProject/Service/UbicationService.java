package duoc.cl.PerfulandiaProject.Service;

import duoc.cl.PerfulandiaProject.Model.Ubication;
import duoc.cl.PerfulandiaProject.Repository.UbicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UbicationService {

    @Autowired
    private UbicationRepository ubicationRepository;

    // Listar todas las ubicaciones
    public String getAllUbications() {
        StringBuilder output = new StringBuilder();
        for (Ubication ubication : ubicationRepository.findAll()) {
            output.append("ID Ubicacion: ").append(ubication.getUbicationId()).append("\n");
            output.append("Nombre: ").append(ubication.getUbicationName()).append("\n\n");
        }
        return output.isEmpty() ? "No hay ubicaciones registradas" : output.toString();
    }

    // Buscar una ubicacion por ID
    public String getUbicationById(int id) {
        Optional<Ubication> buscada = ubicationRepository.findById(id);
        if (buscada.isPresent()) {
            Ubication ubication = buscada.get();
            return "ID Ubicacion: " + ubication.getUbicationId() + "\n" +
                    "Nombre: " + ubication.getUbicationName() + "\n";
        } else {
            return "Ubicacion no encontrada";
        }
    }

    // Agregar una nueva ubicacion
    public String addUbication(Ubication ubication) {
        ubicationRepository.save(ubication);
        return "Ubicacion agregada correctamente";
    }

    // Actualizar una ubicacion
    public String updateUbication(int id, Ubication ubication) {
        if (ubicationRepository.existsById(id)) {
            Ubication actual = ubicationRepository.findById(id).get();
            actual.setUbicationName(ubication.getUbicationName());
            ubicationRepository.save(actual);
            return "Ubicacion actualizada correctamente";
        } else {
            return "Ubicacion no encontrada";
        }
    }

    // Eliminar una ubicacion
    public String deleteUbication(int id) {
        if (ubicationRepository.existsById(id)) {
            ubicationRepository.deleteById(id);
            return "Ubicacion eliminada correctamente";
        } else {
            return "Ubicacion no encontrada";
        }
    }
}