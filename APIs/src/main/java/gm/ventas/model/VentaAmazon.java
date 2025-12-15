package gm.ventas.model;

import gm.ventas.model.enums.Estados;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "ventas_amazon")
public class VentaAmazon extends BaseEntity {

    @Column(name = "cod_venta", length = 10, nullable = false, unique = true)
    private String codVenta;

    @NotNull(message = "El id del usuario no puede ser null")
    @Column(name = "id_usuario", length = 10, nullable = false)
    private Integer idUsuario;

    @NotBlank(message = "El isbn del libro no puede ser null")
    @Column(name = "isbn_libro", length = 20, nullable = false)
    private String isbnLibro;

    @NotNull(message = "La cantidad del usuario no puede ser null")
    @Min(value = 1, message = "La cantidad del usuario no puede ser null")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_venta", nullable = false)
    private Estados estadoVenta;

    @PrePersist
    public void prePersist() {
        this.codVenta = generarCodigoAleatorio(10);
        this.fechaVenta = LocalDateTime.now();
        this.estadoVenta = Estados.EN_PROCESO;
    }

    private String generarCodigoAleatorio(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }

        return sb.toString();
    }
}
