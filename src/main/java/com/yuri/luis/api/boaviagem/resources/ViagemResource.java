package com.yuri.luis.api.boaviagem.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yuri.luis.api.boaviagem.model.Gasto;
import com.yuri.luis.api.boaviagem.model.Viagem;
import com.yuri.luis.api.boaviagem.services.GastoService;
import com.yuri.luis.api.boaviagem.services.ViagemService;

@RestController
@RequestMapping(value = "/viagens")
public class ViagemResource {
	
	@Autowired
	private ViagemService viagemService;
	
	private GastoService gastoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Viagem>>findAll(){
		List<Viagem> listaUsuarios = viagemService.findAll();
		return ResponseEntity.ok().body(listaUsuarios);
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<Viagem> findById(@PathVariable String id){
		Viagem viagem = viagemService.findById(id);
		return ResponseEntity.ok().body(viagem);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public  ResponseEntity<Void> insert(@RequestBody Viagem obj){
		viagemService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdViagem()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
 	public ResponseEntity<Void> delete(@PathVariable String id) {
		viagemService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
 	public ResponseEntity<Void> update(@RequestBody Viagem obj, @PathVariable String id) {
		Viagem viagem = viagemService.findById(id);
		viagem.setIdViagem(id);
		obj = viagemService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping( value = "/{adicionaGasto}" ,method=RequestMethod.POST)
	public  ResponseEntity<Void> insertGastoToViagem(@RequestBody Gasto obj){
		gastoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdGasto()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
