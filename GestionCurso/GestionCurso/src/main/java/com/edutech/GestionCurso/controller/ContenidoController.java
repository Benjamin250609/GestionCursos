package com.edutech.GestionCurso.controller;

import com.edutech.GestionCurso.model.Contenido;
import com.edutech.GestionCurso.service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contenido")
public class ContenidoController {

    @Autowired
    private ContenidoService contenidoService;


    @GetMapping
    public ResponseEntity<List<Contenido>> listar() {
        List<Contenido> contenidos = contenidoService.findAll();
        if (contenidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contenidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContenido(@PathVariable Integer id) {
        return ResponseEntity.ok(contenidoService.findById(id));
    }


    @PostMapping("/crear")
    public ResponseEntity<Contenido> save(@RequestBody Contenido contenido) {
        return ResponseEntity.ok(contenidoService.save(contenido));
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Contenido> update(@PathVariable Integer id, @RequestBody Contenido contenido) {
        return ResponseEntity.ok(contenidoService.update(id, contenido));
    }


    @DeleteMapping("/eliminar/{id}")
    public void delete(@PathVariable Integer id) {
        contenidoService.delete(id);
    }
}