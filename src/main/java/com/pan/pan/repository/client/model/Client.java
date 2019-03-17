package com.pan.pan.repository.client.model;

import com.pan.pan.repository.address.model.Address;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @ManyToOne
    private Address address;

    @Tolerate
    public Client() {
        // Method default for hibernate
    }

    @Builder
    public Client(Long id, String name, String cpf, Address address) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name='" + name + '\'' + ", cpf='" + cpf + '\'' + '}';
    }
}
