<h1>FIAP Fase 2 : Atividade 3</h1>

<h3>Início</h3>

Prosseguindo com o nosso `track record`, é chegado o momento de adicionarmos serviços `RESTful` ao nosso projeto. Seguindo a mesma linha que foi elaborada no <a href="https://github.com/Amorim-cyber/fiap2_fase1_cap5">projeto do capítulo 5 da fase 1</a>, vamos elaborar um sistema que facilite o encontro de moradores de condomínios com prestadores de serviços. Nosso novo modelo de negócios ficou da seguinte forma:

<img src="assets/tabelas.PNG">

 O que muda desse modelo em relação ao anterior é que agora estamos adicionando acessos para a nossa aplicação. Tanto `Morador` quanto `prestador` vão precisar ter um login e uma senha de acesso caso desejem entrar para utilizar.

Criamos a <b>tb_usuario</b> para armazenar os acessos, <b>tb_usuario</b> e <b>tb_prestador</b> vão herdar os dados armazenados dentro da tabela.

A relação entre as tabelas será de 1x1. `Morador` e `Prestador`podem ter apenas um `Usuário de acesso`.

 <h3>OBJETIVO DO PROJETO:</h3>

Este projeto tem como objetivo mostrar as operações CRUD dessas entidade utilizando REST. O documento terá os seguintes passos: <b>1) Demonstrando os metodos GET, PUT, POST e DELETE; 2) Funcionalidades da aplicação; 3) Considerações finais / Instalação</b>

<hr>

 <h3>Demonstrando os metodos GET, PUT, POST e DELETE</h3>

Optei por mostrar os métodos utilizando a documentação do `swagger` pela sua praticidade. 

Estou utilizando o banco de dados `postgres` para realizar a armazenagem dos dados. As consultas estão sendo realizadas via `visual studios code` com uma extensão que possibilita a conexão com o banco.

Clique <a href="http://localhost:8084/moradoresPrestadores/swagger-ui" >aqui</a> para ter acesso a documentação. (:construction: link ainda local :construction:)   

* <B>POST</B>

  O comando POST possibilita incluir novos dados ao nosso banco, no exemplo abaixo estou incluindo um novo condomínio.

  <img src="assets/post1.PNG">

  

  Após executamos a solicitação, vamos receber o corpo do objeto criado e o codigo de sucesso 201.

   <img src="assets/post2.PNG">

  

  Configuramos o programa para não criar novos condomínios que tenham o mesmo número e cep existentes no banco. Caso haja alguma inclusão desse tipo, vamos receber um erro de código 422 informando que já existe um condomínio com estes dados.

   <img src="assets/post3.PNG">

  

  Consultando nosso banco, podemos observar que o condomínio foi inserido com sucesso.

  

  <img src="assets/post4.PNG">

  

* <B>GET</B>

  O comando GET tem o papel de retornar nossas entidades armazenadas no banco em formato de objeto, podemos retornar um ou mais elementos.

  No exemplo abaixo, retornamos apenas um único condomínio por meio da sua chave de identificação.

  

  <img src="assets/get1.PNG">

  

  Podemos chamar mais de um condomínio se quisermos.  

  

  <img src="assets/get2.PNG">

  

  Deixo aqui esse <a href="assets/GetAndPost.mp4">vídeo</a> demonstrando os métodos POST e GET.

  <i>Obs: No vídeo era para ter dito "postman" ao invés de "postgres" na hora de abrir o swagger</i>

* <b>POST</b>

  O comando POST atualiza dados de uma entidade já existente no banco. O programa irá retornar o elemento com os atributos alterados junto com o código 200 de sucesso. 

  <img src="assets/put1.PNG">

  

  Atenção quando for atualizar, o comando só irá ter êxito se preencher os dados da forma correta além de fornecer um id já existente no banco.  Veja <a href="assets/Put.mp4" >aqui</a> o vídeo de demonstração.

* <B>DELETE</B>

  O comando DELETE exclui uma determinada entidade dando seu id de identificação.



