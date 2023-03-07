package com.cambio.convert.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.cambio.convert.models.Moeda;

@Service
public class MoedaLoader {

    public static void loadData(MoedaRepository repository) {

        List<Moeda> moeda= new ArrayList<>();

        moeda.add(new Moeda("USD-BRL"));
        moeda.add(new Moeda("BRL-USD"));
        moeda.add(new Moeda("EUR-BRL"));
        moeda.add(new Moeda("BRL-EUR"));
        moeda.add(new Moeda("GBP-BRL"));
        moeda.add(new Moeda("BRL-GBP"));
        moeda.add(new Moeda("ARS-BRL"));
        moeda.add(new Moeda("BRL-ARS"));
        moeda.add(new Moeda("CLP-BRL"));
        moeda.add(new Moeda("BRL-CLP"));
        repository.saveAll(moeda);

    }

    @Bean
    CommandLineRunner loader(MoedaRepository repository) {
        return (args) -> {
            MoedaLoader.loadData(repository);
        };
    }
}
