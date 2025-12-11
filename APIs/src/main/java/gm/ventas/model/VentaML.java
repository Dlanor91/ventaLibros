package gm.ventas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "ventas_ml")
public class VentaML extends gm.ventas.model.BaseEntity {
    
    @NotBlank(message = "El email del usuario no puede ser null")
    @Column(name = "user_email", length = 50, nullable = false)
    private String userEmail;

    @NotBlank(message = "El telefono del usuario no puede ser null")
    @Column(name = "user_phone", length = 20, nullable = false)
    private String userPhone;

    @NotNull(message = "La cantidad no puede ser null")
    @Min(value = 1, message = "La cantidad debe ser mayor que 0")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull(message = "El precio no puede ser null")
    @Positive(message = "El precio debe ser mayor que 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotBlank(message = "El codigo de la moneda no puede ser null")
    @Column(name = "cod_moneda", length = 3, nullable = false)
    private String codMoneda;

    @NotBlank(message = "El isbn del libro no puede ser null")
    @Column(name = "isbn_libro", length = 20, nullable = false)
    private String isbnLibro;

    @NotBlank(message = "El nombre del libro no puede ser null")
    @Column(name = "nombre_libro", length = 100, nullable = false)
    private String nombreLibro;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta;

    @Column(nullable = false)
    private Boolean procesada = false;

    @PrePersist
    public void prePersist() {
        this.fechaVenta = LocalDateTime.now();
    }
}
