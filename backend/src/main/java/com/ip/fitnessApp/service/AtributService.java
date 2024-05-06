package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.Atribut;
import com.ip.fitnessApp.repository.AtributRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AtributService {
    private final AtributRepository atributRepository;
    @Autowired
    public AtributService(AtributRepository atributRepository) {
        this.atributRepository = atributRepository;
    }

    public List<Atribut> findAll(){
        return atributRepository.findAll();
    }

    public Atribut findById(Integer id){
        return atributRepository.findById(id)
                .orElse(null);
    }

    public Atribut createAtribut(Atribut atribut) {
        //inventar.setDvorana(dvoranaRepository.findById(inventar.getDvorana().getIdDvorana()).orElse(null));
        return atributRepository.save(atribut);
    }

    public Atribut updateAtribut(Atribut newAtribut, Integer id) {
        Atribut atribut = atributRepository.findById(id)
                .orElse(null);
        if (atribut == null)
            return null;

        if (newAtribut.getNazivAtributa() != null)
            atribut.setNazivAtributa(newAtribut.getNazivAtributa());

        return atributRepository.save(atribut);
    }

    public boolean deleteById(Integer id) {
        Optional<Atribut> atribut = atributRepository.findById(id);
        if (atribut.isEmpty())
            return false;
        else{
            atributRepository.deleteById(id);
            return true;
        }
    }

}
