package gm.ventas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "ventas_ebay")
public class VentaEbay extends BaseEntity {

    @Email
    @NotBlank
    @Column(name = "usuario_email", length = 50, nullable = false)
    private String usuarioEmail;

    @NotBlank
    @Column(name = "isbn_libro", length = 20, nullable = false)
    private String isbnLibro;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta;

    @NotNull
    @Column(nullable = false)
    private Boolean procesada = false;

    @PrePersist
    public void prePersist() {
        this.fechaVenta = LocalDateTime.now();
    }
}
