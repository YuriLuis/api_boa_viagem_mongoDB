package com.yuri.luis.api.boaviagem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuri.luis.api.boaviagem.model.Gasto;

public interface GastoRepository extends MongoRepository<Gasto, String> {

}
