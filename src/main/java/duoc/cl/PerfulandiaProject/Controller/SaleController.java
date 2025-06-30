package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Assemblers.SaleModelAssembler;
import duoc.cl.PerfulandiaProject.Model.Sale;
import duoc.cl.PerfulandiaProject.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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

@Profile("!test")
@Tag(name = "CONTROLADOR PARA VENTAS", description = "Gestiona operaciones relacionadas con las ventas y sus detalles.")
@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleModelAssembler assembler;

    @Operation(summary = "Obtener todas las ventas", description = "Lista todas las ventas registradas.")
    @GetMapping
    public CollectionModel<EntityModel<Sale>> getAllSales() {
        List<EntityModel<Sale>> sales = saleRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sales,
                linkTo(methodOn(SaleController.class).getAllSales()).withSelfRel());
    }

    @Operation(summary = "Obtener venta por ID", description = "Obtiene una venta específica por su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta encontrada"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Sale>> getSaleById(@PathVariable int id) {
        return saleRepository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar nueva venta", description = "Registra una nueva venta.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Venta registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Sale>> addSale(@RequestBody Sale sale) {
        Sale saved = saleRepository.save(sale);
        return ResponseEntity
                .created(linkTo(methodOn(SaleController.class).getSaleById(saved.getSaleId())).toUri())
                .body(assembler.toModel(saved));
    }

    @Operation(summary = "Actualizar venta", description = "Actualiza los datos de una venta existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para actualizar"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Sale>> updateSale(@PathVariable int id, @RequestBody Sale sale) {
        return saleRepository.findById(id)
                .map(existing -> {
                    existing.setClientId(sale.getClientId());
                    existing.setSaleDate(sale.getSaleDate());
                    existing.setSaleTotal(sale.getSaleTotal());
                    existing.setSalesLine(sale.getSalesLine());
                    saleRepository.save(existing);
                    return ResponseEntity.ok(assembler.toModel(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar venta", description = "Elimina una venta del sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta eliminada correctamente"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<Void>> deleteSale(@PathVariable int id) {
        return saleRepository.findById(id)
                .map(existing -> {
                    saleRepository.deleteById(id);

                    EntityModel<Void> response = EntityModel.of(null,
                            linkTo(methodOn(SaleController.class).getAllSales()).withRel("all-sales"),
                            linkTo(methodOn(SaleController.class).addSale(null)).withRel("add-sale"));

                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
