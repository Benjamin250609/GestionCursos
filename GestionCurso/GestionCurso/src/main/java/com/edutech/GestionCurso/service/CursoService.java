package com.edutech.GestionCurso.service;


import com.edutech.GestionCurso.model.Curso;
import com.edutech.GestionCurso.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.client.HttpServerErrorException;

@Service
@Transactional
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso findById(Integer id) {
        return cursoRepository.findById(id).get();
    }

    public Curso save(Curso curso) {

        curso.setFecha_creacion(LocalDateTime.now());

        return cursoRepository.save(curso);
    }

    public void delete(Integer id) {
        cursoRepository.deleteById(id);
    }

    public Curso update(Integer id, Curso curso) {
        Curso cursoUpdate = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        cursoUpdate.setCantidad_cupos(curso.getCantidad_cupos());
        cursoUpdate.setCantidad_inscritos(curso.getCantidad_inscritos());
        cursoUpdate.setFecha_creacion(LocalDateTime.now());
        cursoUpdate.setDescripcion(curso.getDescripcion());
        cursoUpdate.setEstado(curso.getEstado());
        cursoUpdate.setTitulo(curso.getTitulo());
        cursoUpdate.setRunProfesor(curso.getRunProfesor());


        return cursoRepository.save(cursoUpdate);
    }

public String obtenerCursoConInstructor(String run_profesor) {
    try {
        String instructorUrl = "http://localhost:8082/api/v1/instructores/" + run_profesor;
        String instructorData = restTemplate.getForObject(instructorUrl, String.class);

        Curso curso = cursoRepository.findByrunProfesor(run_profesor);
        
        if (curso == null) {
            return "No se encontró el curso para el profesor con RUN: " + run_profesor;
        } else {
            StringBuilder resultado = new StringBuilder();
            resultado.append("Curso: \n");
            resultado.append("\n -Curso.").append(curso.getTitulo())
                    .append("\n -Descripcion.").append(curso.getDescripcion())
                    .append("\n -Estado.").append(curso.getEstado())
                    .append("\n -Cantidad cupos.").append(curso.getCantidad_cupos())
                    .append("\n -Cantidad inscritos").append(curso.getCantidad_inscritos()).append("\n");
            resultado.append("Instructor: \n");
            resultado.append(instructorData);
            return resultado.toString();
        }
    } catch (HttpServerErrorException e) {
        return "El instructor con RUN " + run_profesor + " no existe en el sistema";
    }
}



    public Curso CambiarEstadoCurso(Integer idCurso, Boolean estado) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        curso.setEstado(estado);
        return cursoRepository.save(curso);
    }

    public String CuposDisponibles(Integer idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        if (curso.getCantidad_cupos() - curso.getCantidad_inscritos() > 0)
            return "Quedan " + (curso.getCantidad_cupos() - curso.getCantidad_inscritos()) + " cupos disponibles";
        else
            return "No quedan cupos disponibles";
    }

}