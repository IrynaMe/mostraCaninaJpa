package it.mario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
@Entity
public class Proprietario {
    @Id
    private byte codPartecipazione;

    private String cognome;

    private String nome;

    private String citta;

    @OneToOne
    @JoinColumn(name = "codPartecipazione", referencedColumnName = "codPartecipazione")
    private Cane cane;

    public Proprietario(byte codPartecipazione, String cognome, String nome, String citta) {
        this.codPartecipazione = codPartecipazione;
        this.cognome = cognome;
        this.nome = nome;
        this.citta = citta;
    }

    //!!to avoid loop when serializing obj to JSON +@JsonIgnore in CaniController getAllCani method
    @JsonIgnore
    public Cane getCane() {
        return cane;
    }

}//