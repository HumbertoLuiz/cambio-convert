package com.cambio.convert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cambio.convert.models.Moeda;

public interface MoedaRepository extends JpaRepository<Moeda, Long> {

//    Set<Moeda> findByName(String name);
//
//    @Transactional
//    @Modifying(clearAutomatically= true, flushAutomatically= true)
//    @Query("delete from Moeda m where m.id = :id")
//    void removeMoeda(Long id);
//
//    List<Moeda> findByNameIgnoreCase(String name);
//
//    Moeda getRoleById(String id);
//
//    Moeda getRoleByName(String name);

}

