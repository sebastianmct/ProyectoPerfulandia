package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Assemblers.UbicationModelAssembler;
import duoc.cl.PerfulandiaProject.Model.Ubication;
import duoc.cl.PerfulandiaProject.Repository.UbicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CONTROLADOR PARA UBICACIONES", description = "Gestión de ubicaciones físicas en el sistema.")
@RestController
@RequestMapping("/ubications")
public class UbicationController {

    @Autowired
    private UbicationRepository ubicationRepository;

    @Autowired
    private UbicationModelAssembler assembler;

    @Operation(summary = "Obtener todas las ubicaciones", description = "Lista todas las ubicaciones disponibles.")
    @GetMapping
    public CollectionModel<EntityModel<Ubication>> getAllUbications() {
        List<EntityModel<Ubication>> ubications = ubicationRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(ubications,
                linkTo(methodOn(UbicationController.class).getAllUbications()).withSelfRel());
    }

    @Operation(summary = "Obtener ubicación por ID", description = "Obtiene una ubicación específica mediante su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ubicación encontrada"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Ubication>> getUbicationById(@PathVariable int id) {
        return ubicationRepository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nueva ubicación", description = "Registra una nueva ubicación.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ubicación creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Ubication>> addUbication(@RequestBody Ubication ubication) {
        Ubication saved = ubicationRepository.save(ubication);
        return ResponseEntity
                .created(linkTo(methodOn(UbicationController.class).getUbicationById(saved.getUbicationId())).toUri())
                .body(assembler.toModel(saved));
    }

    @Operation(summary = "Actualizar ubicación", description = "Actualiza el nombre de una ubicación.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ubicación actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para actualizar"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Ubication>> updateUbication(@PathVariable int id, @RequestBody Ubication ubication) {
        return ubicationRepository.findById(id)
                .map(existing -> {
                    existing.setUbicationName(ubication.getUbicationName());
                    ubicationRepository.save(existing);
                    return ResponseEntity.ok(assembler.toModel(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar ubicación", description = "Elimina una ubicación del sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ubicación eliminada correctamente"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<Void>> deleteUbication(@PathVariable int id) {
        return ubicationRepository.findById(id)
                .map(existing -> {
                    ubicationRepository.deleteById(id);

                    EntityModel<Void> response = EntityModel.of(null,
                            linkTo(methodOn(UbicationController.class).getAllUbications()).withRel("all-ubications"),
                            linkTo(methodOn(UbicationController.class).addUbication(null)).withRel("add-ubication")
                    );

                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
