package com.nuvu.tarjeta.credito.service.impl;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nuvu.tarjeta.credito.entity.UsuarioEntity;
import com.nuvu.tarjeta.credito.exception.ServiceException;
import com.nuvu.tarjeta.credito.repository.UsuarioRepository;
import com.nuvu.tarjeta.credito.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

	private static final String BASIC = "Basic ";
			    	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioEntity validarAutorizacion( String basic ) throws ServiceException {
		
		if( basic == null || basic.isEmpty() || !basic.contains(BASIC) ) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED);			
		}
		byte[] decodedBytes = Base64.getDecoder().decode(basic.replace(BASIC, ""));
		String decodedString = new String(decodedBytes);
		String []partesAutorizacion = decodedString.split("[:]");
		
		if( partesAutorizacion.length != 2 ) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED);
		}
		String usuario = partesAutorizacion[0];
		String password = partesAutorizacion[1];
		UsuarioEntity usuarioEntity = usuarioRepository.findByUsuarioAndPassword(usuario, password);
		if( usuarioEntity == null ) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED);
		}
		return usuarioEntity;
	}
}
