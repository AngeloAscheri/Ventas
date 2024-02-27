package com.example.demo.service;

import com.example.demo.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService<E> {

    List<Cliente> getAll()throws Exception;
    Optional<Cliente> ById(Long id)throws Exception;
    Cliente save (Cliente cliente)throws Exception;
    Optional<Cliente> update(Cliente cliente, Long id)throws Exception;
    boolean delete(Long id) throws Exception;

}
