package com.nuvu.tarjeta.credito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvu.tarjeta.credito.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String>{

	ClienteEntity findByTipoIdentificacionAndIdentificacion( String tipoIdentificacion, String identificacion );
	
}