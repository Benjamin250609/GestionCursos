package com.edutech.GestionCurso.repository;

import com.edutech.GestionCurso.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    public Curso findByrunProfesor(String runProfesor);
}
