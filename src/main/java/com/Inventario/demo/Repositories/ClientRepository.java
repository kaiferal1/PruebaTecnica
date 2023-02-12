package com.Inventario.demo.Repositories;

import com.Inventario.demo.Entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ClientRepository extends MongoRepository<Client,String> {
}
