package com.nuvu.tarjeta.credito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuvu.tarjeta.credito.entity.ClienteEntity;
import com.nuvu.tarjeta.credito.entity.TarjetaEntity;

public interface TarjetaRepository extends JpaRepository<TarjetaEntity, Long> {

	List<TarjetaEntity> findByCliente( ClienteEntity cliente );
	
	TarjetaEntity findByNumeroTarjeta( String numeroTarjeta );
}
