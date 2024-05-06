package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.Atribut;
import com.ip.fitnessApp.model.Kategorija;
import com.ip.fitnessApp.repository.AtributRepository;
import com.ip.fitnessApp.repository.KategorijaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KategorijaService {
    private final KategorijaRepository kategorijaRepository;
    private final AtributRepository atributRepository;
    @Autowired
    public KategorijaService(KategorijaRepository kategorijaRepository, AtributRepository atributRepository) {
        this.kategorijaRepository = kategorijaRepository;
        this.atributRepository = atributRepository;
    }

    public List<Kategorija> findAll(){
        return kategorijaRepository.findAll();
    }

    public Kategorija findById(Integer id){
        return kategorijaRepository.findById(id)
                .orElse(null);
    }

    public Kategorija createKategorija(Kategorija kategorija) {
        //inventar.setDvorana(dvoranaRepository.findById(inventar.getDvorana().getIdDvorana()).orElse(null));
        return kategorijaRepository.save(kategorija);
    }

    public Kategorija updateKategorija(Kategorija newKategorija, Integer id) {
        Kategorija kategorija = kategorijaRepository.findById(id)
                .orElse(null);
        if (kategorija == null)
            return null;

        if (newKategorija.getNazivKategorije() != null)
            kategorija.setNazivKategorije(newKategorija.getNazivKategorije());
        if (newKategorija.getAtribut() != null){
            Optional<Atribut> a = atributRepository.findById(newKategorija.getAtribut()
                    .getAtributId());
            a.ifPresent(kategorija::setAtribut);
        }

        return kategorijaRepository.save(kategorija);
    }

    public boolean deleteById(Integer id) {
        Optional<Kategorija> kategorija = kategorijaRepository.findById(id);
        if (kategorija.isEmpty())
            return false;
        else{
            kategorijaRepository.deleteById(id);
            return true;
        }
    }

}
