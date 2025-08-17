package co.analisys.biblioteca.controller.impl;

import co.analisys.biblioteca.controller.interfaces.MiembroController;
import co.analisys.biblioteca.model.Miembro;
import co.analisys.biblioteca.service.interfaces.MiembroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MiembroControllerImpl implements MiembroController {

    private final MiembroService miembroService;


    @Override
    public Miembro registrarMiembro(Miembro miembro) {
        return miembroService.registrarMiembro(miembro);
    }

    @Override
    public List<Miembro> listarMiembros() {
        return miembroService.obtenerTodosMiembros();
    }
}
