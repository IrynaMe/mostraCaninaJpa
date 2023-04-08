package it.mario.service;

import it.mario.entity.Cane;
import it.mario.entity.Proprietario;

import java.util.List;

public interface ProprietariService {
    Proprietario creaNewProprietario(Proprietario proprietario);

    Proprietario updateproprietario(Proprietario proprietario);

    Proprietario getProprietario(byte id);

    void deleteProprietario(byte id);

    List<Proprietario> getAllProprietari();

    Cane getCaneByProprietario(byte id);
}
