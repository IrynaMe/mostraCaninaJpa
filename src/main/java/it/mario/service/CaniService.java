package it.mario.service;

import it.mario.entity.Cane;
import it.mario.entity.Proprietario;

import java.util.List;

public interface CaniService {
    // public abstract
    Cane creaNewCane(Cane cane);

    Cane updateCane(Cane cane);

    Cane getCane(byte codicepartecipazione);

    void deleteCane(byte codicepartecipazione);

    List<Cane> getAllDogs();

    Proprietario getProprietarioByCane(byte codicepartecipazione);

}
