package com.edutech.GestionCurso.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="curso")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_curso;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean estado;

    @Column(nullable = false)
    private String fecha_creacion;

    @Column(nullable = false)
    private Integer cantidad_cupos;

    @Column(nullable = false)
    private Integer cantidad_inscritos;

    @Column(nullable = false)
    private String runProfesor;



}
