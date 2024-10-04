package com.nunez.microservicio.cuenta.domain;

import com.nunez.microservicio.cuenta.util.TipoCuenta;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "cuentas")
@Data

public class Cuenta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único de la cuenta", example = "1")
    private Long id;

	@Schema(description = "Número de cuenta", example = "1234567890")
    private String numeroCuenta;

    @Min(value = 1, message = "El saldo debe ser mayor que 0")
    @NotNull
    @Schema(description = "Saldo en la cuenta", example = "100.00")
    private Double saldo;

    @Schema(description = "Tipo de Cuenta", example = "AHORROS")
    private TipoCuenta tipoCuenta;
    
    @Schema(description = "Identificador único deL CLIENTE", example = "1")
    private Long clienteId;

    // Getters y setters
    // ...

}
