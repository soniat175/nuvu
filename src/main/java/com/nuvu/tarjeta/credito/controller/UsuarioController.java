package com.nuvu.tarjeta.credito.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nuvu.tarjeta.credito.entity.UsuarioEntity;
import com.nuvu.tarjeta.credito.exception.ServiceException;
import com.nuvu.tarjeta.credito.service.IUsuarioService;

@RestController
@RequestMapping("/api/autorizacion")
public class UsuarioController {
	
	private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@ResponseBody
	@RequestMapping(value = "/validar", method = RequestMethod.POST)    
	public ResponseEntity<UsuarioEntity> validarAutorizacion( @RequestBody String basic ) {
		
		try {			
			return ResponseEntity.ok().body(
				usuarioService.validarAutorizacion(basic)
			);			
		} catch (ServiceException e) {			
			return new ResponseEntity<>(e.getStatus());
			
		}catch( Exception e ) {
			logger.error("[Error={}]", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
