package com.pan.pan.service.client;

import com.pan.pan.exceptions.UserException;
import com.pan.pan.repository.address.AddressRepository;
import com.pan.pan.repository.address.model.Address;
import com.pan.pan.repository.client.ClientRepository;
import com.pan.pan.repository.client.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Client searchByCpf(String cpf) {

        Client client = findClientByCpf(cpf);

        return client;
    }

    @Override
    public String searchCep(String cep) {

        String json;

        try {

            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");

            URLConnection urlConnection = url.openConnection();

            InputStream is = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));

            json = jsonSb.toString();

        } catch (Exception e) {
            throw new UserException("Cep not found", e);
        }

        log.info("Success" + json);

        return json;
    }

    @Override
    public String searchStates() {

        String json;

        try {

            URL url = new URL("https://servicodados.ibge.gov.br/api/v1/localidades/estados/");

            URLConnection urlConnection = url.openConnection();

            InputStream is = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));

            json = jsonSb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return json;
    }

    @Override
    public String searchCounties(Integer counties) {

        String json;

        try {

            URL url = new URL("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + counties + "/municipios/");

            URLConnection urlConnection = url.openConnection();

            InputStream is = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));

            json = jsonSb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return json;
    }

    @Override
    public Client updateClientAddress(Client client, String cep) throws JSONException {

        Client findClient = findClientByCpf(client.getCpf());

        Address findAddressByCep = findAddress(cep, findClient);

        Client newClient = Client.builder()
                .id(findClient.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .address(findAddressByCep).build();

        Client saveClient = clientRepository.save(newClient);

        return saveClient;
    }

    private Address findAddress(String cep, Client client) throws JSONException {

        String addreesJson = searchCep(cep);

        JSONObject myResponse = new JSONObject(addreesJson);

        Address address = addressRepository.findByCep(client.getAddress().getCep()).orElseThrow(() ->
                new UserException(String.format("Address is not found for cep='%s' ", cep)));

        Address updateAddress = Address.builder()
                .id(address.getId())
                .cep(myResponse.getString("cep"))
                .logradouro(myResponse.getString("logradouro"))
                .bairro(myResponse.getString("bairro"))
                .localidade(myResponse.getString("localidade"))
                .uf(myResponse.getString("uf"))
                .build();

        Address addressNow = addressRepository.save(updateAddress);

        return addressNow;
    }

    @Override
    public Client findClientByCpf(String cpf) {
        return clientRepository.findByCpf(cpf).orElseThrow(() -> new UserException(String.format("Client is not found for cpf='%s' ", cpf)));
    }
}
