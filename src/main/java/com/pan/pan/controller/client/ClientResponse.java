package com.pan.pan.controller.client;

import com.pan.pan.repository.address.model.Address;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientResponse {

    private Long id;

    private String name;

    private String cpf;

    private List<String> address;
}
