package com.yuri.luis.api.boaviagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.luis.api.boaviagem.model.Usuario;
import com.yuri.luis.api.boaviagem.repository.UsuarioRepository;
import com.yuri.luis.api.boaviagem.services.exception.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private  UsuarioRepository usuarioRepository; 

	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario findById(String id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}
	
	public Usuario insert(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void delete(String id) {
		findById(id);
		usuarioRepository.deleteById(id);
	}
	
	public Usuario update(Usuario obj) {
		Usuario newObj = findById(obj.getIdUsuario());
		updateData(newObj, obj);
		return usuarioRepository.save(newObj);
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setLogin(obj.getLogin());
		newObj.setSenha(obj.getSenha());
	}
}
