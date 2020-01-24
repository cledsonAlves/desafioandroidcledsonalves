# desafioandroidcledsonalves
Desefio Android Santander


   ![Screenshot](desafio.png?)


Cadastre-se de forma gratuita no site: "https://developer.marvel.com/" para ter uma chave pública e privada.
Atenção na autenticação para consumo das APIs: "https://developer.marvel.com/documentation/authorization".
Utilize a documentação do site para obter as informações necessárias para o consumo das APIs.

Desafio: 

O desafio terá 3 telas:

1 - Listagem de personagens:

* Faça o consumo da API de listagem de personagens: "/v1/public/characters"; 
* Exiba o nome e foto de cada personagem;
* Ao selecionar o personagem, deverá direcionar para a tela de detalhes.


2 - Detalhes do Personagem:

* Exiba a imagem do personagem, o nome, a descrição e um botão de direcionamento para a uma tela que mostre qual a HQ mais cara daquele personagem.


3 - Detalhe da HQ mais cara do personagem:

* Faça o consumo da API de listagem de HQs por personagem: "/v1/public/characters/{characterId}/comics";
* Exiba na tela somente a revista mais cara daquele personagem com imagem, título, descrição e o preço.


Regras:

* Escolha um pattern de arquitetura que achar mais adequada para um projeto de grande porte;
* Faça o tratamento dos possíveis erros das APIs;
* Aplique testes unitários;
* Faça a paginação limitandio a 20 itens por página;
* Os campos de texto devem ter no máximo 3 linhas.
