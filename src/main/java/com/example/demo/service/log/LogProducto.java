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
    public List<Producto> getAll() throws Exception {
        try {
            return repo.findAll();
        } catch (Exception e){
            throw new Exception(e.getMessage() +
                    " "+ e.getCause());
        }
    }

    @Override
    public Optional<Producto> byId(Long id) throws Exception {
        try {

            return repo.findById(id);

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Producto save(Producto producto) throws Exception {

        try {
            return repo.save(producto);

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Producto> update(Producto producto) throws Exception {
        try {

            if (repo.existsById(producto.getProductoId())){
                repo.save(producto);

            } else System.out.println("No se pudo completar la operación");

            return repo.findById(producto.getProductoId());
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
