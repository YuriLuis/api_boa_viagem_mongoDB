package com.yuri.luis.api.boaviagem.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.yuri.luis.api.boaviagem.model.Gasto;
import com.yuri.luis.api.boaviagem.model.Usuario;
import com.yuri.luis.api.boaviagem.model.Viagem;
import com.yuri.luis.api.boaviagem.repository.GastoRepository;
import com.yuri.luis.api.boaviagem.repository.UsuarioRepository;
import com.yuri.luis.api.boaviagem.repository.ViagemRepository;

@Configuration
public class Instanciacao implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ViagemRepository viagemRepository;
	
	@Autowired 
	private GastoRepository gastoRespository;
	

	@Override
	public void run(String... args) throws Exception {
		
		usuarioRepository.deleteAll();
		viagemRepository.deleteAll();
		gastoRespository.deleteAll();
		
		
		Usuario yuri = new Usuario(null, "yuri.luizg@hotmail.com", "123456789");
		Usuario jackeline = new Usuario(null, "jackeline98@hotmail.com", "123456789");
		
		Viagem saoPaulo = new Viagem(null, "São Paulo", "Negócios", "30/06/2020", null, 500.0, 1, yuri);
		Viagem rioDeJaneiro = new Viagem(null, "Rio de Janeiro", "Lazer", "30/06/2020", null, 1000.0, 2, jackeline);
		
		Gasto gasto1 = new Gasto(null, "Alimentação", 30.0, "30/06/2020", "Buff Livre", "Restaurante Tal");
		Gasto gasto2 = new Gasto(null, "Alimentação", 30.0, "30/06/2020", "Buff Livre", "Restaurante Tal");
		
		usuarioRepository.saveAll(Arrays.asList(yuri, jackeline));
		viagemRepository.saveAll(Arrays.asList(saoPaulo, rioDeJaneiro));
		gastoRespository.saveAll(Arrays.asList(gasto1,gasto2));
		
		gasto1.setIdViagem(saoPaulo.getIdViagem());
		gastoRespository.saveAll(Arrays.asList(gasto1,gasto2));
		saoPaulo.getGastos().addAll(Arrays.asList(gasto1));
		viagemRepository.saveAll(Arrays.asList(saoPaulo));
		
	}

}
