package com.Inventario.demo.Controllers;

import com. Inventario.demo.Repositories.ClientRepository;
import com.Inventario.demo.Entity.Client;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController{
    @Autowired
    private ClientRepository clientRepository;

    //localhost:2070/api/clients/{clientId}
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    //localhost:2070/api/clients/{clientId}
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object>  createClient(@Validated  @RequestBody Client request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        request.setCreatedAt(new Date());
        return new ResponseEntity<>(clientRepository.save(request),HttpStatus.CREATED);
    }

    @PutMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateClient(@PathVariable String clientId,@Validated @RequestBody Client request,BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (!optionalClient.isPresent()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente no encontrado");
        }
        request.setId(clientId);
        return new ResponseEntity<>(clientRepository.save(request),HttpStatus.OK);
    }

    //localhost:2070/api/clients/{clientId}
    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public Client deleteClient(@PathVariable String clientId) {
        Optional<Client> optionalProduct = clientRepository.findById(clientId);
        if (!optionalProduct.isPresent()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente no encontrado");
        }
        Client client = optionalProduct.get();
        clientRepository.delete(client);
        return client;
    }
}
