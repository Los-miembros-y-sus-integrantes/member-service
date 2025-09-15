package co.analisys.biblioteca.service.interfaces;

import co.analisys.biblioteca.model.Miembro;

import java.util.List;

public interface MiembroService {

    public Miembro registrarMiembro(Miembro miembro);
    public List<Miembro> obtenerTodosMiembros();
    public Miembro obtenerMiembroPorId(Long id);
}
