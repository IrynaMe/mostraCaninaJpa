package it.mario.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.mario.entity.Cane;
import it.mario.entity.Proprietario;
import it.mario.service.CaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CaniController {
    @Autowired
    CaniService caniService;

    //find all cani
    @GetMapping("/cani/report")
    @ResponseBody
    @JsonIgnore
    public ResponseEntity<List<Cane>> getAllCani() {
        List<Cane> caniList = caniService.getAllDogs();
        return new ResponseEntity<>(caniList, HttpStatus.OK);
    }

    //find cane by id
    @GetMapping("/cani/{id}")
    @ResponseBody
    public ResponseEntity<Cane> getCane(@PathVariable byte id) {
        Cane cane = caniService.getCane(id);
        return new ResponseEntity<>(cane, HttpStatus.OK);
    }

    //insert cane with postman
    @PostMapping(path = "/cani/insert")
    public ResponseEntity<Cane> insert(
            @RequestParam(value = "cod_partecipazione") byte codPartecipazione,
            @RequestParam(value = "razza") String razza,
            @RequestParam(value = "eta") byte eta,
            @RequestParam(value = "sex") char sex,
            @RequestParam(value = "peso") byte peso,
            @RequestParam(value = "voto") byte voto) {
        Cane cane = new Cane(codPartecipazione, razza, eta, sex, peso, voto);
        caniService.creaNewCane(cane);
        return ResponseEntity.ok(cane);
    }

    //delete cane by id
    @DeleteMapping("/cani/delete/{id}")
    @ResponseBody
    public ResponseEntity<Cane> deleteCane(@PathVariable byte id) {
        Cane cane = caniService.getCane(id);
        if (cane == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        caniService.deleteCane(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //find proprietario by cane
    @GetMapping("/cani/getproprietario/{id}")
    @ResponseBody
    public ResponseEntity<Proprietario> getProprietarioByCaneId(@PathVariable byte id) {
        Proprietario proprietario = caniService.getProprietarioByCane(id);
        return new ResponseEntity<>(proprietario, HttpStatus.OK);
    }

}//
