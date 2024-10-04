package com.nunez.microservicio.cuenta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nunez.microservicio.cuenta.dao.CuentaRepository;
import com.nunez.microservicio.cuenta.domain.Cuenta;
import com.nunez.microservicio.cuenta.service.CuentaServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
@Tag(name = "Cuentas", description = "Operaciones sobre cuentas")
public class CuentaController {

	@Autowired
	private CuentaServiceImpl cuentaService;

	@Autowired
	private CuentaRepository cuentaRepository;

	@GetMapping
	@Operation(summary = "Listar cuentas", description = "Lista cuentas el sistema")
	public List<Cuenta> getAllCuentas() {
		return cuentaService.findAll();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Listar detalle de cuenta", description = "Lista detalle de cuenta en el sistema")
	public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
		Cuenta cuenta = cuentaService.findById(id);
		return cuenta != null ? ResponseEntity.ok(cuenta) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@Operation(summary = "Crear una nueva cuenta", description = "Crea una nueva cuenta en el sistema")
	public ResponseEntity<Cuenta> createCuenta(@RequestBody @Validated Cuenta cuenta) {

		Cuenta createdCuenta = cuentaService.save(cuenta);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCuenta);
	}

	@PutMapping("/{id}/depositar/{monto}")
	@Operation(summary = "Realizar un depósito", description = "Realiza un depósito en la cuenta")
	public ResponseEntity<?> depositarCuenta(@PathVariable Long id, @PathVariable Double monto) {

		Optional<Cuenta> oCuenta = cuentaRepository.findById(id);
		if (oCuenta.isPresent()) {
			Cuenta cuenta = oCuenta.get();
			double nuevoSaldo = cuenta.getSaldo() + monto;
			cuenta.setSaldo(nuevoSaldo);
			cuentaRepository.save(cuenta);

			return ResponseEntity.ok().body(cuenta);

		} else {

			return ResponseEntity.ok().body("Cuenta no existe");
		}

	}

	@PutMapping("/{id}/retirar/{monto}")
	@Operation(summary = "Realizar un retiro", description = "Realiza un retiro de la cuenta")
	public ResponseEntity<?> retirarCuenta(@PathVariable Long id, @PathVariable Double monto) {

		Optional<Cuenta> oCuenta = cuentaRepository.findById(id);
		if (oCuenta.isPresent()) {
			Cuenta cuenta = oCuenta.get();

			if (cuenta.getSaldo() >= monto) {
				double nuevoSaldo = cuenta.getSaldo() - monto;
				cuenta.setSaldo(nuevoSaldo);
				cuentaRepository.save(cuenta);

				return ResponseEntity.ok().body(cuenta);

			} else {
				return ResponseEntity.ok("Saldo insuficiente...");
			}

		} else {

			return ResponseEntity.ok("Cuenta no existe");
		}

	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar una cuenta", description = "Elimina una cuenta en la cuenta")
	public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
		cuentaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
