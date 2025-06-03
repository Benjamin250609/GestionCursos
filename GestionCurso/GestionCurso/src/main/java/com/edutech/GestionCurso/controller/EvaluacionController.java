package com.edutech.GestionCurso.controller;

import com.edutech.GestionCurso.model.Evaluacion;
import com.edutech.GestionCurso.service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluacion")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;


    @GetMapping
    public ResponseEntity<List<Evaluacion>> listar() {
        List<Evaluacion> evaluaciones = evaluacionService.findAll();
        if (evaluaciones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEvaluacion(@PathVariable Integer id) {
        return ResponseEntity.ok(evaluacionService.findById(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Evaluacion>> getEvaluacionPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(evaluacionService.findAll().stream()
                .filter(e -> e.getTipo().equals(tipo))
                .toList());
    }


    @PostMapping("/crear")
    public ResponseEntity<Evaluacion> save(@RequestBody Evaluacion evaluacion) {
        return ResponseEntity.ok(evaluacionService.save(evaluacion));
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Evaluacion> update(@PathVariable Integer id, @RequestBody Evaluacion evaluacion) {
        return ResponseEntity.ok(evaluacionService.update(id, evaluacion));
    }


    @DeleteMapping("/eliminar/{id}")
    public void delete(@PathVariable Integer id) {
        evaluacionService.delete(id);
    }
}