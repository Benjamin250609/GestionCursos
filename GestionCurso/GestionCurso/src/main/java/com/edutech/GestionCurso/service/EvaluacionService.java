package com.edutech.GestionCurso.service;


import com.edutech.GestionCurso.model.Evaluacion;
import com.edutech.GestionCurso.repository.EvaluacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EvaluacionService {

    @Autowired
    EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> findAll() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion findById(Integer id) {
        return evaluacionRepository.findById(id).get();
    }

    public Evaluacion save(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public void delete(Integer id) {
        evaluacionRepository.deleteById(id);
    }

    public Evaluacion update(Integer id, Evaluacion evaluacion) {

        Evaluacion evaluacionUpdate = evaluacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluacion no encontrada"));

        evaluacionUpdate.setTipo(evaluacion.getTipo());
        evaluacionUpdate.setNota_aprobacion(evaluacion.getNota_aprobacion());

        return evaluacionRepository.save(evaluacionUpdate);


    }

}
