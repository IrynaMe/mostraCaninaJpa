package it.mario.controller;

import it.mario.entity.Cane;
import it.mario.entity.Proprietario;
import it.mario.service.ProprietariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProprietariController {
    @Autowired
    ProprietariService proprietariService;

    //menu
    @GetMapping("/")
    public ModelAndView viewIndex(Model model) {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    //form to insert id
    @GetMapping("/proprietari/insert")
    public ModelAndView insert(Model model) {
        ModelAndView mav = new ModelAndView("insertproprietario");
        return mav;
    }

    //table->show inserted proprietario
    @PostMapping(path = "/proprietari/inserito")
    public ModelAndView insert(
            @RequestParam(value = "codPartecipazione") Byte codPartecipazione,
            @RequestParam(value = "citta") String citta,
            @RequestParam(value = "cognome") String cognome,
            @RequestParam(value = "nome") String nome) {
        try {
            if (codPartecipazione == null || citta == null || cognome == null || nome == null) {
                throw new IllegalArgumentException("I parametri codPartecipazione, citta, cognome, nome non possono essere vuoti ");
            }
            Proprietario proprietario = new Proprietario(codPartecipazione, cognome, nome, citta);
            proprietariService.creaNewProprietario(proprietario);
            ModelAndView mav = new ModelAndView("insprop");
            mav.addObject("proprietario", proprietario);
            return mav;
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("errorMessage", "Errore inserimento" + e.getMessage());
            return mav;
        }
    }

/*        @GetMapping("/proprietari/report/list")
        public ResponseEntity<List<Proprietario>> getAllProprietari() {
            return new ResponseEntity<>(proprietariService.getAllProprietari(), HttpStatusCode.valueOf(200));
        }*/

    //show all proprietari
    @GetMapping("/proprietari/report")
    public ModelAndView getAllProprietari(Model model) {
        List<Proprietario> listProprietari = proprietariService.getAllProprietari();
        ModelAndView mav = new ModelAndView("allproprietari");
        mav.addObject("listProprietari", listProprietari);
        return mav;
    }


    /*    @GetMapping("/proprietari/{id}")
        public ResponseEntity<Proprietario> getProprietario(@PathVariable byte id) {
            return new ResponseEntity<>(proprietariService.getProprietario(id), HttpStatusCode.valueOf(200));
      }*/
    //form to insert id to find proprietario
    @GetMapping("/proprietari/id")
    public ModelAndView getProprietarioId(Model model) {
        ModelAndView mav = new ModelAndView("idprop");
        return mav;
    }

    //proprietario found by id
    @PostMapping("/proprietari/cerca")
    public ModelAndView getProprietario(@RequestParam(value = "codPartecipazione") byte codPartecipazione) {
        try {
            Proprietario proprietario = proprietariService.getProprietario(codPartecipazione);
            ModelAndView mav = new ModelAndView("singleprop");
            mav.addObject("proprietario", proprietario);
            return mav;
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("errorMessage", "Proprietario NON trovato " + e.getMessage());
            return mav;
        }
    }


    //form insert id to delete proprietario
    @GetMapping("/proprietari/delete")
    public ModelAndView idToDelete(Model model) {
        ModelAndView mav = new ModelAndView("delete");
        return mav;
    }

    //show deleted proprietario
    @PostMapping("/proprietari/cancellato")
    public ModelAndView cancellaProp(@RequestParam(value = "codPartecipazione") byte codPartecipazione) {
        try {
            Proprietario proprietario = proprietariService.getProprietario(codPartecipazione);
            ModelAndView mav = new ModelAndView("deleteproprietario");
            mav.addObject("proprietario", proprietario);
            proprietariService.deleteProprietario(codPartecipazione);
            return mav;
        } catch (NoSuchElementException e) {
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("errorMessage", "Codice NON trovato " + e.getMessage());
            return mav;
        }
    }


    //form to insert id to search cane of proprietario
    @GetMapping("/proprietari/canecerca")
    public ModelAndView idSearchDog(Model model) {
        ModelAndView mav = new ModelAndView("idcanecerca");
        return mav;
    }

    //show cane of proprietario
    @PostMapping("/proprietari/canetrovato")
    public ModelAndView dogFound(@RequestParam(value = "codPartecipazione") byte codPartecipazione) {
        try {
            Proprietario proprietario = proprietariService.getProprietario(codPartecipazione);
            Cane cane = proprietariService.getCaneByProprietario(codPartecipazione);
            ModelAndView mav = new ModelAndView("caneproptrovato");
            mav.addObject("proprietario", proprietario);
            mav.addObject("cane", cane);
            return mav;
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("errorMessage", "Proprietario e/o Cane con codice "
                    + codPartecipazione + " NON trovato: " + e.getMessage());
            return mav;
        }


    }//


}//
