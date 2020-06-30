package com.yuri.luis.api.boaviagem.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.luis.api.boaviagem.model.Gasto;
import com.yuri.luis.api.boaviagem.model.Viagem;
import com.yuri.luis.api.boaviagem.repository.GastoRepository;
import com.yuri.luis.api.boaviagem.repository.ViagemRepository;
import com.yuri.luis.api.boaviagem.services.exception.ObjectNotFoundException;

@Service
public class GastoService {
	
	@Autowired
	private  GastoRepository gastoRepository; 
	
	@Autowired
	private ViagemRepository viagemRepository;

	
	public List<Gasto> findAll(){
		return gastoRepository.findAll();
	}
	
	public Gasto findById(String id) {
		Optional<Gasto> gasto = gastoRepository.findById(id);
		return gasto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}
	
	public Gasto insert(Gasto gasto) {
		
		gastoRepository.save(gasto);
		Optional<Viagem> viagem = viagemRepository.findById(gasto.getIdViagem());
		if (viagem.isPresent()) {
			viagem.get().getGastos().addAll(Arrays.asList(gasto));
			viagemRepository.save(viagem.get());
		}

		return gasto;
	}
	
	public void delete(String id) {
		Optional<Gasto> gasto = gastoRepository.findById(id);
		Optional<Viagem> viagem = viagemRepository.findById(gasto.get().getIdViagem());
		if (viagem.isPresent()) {
			viagem.get().getGastos().removeAll(Arrays.asList(gasto.get()));
			viagemRepository.save(viagem.get());
		}
		
		gastoRepository.deleteById(gasto.get().getIdGasto());
	}
	
	public Gasto update(Gasto obj) {
		Gasto newObj = findById(obj.getIdGasto());
		updateData(newObj, obj);
		gastoRepository.save(newObj);
		Optional<Viagem> viagem = viagemRepository.findById(newObj.getIdViagem());
		if (viagem.isPresent()) {
			viagem.get().getGastos().clear();
			viagem.get().getGastos().addAll(Arrays.asList(newObj));
			viagemRepository.save(viagem.get());
		}
		return newObj;
	}

	private void updateData(Gasto newObj, Gasto obj) {
		newObj.setTipoGasto(obj.getTipoGasto());
		newObj.setValor(obj.getValor());
		newObj.setData(obj.getData());
		newObj.setDescricao(obj.getDescricao());
		newObj.setLocal(obj.getLocal());
	}
	
}
