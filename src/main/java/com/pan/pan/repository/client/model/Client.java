package com.pan.pan.repository.client.model;

import com.pan.pan.repository.address.model.Address;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Address> address;

    @Tolerate
    public Client() {
        // Method default for hibernate
    }

    @Builder
    public Client(Long id, String name, String cpf, List<Address> address) {
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
