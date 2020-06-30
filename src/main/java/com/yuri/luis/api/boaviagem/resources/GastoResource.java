package com.yuri.luis.api.boaviagem.resources;

import java.net.URI;
import java.util.Arrays;
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
@RequestMapping(value = "/gastos")
public class GastoResource {
	
	@Autowired
	private GastoService gastoService;
	
	@Autowired
	private ViagemService viagemService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Gasto>>findAll(){
		List<Gasto> listaGasto = gastoService.findAll();
		return ResponseEntity.ok().body(listaGasto);
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<Gasto> findById(@PathVariable String id){
		Gasto gasto = gastoService.findById(id);
		return ResponseEntity.ok().body(gasto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public  ResponseEntity<Void> insert(@RequestBody Gasto obj){
		gastoService.insert(obj);
		Viagem viagem = viagemService.findById(obj.getIdViagem());
		viagem.getGastos().addAll(Arrays.asList(obj));
		viagemService.update(viagem);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdGasto()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
 	public ResponseEntity<Void> delete(@PathVariable String id) {
		Gasto gasto = gastoService.findById(id);
		Viagem viagem = viagemService.findById(gasto.getIdViagem());
		viagem.getGastos().removeAll(Arrays.asList(gasto));
		viagemService.update(viagem);
		gastoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
 	public ResponseEntity<Void> update(@RequestBody Gasto obj, @PathVariable String id) {
		Gasto gasto = gastoService.findById(id);
		gasto.setIdGasto(id);
		obj = gastoService.update(obj);
		Viagem viagem = viagemService.findById(obj.getIdViagem());
		viagem.getGastos().removeAll(Arrays.asList(obj));
		viagemService.update(viagem);
		return ResponseEntity.noContent().build();
	}

}
