xy-inc
========

Essa API possui com função a consulta de endereços, por meio do site dos Correios. A mesma fornece dois serviços:

* buscaCep: Passa-se como parâmetro o Cep e obtém como resultado um endereço.
* buscarEnd: Passa-se como parâmetro o logradouro e obtém como resultado uma lista de endereço.

Todos os resultados são exibidos no formato JSON

Arquitetura
========

A linguagem utilizada foi o Java 8, juntamente com o framework Spring e servidor TomCat 8. O sistema foi escrito com auxílio de IDE Eclipse Mars.

As dependências e build da aplicação são administradas pelo maven.

Build e execução
========

A build e execução pode ser feita por meio de uma IDE. Por padrão a porta que irá rodar o sistema será a porta 8080.

Foi criado um teste para testar a API, por meio do JUnit.

Serviços providos
========



[http://localhost:8080/xy-inc/buscaCep/13564030](http://localhost:8080/xy-inc/buscaCep/13564030)
 
e

[http://localhost:8080/xy-inc/buscarEnd/Rua%20Jos%C3%A9%20Duarte%20de%20Souza](http://localhost:8080/xy-inc/buscarEnd/Rua%20Jos%C3%A9%20Duarte%20de%20Souza)

Como por exemplo o resultado mostrado da primeira consulta é:

```xml

{"logradouros":
    [
     {"Logradouro":"Rua José Duarte de Souza",
	 "Localidade":"São Carlos/SP",
	 "Bairro":"Jardim Santa Paula",
	 "CEP":"13564-030"}
	 ]
}

```

Em caso ocorra um erro o resultado seria:

```xml

{"erro":"O Cep 13564g030 é inválido!"}

```




