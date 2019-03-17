package com.pan.pan.service.client;

import com.pan.pan.repository.client.model.Client;
import org.json.JSONException;

public interface ClientService {

    Client findClientByCpf(String cpf);

    Client searchByCpf(String cpf);

    String searchCep(String cep);

    String searchStates();

    String searchCounties(Integer counties);

    Client updateClientAddress(Client client, String cep) throws JSONException;
}
