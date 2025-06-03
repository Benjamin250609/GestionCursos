package com.edutech.GestionCurso.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reseña")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reseña;

    @Column(nullable = false)
    private String comentario;

    @Column(nullable = false)
    private Integer calificacion_resena;

    @Column(nullable = false)
    private String fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @Column(nullable = false)
    private String run_estudiante;




}
