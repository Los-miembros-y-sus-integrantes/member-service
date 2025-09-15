package co.analisys.biblioteca.controller.interfaces;

import co.analisys.biblioteca.model.Miembro;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("/miembros")
@Tag(name = "Miembro", description = "Operaciones relacionadas con los miembros")
public interface MiembroController {

    @PostMapping()
    @Operation(summary = "Registrar un nuevo miembro")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Miembro registrado exitosamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public Miembro registrarMiembro(@RequestBody Miembro miembro);
    
    @GetMapping()
    @Operation(summary = "Listar todos los miembros")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Miembros listados exitosamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "No se encontraron miembros")
    })
    public List<Miembro> listarMiembros();

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un miembro por su ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Miembro encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Miembro no encontrado")
    })
    public Miembro obtenerMiembroPorId(@PathVariable Long id);

}
