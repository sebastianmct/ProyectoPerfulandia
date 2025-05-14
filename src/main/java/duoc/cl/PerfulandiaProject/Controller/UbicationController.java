package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Model.Ubication;
import duoc.cl.PerfulandiaProject.Service.UbicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ubications")
public class UbicationController {

    @Autowired
    private UbicationService ubicationService;

    @GetMapping
    public String getAllUbications() {
        return ubicationService.getAllUbications();
    }

    @GetMapping("/{id}")
    public String getUbicationById(@PathVariable int id) {
        return ubicationService.getUbicationById(id);
    }

    @PostMapping
    public String addUbication(@RequestBody Ubication ubication) {
        return ubicationService.addUbication(ubication);
    }

    @PutMapping("/{id}")
    public String updateUbication(@PathVariable int id, @RequestBody Ubication ubication) {
        return ubicationService.updateUbication(id, ubication);
    }

    @DeleteMapping("/{id}")
    public String deleteUbication(@PathVariable int id) {
        return ubicationService.deleteUbication(id);
    }
}