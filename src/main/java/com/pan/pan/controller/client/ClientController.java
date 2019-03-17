package com.pan.pan.controller.client;

import com.pan.pan.controller.CollectionResponse;
import com.pan.pan.repository.address.model.Address;
import com.pan.pan.repository.client.model.Client;
import com.pan.pan.service.client.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api
@RestController
@RequestMapping(ClientController.URI_CLIENT)
public class ClientController {

    public static final String URI_CLIENT = "/client/";

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Method for search client by cpf", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client search ok", response = CollectionResponse.class),
            @ApiResponse(code = 404, message = "Empty result")})
    @RequestMapping(value = "searchByCpf", method = RequestMethod.GET)
    public ResponseEntity searchClient(@RequestParam("cpf") String cpf) {

        Client client = clientService.searchByCpf(cpf);

        ClientResponse newClient = ClientResponse.builder()
                .id(client.getId())
                .cpf(client.getCpf())
                .name(client.getName())
                .address(Address.builder()
                        .cep(client.getAddress().getCep())
                        .logradouro(client.getAddress().getLogradouro())
                        .localidade(client.getAddress().getLocalidade())
                        .bairro(client.getAddress().getBairro())
                        .uf(client.getAddress().getUf())
                        .build())
                .build();

        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(newClient);
    }

    @ApiOperation(value = "Method to find the address by cep", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cep search ok", response = CollectionResponse.class),
            @ApiResponse(code = 404, message = "Empty result")})
    @RequestMapping(value = "searchByCep", method = RequestMethod.GET)
    public ResponseEntity searchcep(@RequestParam("cep") String cep) {

        String address = clientService.searchCep(cep);

        if (address == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, String> mapa = new HashMap<>();

        Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(address);

        while (matcher.find()) {
            String[] group = matcher.group().split(":");
            mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
        }
        return ResponseEntity.ok().body(mapa);
    }

    @ApiOperation(value = "Method to find the states", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cep search ok", response = CollectionResponse.class),
            @ApiResponse(code = 404, message = "Empty result")})
    @RequestMapping(value = "searchByStates", method = RequestMethod.GET)
    //TODO Implementar metodo de busca de estado
    public ResponseEntity searchStates() {

        String states = clientService.searchStates();

        if (states == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(states);
    }

    @ApiOperation(value = "Method to find the counties", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Counties search ok", response = CollectionResponse.class),
            @ApiResponse(code = 404, message = "Empty result")})
    @RequestMapping(value = "searchByCounties", method = RequestMethod.GET)
    public ResponseEntity searchCounties(@RequestParam("counties") Integer countie) {

        String counties = clientService.searchCounties(countie);

        if (counties == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(counties);
    }

    @ApiOperation(value = "Update client address", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful client update", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "client not found"),
            @ApiResponse(code = 500, message = "Unhandled error")})
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateClientAddress(
            @Valid @RequestBody ClientRequest clientRequest) throws JSONException {

        Client newClient = Client.builder()
                .name(clientRequest.getName())
                .cpf(clientRequest.getCpf())
                .build();

        Client updateClientAddress = clientService.updateClientAddress(newClient, clientRequest.getCep());

        return ResponseEntity.ok().body(updateClientAddress);
    }
}
