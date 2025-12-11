package gm.ventas.repository;

import gm.ventas.model.VentaAmazon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaAmazonRepository extends JpaRepository<VentaAmazon, Integer> {
    
}
