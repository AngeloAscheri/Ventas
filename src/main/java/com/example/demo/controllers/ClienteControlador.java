package com.example.demo.controllers;

import com.example.demo.models.Cliente;
import com.example.demo.repository.RepositoryCliente;
import com.example.demo.service.log.LogCliente;
import com.example.demo.service.log.LogProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
@RestController
public class ClienteControlador {

    @Autowired
    private LogCliente repo;

    @GetMapping
    public  String index(){
        return "Conectado";
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


    @PutMapping("modificar/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cliente entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repo.update(entity, id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error.\"}");
        }
    }


    @DeleteMapping("baja/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(repo.delete(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error.\"}");
        }
    }

    @Operation(summary = "creacion de nuevo usuario", description = "Crea nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operacion completada"),
            @ApiResponse(responseCode = "400", description = "parametros incorrectos",
                    content = {@Content(mediaType="application/json",
                            schema = @Schema(implementation = ErrorAttributes.class))}
            )})

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Cliente entity){
        try {
            return  ResponseEntity.status(HttpStatus.OK).
                    body(repo.save(entity));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("{\"error\":\"Error.Por favor intente mas tarde\"}");
        }
    }

}
