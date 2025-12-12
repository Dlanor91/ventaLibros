package gm.ventas.repository;

import gm.ventas.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

    Libro findByIsbn(String isbn);
}
