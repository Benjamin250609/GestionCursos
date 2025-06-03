package com.edutech.GestionCurso.service;
import com.edutech.GestionCurso.model.Resena;
import com.edutech.GestionCurso.repository.ResenaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ResenaService {

    @Autowired
    ResenaRepository resenaRepository;

    public List<Resena> findAll() {
        return resenaRepository.findAll();
    }

    public Resena findById(Integer id) {
        return resenaRepository.findById(id).get();
    }

    public Resena save(Resena resena) {
        return resenaRepository.save(resena);
    }

    public void delete(Integer id) {
        resenaRepository.deleteById(id);
    }

    public Resena update(Integer id, Resena resena) {

        Resena resenaUpdate = resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resena no encontrada"));

        resenaUpdate.setComentario(resena.getComentario());
        resenaUpdate.setCalificacion_resena(resena.getCalificacion_resena());
        resenaUpdate.setCurso(resena.getCurso());


        return resenaRepository.save(resenaUpdate);
    }




}
