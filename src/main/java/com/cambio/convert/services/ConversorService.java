package com.cambio.convert.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cambio.convert.dtos.CambioResponse;
import com.cambio.convert.exceptions.ConversorException;
import com.cambio.convert.models.Cambio;
import com.cambio.convert.models.Moeda;
import com.cambio.convert.repository.CambioRepository;
import com.cambio.convert.repository.MoedaRepository;
import com.cambio.convert.utils.ConsumerApi;

import lombok.Data;

@Data
@Service
public class ConversorService {

    private String mensagem= "";

    @Autowired
    private RestTemplate restTemplate;

    private String URL;

    private Double cotacao;

    @Autowired
    private ConsumerApi consumer;

    @Autowired
    private CambioRepository cambioRepository;

    @Autowired
    private MoedaRepository moedaRepository;

    public List<Cambio> filtra(Long id) {
        return cambioRepository.findByMoeda(id);
    }

    public List<Cambio> findAll() {
        return cambioRepository.findAll();
    }

    public String converter(CambioResponse cambioResponse) throws ConversorException {

        try {

            Cambio cambios= consumer.getInfo();

            Cambio cambio= cambios;
            if (cambios.equals(null)) {
                mensagem= "Não existe nenhuma cotação";
            }
            Double cotacao= Double.valueOf(cambio.getBid());
            Double valorAConverter= cambioResponse.getValorAConverter();
            Double valorConvertido= null;

            List<Moeda> codigo= moedaRepository.findAll();

            if (codigo != null) {
                for (int i= 0; i < codigo.size() - 1; i++ ) {
                    if (codigo.get(i).getName()
                        .equals(codigo.get(i).getName())) {
                        valorConvertido= valorAConverter * cotacao;
                        mensagem= formatar(valorAConverter) + " reais em " +
                            codigo.get(i).getName() +
                            " é " +
                            formatar(valorConvertido);
                    } else {
                        valorConvertido= valorAConverter / cotacao;
                        mensagem= formatar(valorAConverter) +
                            codigo.get(i).getName() + " em reais é " +
                            formatar(valorConvertido);
                    }
                }
            }
        } catch (HttpClientErrorException.BadRequest e) {
            throw new ConversorException("Dado informado é inválido");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
        return mensagem;
    }

    private String formatar(Double numero) {
        return String.format("%.2f", numero);
    }
}