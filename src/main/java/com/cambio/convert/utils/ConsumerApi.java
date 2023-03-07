package com.cambio.convert.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cambio.convert.models.Cambio;
import com.cambio.convert.models.Moeda;
import com.cambio.convert.repository.MoedaRepository;

import lombok.Data;

@Data
@Service
public class ConsumerApi {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MoedaRepository repository;

    private String URL;
    private String atual;
    private String proximo;

    public Cambio getInfo() {

        List<Moeda> codigo= repository.findAll();

        try {
            URL= "https://economia.awesomeapi.com.br/" + codigo.get(0).getName();
            for (int i= 0; i < codigo.size() - 1; i++ ) {
                proximo= URL + codigo.get(i + 1).getName();
                atual= proximo + "/";
            }
        } catch (IndexOutOfBoundsException e) {
            e.getMessage();
        }
        Cambio[] responseEntity= restTemplate.getForObject(URL, Cambio[].class);
        return responseEntity[0];
    }
}

