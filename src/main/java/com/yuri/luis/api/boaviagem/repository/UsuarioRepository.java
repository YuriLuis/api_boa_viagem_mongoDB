package com.yuri.luis.api.boaviagem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuri.luis.api.boaviagem.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}
