package com.pan.pan.controller.client;

import lombok.Data;

@Data
public class ClientRequest {

    private String name;

    private String cpf;

    private String cep;

    private String logradouro;

    private String localidade;

    private String bairro;

    private String uf;
}
