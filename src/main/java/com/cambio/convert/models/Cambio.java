package com.cambio.convert.models;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded= true)
@ToString(onlyExplicitlyIncluded= true)
public class Cambio implements Serializable {

    private static final long serialVersionUID= 1L;

    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String bid;

    @Column
    private String tipoConversao;

    @Column
    private Double valorAConverter;

    @ManyToMany
    @JoinTable(name= "cambio_codigo", joinColumns= {
            @JoinColumn(name= "cambio_id") }, inverseJoinColumns= {
                    @JoinColumn(name= "codigo_id") })
    private Set<Moeda> moedas;
}
