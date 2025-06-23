package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Assemblers.StockModelAssembler;
import duoc.cl.PerfulandiaProject.Model.Stock;
import duoc.cl.PerfulandiaProject.Model.StockId;
import duoc.cl.PerfulandiaProject.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CONTROLADOR PARA STOCK", description = "Gestión de stock de productos en distintas ubicaciones.")
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockModelAssembler assembler;

    @Operation(summary = "Obtener stock por producto y ubicación", description = "Retorna la cantidad disponible de un producto en una ubicación específica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido o malformado"),
            @ApiResponse(responseCode = "404", description = "Stock no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{productId}/{ubicationId}")
    public ResponseEntity<EntityModel<Stock>> getStock(@PathVariable int productId, @PathVariable int ubicationId) {
        StockId id = new StockId(productId, ubicationId);
        return stockRepository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear registro de stock", description = "Crea una nueva entrada de stock si no existe para la combinación de producto y ubicación.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Stock creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o stock ya existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Stock>> createStock(@RequestBody Stock stock) {
        StockId id = new StockId(stock.getProductId(), stock.getUbicationId());
        if (stockRepository.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        Stock saved = stockRepository.save(stock);
        return ResponseEntity
                .created(linkTo(methodOn(StockController.class)
                        .getStock(saved.getProductId(), saved.getUbicationId())).toUri())
                .body(assembler.toModel(saved));
    }

    @Operation(summary = "Actualizar stock", description = "Modifica la cantidad disponible de un producto en una ubicación.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para actualizar"),
            @ApiResponse(responseCode = "404", description = "Stock no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{productId}/{ubicationId}")
    public ResponseEntity<EntityModel<Stock>> updateStock(@PathVariable int productId,
                                                          @PathVariable int ubicationId,
                                                          @RequestBody Stock stock) {
        StockId id = new StockId(productId, ubicationId);
        return stockRepository.findById(id)
                .map(existing -> {
                    existing.setQuantityDisponible(stock.getQuantityDisponible());
                    stockRepository.save(existing);
                    return ResponseEntity.ok(assembler.toModel(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar stock", description = "Elimina un registro de stock.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock eliminado correctamente"),
            @ApiResponse(responseCode = "400", description = "ID inválido o malformado"),
            @ApiResponse(responseCode = "404", description = "Stock no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{productId}/{ubicationId}")
    public ResponseEntity<?> deleteStock(@PathVariable int productId, @PathVariable int ubicationId) {
        StockId id = new StockId(productId, ubicationId);
        return stockRepository.findById(id)
                .map(existing -> {
                    stockRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
