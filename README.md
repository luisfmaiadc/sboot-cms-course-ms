<h1 align="center">sboot-cms-course-ms</h1>

<p align="center" style="margin-bottom: 20;">
    <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
    <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Springboot" />
    <img src="https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white" alt="Apache Maven" />
    <img src="https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white" alt="Postgres" /> 
    <img src="https://img.shields.io/badge/Flyway-CC0200.svg?style=for-the-badge&logo=Flyway&logoColor=white" alt="Flyway" />
</p>

<p align="center">O <b>sboot-cms-course-ms</b> é um microsserviço do <b>Sistema de Gerenciamento de Cursos</b>, responsável pelo gerenciamento de cursos. Ele permite cadastrar, atualizar, inativar e validar cursos, garantindo que somente cursos válidos e com instrutores ativos possam ser oferecidos aos alunos.</p>

<h2>📌 Visão Geral</h2>
<p align="justify">Este microsserviço opera de forma independente, possuindo seu próprio banco de dados PostgreSQL e se comunicando com outros serviços através do Eureka Service Discovery. Sua autenticação ocorre via API Gateway, permitindo requisições diretas sem necessidade de token para fins de testes e desenvolvimento.</p>

<h4>🔹 Regra de negócio</h4>

- Um curso pode ser cadastrado sem instrutor, mas, nesse caso, seu status de ativo será false.
- Se um curso está inativo e recebe um instrutor válido, ele é automaticamente ativado.
- Ao atribuir um instrutor a um curso, o sistema verifica se ele existe por meio da comunicação com o <code>sboot-cms-teacher-ms</code> utilizando Feign Client.

<h2>🚀 Tecnologias Utilizadas</h2>

- <b>Java 21 + Spring Boot 3.4.2</b>
- <b>Spring Web</b> (<code>spring-boot-starter-web</code>)
- <b>Spring Cloud OpenFeign</b> (comunicação entre microsserviços)
- <b>PostgreSQL</b> (banco de dados exclusivo do serviço)
- <b>Flyway</b> (para versionamento e gerenciamento de migrations)
- <b>Lombok</b> (redução de código boilerplate)
- <b>Eureka Client</b> (registro no Service Discovery)

<h2>🏗️ Estrutura do Projeto</h2>

```bash
sboot-cms-course-ms
│-- src/main/java/com/portfolio/luisfmdc/sboot_cms_course_ms
│   ├── client/           # Configuração do Feign Client para comunicação com sboot-cms-teacher-ms
│   ├── controller/       # Camada de controle (endpoints REST)
│   ├── domain/           # Classes de domínio e DTOs
│   ├── infra/exception/  # Tratamento personalizado de exceções
│   ├── repository/       # Interfaces de acesso ao banco de dados
│   ├── service/          # Regras de negócio e operações principais
│   ├── SbootCourseApplication.java  # Classe principal
│-- src/main/resources
│   ├── application.properties  # Configurações do serviço
│-- pom.xml  # Dependências do projeto
```

<h2>🛠️ Configuração e Execução</h2>

<h3>📌 Pré-requisitos</h3>
<p>Antes de iniciar o serviço, é necessário:</p>

1. Ter o <b>PostgreSQL</b> instalado e configurado.
2. Criar manualmente o banco de dados <code>dbCourse</code>, pois o PostgreSQL não cria automaticamente.
3. O Flyway se encarregará de criar as tabelas na primeira execução do serviço.

<h3>📜 Configuração do Banco de Dados (<code>application.properties</code>)</h3>

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dbCourse
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

<h3>🚀 Executando o Serviço</h3>

1. Clone o repositório e navegue até o diretório <code>sboot-cms-course-ms</code>:

```sh
git clone https://github.com/luisfmaiadc/sboot-cms-course-ms.git
cd sboot-cms-course-ms
```

2. Compile e execute o projeto com:

```sh
mvn clean install
mvn spring-boot:run
```

3. O serviço estará disponível na porta configurada em <code>application.properties</code> ou em uma porta aleatória devido ao uso do Eureka Client (padrão).

<h2>📡 Endpoints Principais</h2>

| Método | Endpoint | Descrição |
|     :---     |     :---      |      :---     |
| POST         | <code>/curso/cadastrar                   | Cadastrar um novo curso           |
| PUT          | <code>/curso/atualizar/{idCurso}</code>  | Atualizar dados de um curso       |
| DELETE       | <code>/curso/deletar/{idCurso}</code>    | Inativar um curso                 |
| GET          | <code>/curso/validar/{idCurso}</code>    | Validar um curso                  |

<h2>🔗 Comunicação com Outros Microsserviços</h2>

- O sboot-cms-course-ms utiliza Eureka Client para se registrar no Service Discovery, permitindo que outros microsserviços o localizem dinamicamente.
- Para validar a atribuição de instrutores aos cursos, o microsserviço consulta o sboot-cms-teacher-ms via <b>Feign Client</b>, localizado na pasta client/.

<h2>🔒 Autenticação</h2>

- A autenticação via JWT ocorre apenas quando as requisições passam pelo API Gateway.
- Para fins de teste, o microsserviço aceita requisições diretas sem necessidade de token.

<h2>📚 Mais Informações</h2>
<p>Para uma visão completa do sistema, incluindo a arquitetura, os demais microsserviços e detalhes sobre o desenvolvimento, acesse o README principal do projeto:</p>


➡️ [Visão Geral do Sistema](https://github.com/luisfmaiadc/pom-base-course-management-system) 