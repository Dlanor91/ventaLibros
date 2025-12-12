package gm.ventas.repository;

import gm.ventas.model.VentaAmazon;
import gm.ventas.model.enums.Estados;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaAmazonRepository extends JpaRepository<VentaAmazon, Integer> {
    List<VentaAmazon> findByEstadoVenta(Estados estado);
}
