package com.nuvu.tarjeta.credito.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nuvu.tarjeta.credito.entity.ClienteEntity;
import com.nuvu.tarjeta.credito.entity.TarjetaEntity;
import com.nuvu.tarjeta.credito.exception.ServiceException;
import com.nuvu.tarjeta.credito.service.IClienteService;
import com.nuvu.tarjeta.credito.service.ITarjetaService;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetasController extends AutorizacionController {

	private final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	private static final String ENCABEZADO_AUTORIZACION = "Authorization";
	
	@Autowired
	private ITarjetaService tarjetaService;
	
	@Autowired
	private IClienteService clienteService;
	
	
	@ResponseBody
	@RequestMapping(value = "/guardar/{tipoIdentificacion}/{identificacion}", method = RequestMethod.POST)    
	public ResponseEntity<?> guardarCliente( @RequestBody TarjetaEntity request,
		@PathVariable( name = "tipoIdentificacion", required = false ) String tipoIdentificacion,
		@PathVariable( name = "identificacion", required = false ) String identificacion,
		@RequestHeader(ENCABEZADO_AUTORIZACION) String autorizacion) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			accesoPermitido( autorizacion );
			ClienteEntity cliente = clienteService.consultarByTipoAndIdentificacion(tipoIdentificacion, identificacion);
			if( cliente == null ) {
				
				response.put("codigo", HttpStatus.NOT_FOUND.value());
				response.put("mensaje", "Cliente no existe");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			request.setCliente(cliente);
			TarjetaEntity tarjeta = tarjetaService.guardar(request);
			return new ResponseEntity<>(tarjeta, HttpStatus.OK);
			
		} catch (ServiceException e) {
			
			response.put("codigo", e.getStatus().value());
			response.put("mensaje", e.getStatus().getReasonPhrase());
			return new ResponseEntity<>( response, e.getStatus() );
		}
		catch( Exception e ) {
			logger.error("[Error={}]", e);
			response.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping( value="/encontrarPorIdentificacion/{tipoIdentificacion}/{identificacion}" )
	public ResponseEntity<?> buscarClientePor( 
		@PathVariable( name = "tipoIdentificacion", required = false ) String tipoIdentificacion,
		@PathVariable( name = "identificacion", required = false ) String identificacion,
		@RequestHeader(ENCABEZADO_AUTORIZACION) String autorizacion) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			accesoPermitido( autorizacion );
			List<TarjetaEntity> tarjetas = tarjetaService.consultarByTipoAndIdentificacion(tipoIdentificacion, identificacion);
			if( tarjetas.isEmpty() ) {
				response.put("codigo", HttpStatus.NOT_FOUND.value());
				response.put("mensaje", "Tarjetas de Credito del cliente no encontradas");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok().body( tarjetas );			
			
		} catch (ServiceException e) {
			
			response.put("codigo", e.getStatus().value());
			response.put("mensaje", e.getStatus().getReasonPhrase());
			return new ResponseEntity<>( response, e.getStatus() );
		}
		catch( Exception e ) {
			logger.error("[Error={}]", e);
			response.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping( value="/encontrarPorTarjeta/{numeroTarjeta}" )
	public ResponseEntity<?> buscarClientePor( 
			@PathVariable( name = "numeroTarjeta", required = false ) String numeroTarjeta,
			@RequestHeader(ENCABEZADO_AUTORIZACION) String autorizacion ){
		
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			accesoPermitido( autorizacion );
			
			TarjetaEntity tarjeta = tarjetaService.consultarByNumeroTarjeta(numeroTarjeta);
			if( tarjeta == null ) {
				response.put("codigo", HttpStatus.NOT_FOUND.value());
				response.put("mensaje", "Tarjetas de Credito no encontrada");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok().body( tarjeta );
			
		} catch (ServiceException e) {
			
			response.put("codigo", e.getStatus().value());
			response.put("mensaje", e.getStatus().getReasonPhrase());
			return new ResponseEntity<>( response, e.getStatus() );
		}
		catch( Exception e ) {
			logger.error("[Error={}]", e);
			response.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}			
}
