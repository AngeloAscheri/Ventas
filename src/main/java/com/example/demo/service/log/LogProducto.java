package com.example.demo.service.log;

import com.example.demo.models.Producto;
import com.example.demo.repository.RepositoryProducto;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LogProducto implements ProductoService<Producto> {

    @Autowired
    private RepositoryProducto repo;
    @Override
    public List<Producto> findAll() throws Exception {
        try {
            return repo.findAll();
        } catch (Exception e){
            throw new Exception(e.getMessage() +
                    " "+ e.getCause());
        }
    }

    @Override
    public Producto findById(Long id) throws Exception {

        try {
            Optional<Producto> personaOptional= repo.findById(id);
            return personaOptional.get();

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Producto save(Producto entity) throws Exception {

        try {
            entity = repo.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Producto update(Long id, Producto entity) throws Exception {

        try {
            Optional<Producto> personaOptional = repo.findById(id);
            Producto cliente = personaOptional.get();
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
