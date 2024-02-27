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
    public List<Cliente> getAll() throws Exception {
        try {
            return repo.findAll();
        } catch (Exception e){
            throw new Exception(e.getMessage() +
                    " "+ e.getCause());
        }
    }

    @Override
    public Optional<Cliente> ById(Long id) throws Exception {
        try {
            return repo.findById(id);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Cliente save(Cliente cliente) throws Exception {

        try {
            return repo.save(cliente);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Cliente> update(Cliente cliente, Long id) throws Exception {

        try {
            if (repo.existsById(id)){
                repo.save(cliente);
            } else System.out.println("No se pudo completar la operación");

            return repo.findById(id);
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
