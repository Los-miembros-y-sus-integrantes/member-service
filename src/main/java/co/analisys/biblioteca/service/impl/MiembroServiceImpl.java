package co.analisys.biblioteca.service.impl;

import co.analisys.biblioteca.model.Miembro;
import co.analisys.biblioteca.repository.MiembroRepository;
import co.analisys.biblioteca.service.interfaces.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiembroServiceImpl implements MiembroService {

    @Autowired
    private MiembroRepository miembroRepository;

    @Override
    public Miembro registrarMiembro(Miembro miembro) {
        return miembroRepository.save(miembro);
    }

    @Override
    public List<Miembro> obtenerTodosMiembros() {
        return miembroRepository.findAll();
    }
}
