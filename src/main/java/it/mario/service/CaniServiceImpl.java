package it.mario.service;

import it.mario.entity.Cane;
import it.mario.entity.Proprietario;
import it.mario.repository.CaniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CaniServiceImpl implements CaniService {
    @Autowired
    CaniRepository caniRepository;

    @Override
    public Cane creaNewCane(Cane cane) {
        return caniRepository.save(cane);
    }

    @Override
    public Cane updateCane(Cane cane) {
        return null;
    }

    @Override
    public Cane getCane(byte codicepartecipazione) {
        return caniRepository.findById(codicepartecipazione).get();
    }

    @Override
    public void deleteCane(byte codicepartecipazione) {
        caniRepository.deleteById(codicepartecipazione);
    }

    @Override
    public List<Cane> getAllDogs() {
        return caniRepository.findAll();
    }


    @Override
    public Proprietario getProprietarioByCane(byte codicepartecipazione) {
        Cane cane = caniRepository.findById(codicepartecipazione).orElse(null);
        return cane.getProprietario();
    }
}//
