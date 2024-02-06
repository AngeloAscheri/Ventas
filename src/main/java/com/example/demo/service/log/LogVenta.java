package com.example.demo.service.log;

import com.example.demo.models.Venta;
import com.example.demo.repository.RepositoryVenta;
import com.example.demo.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LogVenta implements VentaService<Venta> {

    @Autowired
    private RepositoryVenta repo;
    @Override
    public List<Venta> findAll() throws Exception {
        try {
            return repo.findAll();
        } catch (Exception e){
            throw new Exception(e.getMessage() +
                    " "+ e.getCause());
        }
    }

    @Override
    public Venta findById(Long id) throws Exception {

        try {
            Optional<Venta> personaOptional= repo.findById(id);
            return personaOptional.get();

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Venta save(Venta entity) throws Exception {

        try {
            entity = repo.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Venta update(Long id, Venta entity) throws Exception {

        try {
            Optional<Venta> personaOptional = repo.findById(id);
            Venta cliente = personaOptional.get();
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
