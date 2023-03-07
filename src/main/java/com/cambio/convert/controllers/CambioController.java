package com.cambio.convert.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cambio.convert.dtos.CambioResponse;
import com.cambio.convert.dtos.FlashMessage;
import com.cambio.convert.exceptions.ValidatingException;
import com.cambio.convert.models.Cambio;
import com.cambio.convert.models.Moeda;
import com.cambio.convert.repository.MoedaRepository;
import com.cambio.convert.services.ConversorService;

import jakarta.validation.Valid;

@Controller
public class CambioController {

    @Autowired
    private ConversorService service;

    @Autowired
    private MoedaRepository moedaRepository;

//    @GetMapping("/")
//    public String main(Model model) {
//        model.addAttribute("cambioResponse", service.getMensagem());
//        return "main";
//    }
//
//    @PostMapping("converter")
//    public String converter(CambioResponse cambioResponse) {
//        service.converter(cambioResponse);
//        return "redirect:/";
//    }

//    @GetMapping("/")
//    public ModelAndView converter(CambioResponse cambioResponse) {
//        var modelAndView= new ModelAndView("main");
//        modelAndView.addObject("cambioResponse", new CambioResponse());
//        modelAndView.addObject("mensagem", service.getMensagem());
//        return modelAndView;
//    }

//    @ModelAttribute("moedas")
//    public Iterator<Moeda> getMoeda() {
//        List<Moeda> moeda= moedaRepository.findAll();
//        Iterator<Moeda> getMoeda= moeda.iterator();
//        return getMoeda;
//    }

//    @PostMapping("converter")
//    public String converter(
//        @Valid @ModelAttribute("cambioResponse") CambioResponse cambioResponse,
//        BindingResult result,
//        RedirectAttributes attrs) {
//
//        try {
//            service.converter(cambioResponse);
//            attrs.addFlashAttribute("alert",
//                new FlashMessage("alert-success", "Realizado com sucesso!"));
//        } catch (ValidatingException e) {
//            result.addError(e.getFieldError());
//            return "main";
//        }
//        return "redirect:/";
//    }
//
//    @PostMapping(value= "filtra")
//    public String filtra(Long cod, Model model) {
//        List<Cambio> cambios= service.filtra(cod);
//        model.addAttribute("cambios", cambios);
//        model.addAttribute("moedas", moedaRepository.findAll());
//        return "main";
//    }

    @GetMapping("/")
    public ModelAndView converter(CambioResponse cambioResponse) {
        var modelAndView= new ModelAndView("main");
        List<Cambio> cambioList= service.findAll();
        modelAndView.addObject("cambioResponse", new CambioResponse());
        modelAndView.addObject("cambioList", cambioList);
        modelAndView.addObject("moedas", moedaRepository.findAll());
        modelAndView.addObject("mensagem", service.getMensagem());
        modelAndView.addObject("cod", new Moeda());
        return modelAndView;
    }

    @PostMapping("converter")
    public String converter(
        @Valid @ModelAttribute("cambioResponse") CambioResponse cambioResponse,
        BindingResult result,
        RedirectAttributes attrs) {

        try {
            service.converter(cambioResponse);
            attrs.addFlashAttribute("alert",
                new FlashMessage("alert-success", "Realizado com sucesso!"));
        } catch (ValidatingException e) {
            result.addError(e.getFieldError());
            return "main";
        }
        return "redirect:/";
    }

    @PostMapping("filtra")
    public String converter(CambioResponse cambioResponse, Long cod, Model model) {
        List<Cambio> cambioList= service.filtra(cod);
        model.addAttribute("cambioResponse", new CambioResponse());
        model.addAttribute("cambioList", cambioList);
        model.addAttribute("moedas", moedaRepository.findAll());
        model.addAttribute("mensagem", service.getMensagem());
        model.addAttribute("cod", new Moeda());
        return "redirect:/";
    }

}