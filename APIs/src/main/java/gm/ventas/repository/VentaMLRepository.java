package gm.ventas.repository;

import gm.ventas.model.VentaML;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaMLRepository extends JpaRepository<VentaML, Integer> {
    List<VentaML> findByProcesadaFalse();
}
