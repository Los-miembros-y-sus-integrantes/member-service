package co.analisys.biblioteca.controller.interfaces;

import co.analisys.biblioteca.model.Miembro;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/miembros")
public interface MiembroController {

    @PostMapping()
    public Miembro registrarMiembro(@RequestBody Miembro miembro);
    @GetMapping()
    public List<Miembro> listarMiembros();
}
