package com.nuvu.tarjeta.credito.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvu.tarjeta.credito.entity.ClienteEntity;
import com.nuvu.tarjeta.credito.repository.ClienteRepository;
import com.nuvu.tarjeta.credito.service.IClienteService;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteEntity guardar( ClienteEntity cliente ) {		
		return clienteRepository.save(cliente);
	}
	
	public ClienteEntity consultarByTipoAndIdentificacion( String tipoIdentificacion, String identificacion  ) {
		return clienteRepository.findByTipoIdentificacionAndIdentificacion(tipoIdentificacion, identificacion);
	}
	
}
