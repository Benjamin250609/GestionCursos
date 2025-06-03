package com.edutech.GestionCurso.controller;

import com.edutech.GestionCurso.model.Curso;
import com.edutech.GestionCurso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;


    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        List<Curso> cursos = cursoService.findAll();
        if (cursos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Curso> getCurso(@PathVariable Integer id) {
        return ResponseEntity.ok(cursoService.findById(id));
    }

    @GetMapping("/{run}")
    public ResponseEntity<?> getCursoConInstructor(@PathVariable String run) {
        return ResponseEntity.ok(cursoService.obtenerCursoConInstructor(run));
    }

    @GetMapping("/cupos/{id}")
    public ResponseEntity<String> cuposDisponibles(@PathVariable Integer id) {
        return ResponseEntity.ok(cursoService.CuposDisponibles(id));
    }


    @PostMapping("/crear")
    public ResponseEntity<Curso> save(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.save(curso));
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Curso> update(@PathVariable Integer id, @RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.update(id, curso));
    }

    @PutMapping("/estado/{id}/{estado}")
    public ResponseEntity<Curso> cambiarEstado(@PathVariable Integer id, @PathVariable Boolean estado) {
        return ResponseEntity.ok(cursoService.CambiarEstadoCurso(id, estado));
    }


    @DeleteMapping("/eliminar/{id}")
    public void delete(@PathVariable Integer id) {
        cursoService.delete(id);
    }
}