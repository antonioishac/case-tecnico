# API para cadastrar bancos, estados e consumir uma API externa

#  Arquitetura do projeto
	Projeto desenvolvido com Spring Boot, Spring Data JPA com Hibernate,
	Validação com Bean Validation e mensagens configuradas em um arquivo .properties,
	Controlando as exceptions da API com ExceptionHandler utilizando a arquitetura RestControllerAdvice
	padronizando as respostas com a RFC 7807,
	Utilização do Criteria, CriteriaBuilder e CriteriaQuery
	Nivel de maturiade Richardson, utilização de URI, HTTP Methods e HATEOAS (Hypermedia).
	Banco de dados Oracle, MySql e h2 para testes da API, criação dos scripts com a utilização do Flyway,
	Documentação da Aplicação com Swagger.

##	Consumo de uma API externa
	O consumo de uma API externa foi utilizado o seguinte endereço: https://swapi.co/api/planets/
	Para consumir a API consulte a documentação swagger.

##	Branchs
	Caminho do repositório no github: https://github.com/antonioishac/case-tecnico
	
	# Projeto desenvolvido utilizando o banco de dados MySql: https://github.com/antonioishac/case-tecnico/tree/master

	# Projeto desenvolvido utilizando o banco de dados Oracle12c: https://github.com/antonioishac/case-tecnico/tree/dev-oracle

	# Foi desenvolvido em uma branch a utilização do banco de dados MySql para disponibilizar a API no Heroku.


##	Publicação do ambiente em um serviço cloud de hospedagens (Heroku)
	https://case-tecnico-api.herokuapp.com

##	Configurar a aplicação para rodar em um container
	Projeto configurado com para geração da imagem docker.
	Projeto publicado no docker hub: https://hub.docker.com/r/aishac/case-tecnico
	Comando para subir a imagem docker: docker run -p 8083:8083 -t --network host aishac/case-tecnico:03	

##	Documentação da API
	https://case-tecnico-api.herokuapp.com/swagger-ui.html
