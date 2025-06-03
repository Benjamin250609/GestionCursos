package com.edutech.GestionCurso.controller;

import com.edutech.GestionCurso.model.Resena;
import com.edutech.GestionCurso.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resena")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;


    @GetMapping
    public ResponseEntity<List<Resena>> listar() {
        List<Resena> resenas = resenaService.findAll();
        if (resenas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resenas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResena(@PathVariable Integer id) {
        return ResponseEntity.ok(resenaService.findById(id));
    }


    @PostMapping("/crear")
    public ResponseEntity<Resena> save(@RequestBody Resena resena) {
        return ResponseEntity.ok(resenaService.save(resena));
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Resena> update(@PathVariable Integer id, @RequestBody Resena resena) {
        return ResponseEntity.ok(resenaService.update(id, resena));
    }


    @DeleteMapping("/eliminar/{id}")
    public void delete(@PathVariable Integer id) {
        resenaService.delete(id);
    }
}