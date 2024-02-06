package com.example.demo.service;

import java.util.List;

public interface ProductoService <E>{
    List<E> findAll() throws Exception;
    E findById(Long id) throws Exception;
    E save(E entity) throws Exception;
    E update(Long id, E entity) throws Exception;
    boolean delete(Long id) throws Exception;
}
