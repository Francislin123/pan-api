package com.pan.pan.controller.client;

import com.pan.pan.repository.address.model.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponse {

    private Long id;

    private String name;

    private String cpf;

    private Address address;
}
