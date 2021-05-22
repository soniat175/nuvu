package com.nuvu.tarjeta.credito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuvu.tarjeta.credito.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	UsuarioEntity findByUsuarioAndPassword( String usuario, String password );

}
