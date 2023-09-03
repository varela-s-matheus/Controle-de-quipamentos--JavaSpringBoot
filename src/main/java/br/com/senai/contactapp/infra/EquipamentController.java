package br.com.senai.contactapp.infra;

import br.com.senai.contactapp.domain.equipament.Equipament;
import br.com.senai.contactapp.domain.equipament.EquipamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/equipaments")
public class EquipamentController {

    @Autowired
    private EquipamentService service;

    @GetMapping("/form/{id}")
    public ModelAndView form(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("equipaments/form");
        if ("novo".equals(id)) {
            mv.addObject("equipament", Equipament.builder().build());
            mv.addObject("message", "");
        } else {
            Equipament equipament = service.findById(UUID.fromString(id));
            mv.addObject("equipament", equipament != null ?
                    equipament :
                    Equipament.builder().build());
            mv.addObject("message", equipament!= null ? "" : "Equipamento não encontrado" );
        }
        return mv;
    }

    @GetMapping("/form")
    public ModelAndView showNewFormContact() {
        ModelAndView mv = new ModelAndView("equipaments/form");
        mv.addObject("message", "");
        mv.addObject("equipament", Equipament.builder().build());
        return mv;
    }

    @PostMapping("/saveEquipament")
    public ModelAndView save(@ModelAttribute Equipament equipament) {
        ModelAndView mv = new ModelAndView("equipaments/form");
        boolean result = service.save(equipament);
        String message = result ? "Equipamento salvo com sucesso" : "Não foi possível salvar o registro";
        mv.addObject("message", message);
        mv.addObject("equipament", equipament);
        return mv;
    }

    @GetMapping()
    public ModelAndView listAll() {
        ModelAndView mv = new ModelAndView("equipaments/list");
        mv.addObject("equipaments", service.findAll());
        mv.addObject("message", "");
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView remove(@PathVariable UUID id) {
        ModelAndView mv = new ModelAndView("equipaments/list");
        boolean result = service.removeEquipament(id);
        String message = result ? "Equipamento removido com sucesso" : "Não foi possível remover o registro";
        mv.addObject("equipaments", service.findAll());
        mv.addObject("message", message);
        return mv;
    }

}
