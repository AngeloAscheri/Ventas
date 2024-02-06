package com.example.demo.service.log;

import com.example.demo.models.Cliente;
import com.example.demo.repository.RepositoryCliente;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogCliente implements ClienteService<Cliente> {

    @Autowired
    private RepositoryCliente repo;

    @Override
    public List<Cliente> findAll() throws Exception {
        try {
            List<Cliente> entities = repo.findAll();
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage() +
                    " "+ e.getCause());
        }
    }

    @Override
    public Cliente findById(Long id) throws Exception {

        try {
            Optional<Cliente> personaOptional= repo.findById(id);
            return personaOptional.get();

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Cliente save(Cliente entity) throws Exception {

        try {
            entity = repo.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Cliente update(Long id, Cliente entity) throws Exception {

        try {
            Optional<Cliente> personaOptional= repo.findById(id);
            Cliente cliente = personaOptional.get();
            return cliente = repo.save(entity);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            if (repo.existsById(id)){
                repo.deleteById(id);
                return true;}
            else throw new Exception("Error al borrar");
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
