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

import com.yuri.luis.api.boaviagem.model.Usuario;
import com.yuri.luis.api.boaviagem.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>>findAll(){
		List<Usuario> listaUsuarios = usuarioService.findAll();
		return ResponseEntity.ok().body(listaUsuarios);
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<Usuario> findById(@PathVariable String id){
		Usuario usuario = usuarioService.findById(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public  ResponseEntity<Void> insert(@RequestBody Usuario obj){
		usuarioService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
 	public ResponseEntity<Void> delete(@PathVariable String id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
 	public ResponseEntity<Void> update(@RequestBody Usuario obj, @PathVariable String id) {
		Usuario usuario = usuarioService.findById(id);
		usuario.setIdUsuario(id);
		obj = usuarioService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{autentica}", method=RequestMethod.POST)
	public ResponseEntity<Void> autenticaUsuario(@RequestBody Usuario obj){
		
		for(Usuario u : usuarioService.findAll()) {
			
			if (obj.getLogin().equalsIgnoreCase(u.getLogin()) &&
					obj.getSenha().equalsIgnoreCase(u.getSenha())) {
				obj.setIdUsuario(u.getIdUsuario());
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();
				return ResponseEntity.created(uri).build();
			}
		}
		return null;
	}

}
