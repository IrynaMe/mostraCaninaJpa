package it.mario.entity;

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
public class Cane {
    @Id
    private byte codPartecipazione;

    @Column(length = 40)
    private String razza;

    private byte eta;

    private char sex;

    private byte peso;

    private byte voto;

    @OneToOne(mappedBy = "cane")
    private Proprietario proprietario;

    public Cane(byte codPartecipazione, String razza, byte eta, char sex, byte peso, byte voto) {
        this.codPartecipazione = codPartecipazione;
        this.razza = razza;
        this.eta = eta;
        this.sex = sex;
        this.peso = peso;
        this.voto = voto;
    }
}//