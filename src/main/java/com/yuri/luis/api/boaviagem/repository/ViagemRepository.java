package com.yuri.luis.api.boaviagem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuri.luis.api.boaviagem.model.Viagem;

public interface ViagemRepository extends MongoRepository<Viagem, String> {

}
