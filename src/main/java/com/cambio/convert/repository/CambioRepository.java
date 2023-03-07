package com.cambio.convert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cambio.convert.models.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {

    @Query("select c from Cambio c join c.moedas m where m.id = ?1")
    List<Cambio> findByMoeda(Long id);
}
