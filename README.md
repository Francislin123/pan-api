
# Pan Api

Esta aplicação é responsável pela busca de endereço pelo cep e atualizaçao do usuário


# Technologies
  
- Java 8
  
- Maven 4.0.0 
- Spring Boot 2.0.8.RELEASE
- Spring Fox 2.7.0
- Spring Security 5.0.7

- Lombok 1.18.0
  
- Hibernate Core 5.2.17.Final  
- H2 Sql

### Para rodar a aplicação é só seguir os passos abaixo.

- 1 - Entrar na pasta onde está o jar do projeto
- 2 - Executar o seguinte comando pelo cmd

- java -jar pan-0.0.1-SNAPSHOT.jar
   
### Documentação da API (Swagger) And URL For access H2
- Link para acesso local: http://localhost:8077/pan-api/v1/swagger-ui.html#/
- http://localhost:8077/pan-api/v1/h2/

### Insert dos dados no H2:
insert
	into
		ADDRESS (ID ,
		NEIGHBORHOOD ,
		CEP ,
		LOCALITY ,
		PUBLIC_PLACE ,
		UF)
	values (1,
	'Vila Gomes Cardim',
	'03317000',
	'São Paulo',
	'Rua Serra De Botucatu',
	'Teste');

insert
	into
		CLIENT (ID ,
		CPF ,
		name,
		ADDRESS_ID)
	values (1,
	'40208053859',
	'Frans',
	1);	

### Você também pode:

- Solicitação de mesclagem no ramo mestre após ser aplicada na produção
- Todas as histórias precisam se ramificar da homologação
- Toda tarefa precisa de um ramo da história
- A fusão dos ramos das tarefas deve ser feita no equivalente
- Um membro que desenvolveu o história será responsável pela solicitação de mesclagem na homologação