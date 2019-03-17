package com.pan.pan.repository.address.model;

import com.pan.pan.repository.client.model.Client;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "public_place")
    private String logradouro;

    @Column(name = "locality")
    private String localidade;

    @Column(name = "neighborhood")
    private String bairro;

    @Column(name = "uf")
    private String uf;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Client> client;

    @Tolerate
    public Address() {
        // Method default for hibernate
    }

    @Builder
    public Address(Long id, String cep, String logradouro, String localidade, String bairro, String uf, List<Client> client) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.localidade = localidade;
        this.bairro = bairro;
        this.uf = uf;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", uf='" + uf + '\'' +
                ", client=" + client +
                '}';
    }
}
