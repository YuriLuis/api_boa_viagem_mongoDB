package com.yuri.luis.api.boaviagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.luis.api.boaviagem.model.Viagem;
import com.yuri.luis.api.boaviagem.repository.ViagemRepository;
import com.yuri.luis.api.boaviagem.services.exception.ObjectNotFoundException;

@Service
public class ViagemService {
	
	@Autowired
	private  ViagemRepository viagemRepository; 

	
	public List<Viagem> findAll(){
		return viagemRepository.findAll();
	}
	
	public Viagem findById(String id) {
		Optional<Viagem> viagem = viagemRepository.findById(id);
		return viagem.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}
	
	public Viagem insert(Viagem obj) {
		return viagemRepository.save(obj);
	}
	
	public void delete(String id) {
		findById(id);
		viagemRepository.deleteById(id);
	}
	
	public Viagem update(Viagem obj) {
		Viagem newObj = findById(obj.getIdViagem());
		updateData(newObj, obj);
		return viagemRepository.save(newObj);
	}

	private void updateData(Viagem newObj, Viagem obj) {
		newObj.setDestino(obj.getDestino());
		newObj.setTipoDeViagem(obj.getTipoDeViagem());
		newObj.setDataChegada(obj.getDataChegada());
		newObj.setDataPartida(obj.getDataPartida());
		newObj.setUsuario(obj.getUsuario());
		newObj.setOrcamento(obj.getOrcamento());
		newObj.setQuantidadePessoa(obj.getQuantidadePessoa());
	}
}
