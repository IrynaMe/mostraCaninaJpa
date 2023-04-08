package it.mario.service;

import it.mario.entity.Cane;
import it.mario.entity.Proprietario;
import it.mario.repository.ProprietaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProprietariServiceImpl implements ProprietariService {
    @Autowired
    ProprietaryRepository proprietaryRepository;

    @Override
    public Proprietario creaNewProprietario(Proprietario proprietario) {
        return proprietaryRepository.save(proprietario);
    }

    @Override
    public Proprietario updateproprietario(Proprietario proprietario) {
        return null;
    }

    @Override
    public Proprietario getProprietario(byte id) {
        return proprietaryRepository.findById(id).get();
    }

    @Override
    public void deleteProprietario(byte id) {
        proprietaryRepository.deleteById(id);
    }

    @Override
    public List<Proprietario> getAllProprietari() {
        return proprietaryRepository.findAll();
    }

    @Override
    public Cane getCaneByProprietario(byte codicepartecipazione) {
        Proprietario proprietario = proprietaryRepository.findById(codicepartecipazione).get();
        return proprietario.getCane();
    }
}//
