package com.edutech.GestionCurso.service;

import com.edutech.GestionCurso.model.Contenido;
import com.edutech.GestionCurso.repository.ContenidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ContenidoService {

    @Autowired
    private ContenidoRepository contenidoRepository;

    public List<Contenido> findAll() {
        return contenidoRepository.findAll();
    }

    public Contenido findById(Integer id) {
        return contenidoRepository.findById(id).get();
    }

    public Contenido save(Contenido contenido) {
        return contenidoRepository.save(contenido);
    }

    public void delete(Integer id_contenido  ) {
        contenidoRepository.deleteById(id_contenido);
    }

    public Contenido update(Integer id_contenido, Contenido contenido) {
        Contenido contenidoUpdate = contenidoRepository.findById(id_contenido)
                .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));

        contenidoUpdate.setDescripcion(contenido.getDescripcion());
        contenidoUpdate.setTipo(contenido.getTipo());
        contenidoUpdate.setTitulo(contenido.getTitulo());

        return contenidoRepository.save(contenidoUpdate);
    }

}
