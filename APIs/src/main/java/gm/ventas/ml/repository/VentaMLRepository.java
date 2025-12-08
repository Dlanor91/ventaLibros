package gm.ventas.ml.repository;

import gm.ventas.ml.model.VentaML;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaMLRepository extends JpaRepository<VentaML, Integer> {
    List<VentaML> findByProcesadaFalse();
}
