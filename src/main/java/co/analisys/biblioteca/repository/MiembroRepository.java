package co.analisys.biblioteca.repository;

import co.analisys.biblioteca.model.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiembroRepository extends JpaRepository<Miembro, Long> {
}
