package co.analisys.biblioteca.controller.interfaces;

import co.analisys.biblioteca.model.Miembro;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Miembro registrarMiembro(@RequestBody Miembro miembro);
    
    @GetMapping()
    @Operation(summary = "Listar todos los miembros")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Miembros listados exitosamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "No se encontraron miembros")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Miembro> listarMiembros();
}
