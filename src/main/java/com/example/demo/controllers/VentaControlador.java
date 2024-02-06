package com.example.demo.controllers;

import com.example.demo.models.Venta;
import com.example.demo.repository.RepositoryVenta;
import com.example.demo.service.log.LogVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="/venta")
public class VentaControlador {

    @Autowired
    private LogVenta repo;

    @GetMapping
    public  String index(){
        return "Conectado";
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try {
            return  ResponseEntity.status(HttpStatus.OK).
                    body(repo.findAll());

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("{\"error\":\"Error.No se encontró registro.\"}");
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return  ResponseEntity.status(HttpStatus.OK).
                    body(repo.findById(id));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("{\"error\":\"Error.No se encontró registro.\"}");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody Venta entity){
        try {
            return  ResponseEntity.status(HttpStatus.OK).
                    body(repo.save(entity));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("{\"error\":\"Error.Por favor intente mas tarde\"}");
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Venta entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repo.update(id,entity));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error.Por favor intente mas tarde\"}");
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(repo.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error.Por favor intente mas tarde\"}");
        }

    }
}
