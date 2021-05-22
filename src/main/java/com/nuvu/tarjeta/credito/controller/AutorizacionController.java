package com.nuvu.tarjeta.credito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nuvu.tarjeta.credito.entity.UsuarioEntity;
import com.nuvu.tarjeta.credito.exception.ServiceException;

@RestController
public class AutorizacionController {
	
	@Value("${application.autorizacion.url}") 
	private String urlAutorizacion;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public boolean accesoPermitido( String basic ) throws ServiceException {
		
		HttpEntity<String> request = new HttpEntity<>(basic);		
		ResponseEntity<UsuarioEntity> response = restTemplate.exchange(urlAutorizacion, HttpMethod.POST, request, UsuarioEntity.class);
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new ServiceException(response.getStatusCode());
		}
		return true;
	}
}
