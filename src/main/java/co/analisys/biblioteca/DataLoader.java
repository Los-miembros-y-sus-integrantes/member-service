package co.analisys.biblioteca;

import co.analisys.biblioteca.model.Miembro;
import co.analisys.biblioteca.repository.MiembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MiembroRepository miembroRepository;

    public void run(String... args) throws Exception {
        Miembro miembro1 = new Miembro();
        miembro1.setNombre("Juan Pérez");
        miembro1.setEmail("juan@email.com");
        miembro1.setFechaInscripcion(LocalDate.now());
        miembroRepository.save(miembro1);

        Miembro miembro2 = new Miembro();
        miembro2.setNombre("María López");
        miembro2.setEmail("maria@email.com");
        miembro2.setFechaInscripcion(LocalDate.now().minusDays(30));
        miembroRepository.save(miembro2);
    }
}