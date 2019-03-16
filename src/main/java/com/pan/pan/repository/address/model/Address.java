package com.pan.pan.repository.address.model;

import com.pan.pan.repository.client.model.Client;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "cep")
    private String cep;

    @Column(name = "public_place")
    private String logradouro;

    @Column(name = "complement")
    private String complement;

    @Column(name = "neighborhood")
    private String bairro;

    @Column(name = "locality")
    private String localidade;

    @Column(name = "uf")
    private String uf;

    @ManyToOne
    private Client client;

    @Tolerate
    public Address() {
        // Method default for hibernate
    }

    @Builder
    public Address(Long id, String street, String cep, String logradouro, String complement, String bairro, String localidade, String uf) {
        this.id = id;
        this.street = street;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complement = complement;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complement='" + complement + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
