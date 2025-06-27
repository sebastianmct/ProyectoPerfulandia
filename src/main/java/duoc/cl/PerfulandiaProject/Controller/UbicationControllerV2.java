package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Model.Ubication;
import duoc.cl.PerfulandiaProject.Service.UbicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Profile("test")
@RestController
@RequestMapping("/ubications")
public class UbicationControllerV2 {

    @Autowired
    private UbicationService ubicationService;

    @GetMapping
    public ResponseEntity<String> getAllUbications() {
        String response = ubicationService.getAllUbications();
        if (response == null || response.equals("No hay ubicaciones registradas")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUbicationById(@PathVariable int id) {
        String response = ubicationService.getUbicationById(id);
        if (response == null || response.equals("Ubicacion no encontrada")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> addUbication(@RequestBody Ubication ubication) {
        String response = ubicationService.addUbication(ubication);
        if (response.toLowerCase().contains("error")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok("Ubicación agregada");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUbication(@PathVariable int id, @RequestBody Ubication ubication) {
        String response = ubicationService.updateUbication(id, ubication);
        if (response.equals("Ubicacion no encontrada")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Ubicación actualizada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUbication(@PathVariable int id) {
        String response = ubicationService.deleteUbication(id);
        if (response.equals("Ubicacion no encontrada")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Ubicación eliminada");
    }
}
