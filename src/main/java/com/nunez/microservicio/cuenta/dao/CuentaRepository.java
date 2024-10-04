package com.nunez.microservicio.cuenta.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nunez.microservicio.cuenta.domain.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
	
	public List<Cuenta> findByClienteId (Long clienteId);
}	