package com.nuvu.tarjeta.credito.service;

import com.nuvu.tarjeta.credito.entity.UsuarioEntity;
import com.nuvu.tarjeta.credito.exception.ServiceException;

public interface IUsuarioService {

	UsuarioEntity validarAutorizacion( String basic ) throws ServiceException;
	
}
