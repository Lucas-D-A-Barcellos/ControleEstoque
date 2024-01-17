<h1>Sistema de Estoque de peças utilizando Java e MySQL</h1>
Este é um sistema de controle de estoque desenvolvido em Java que utiliza um banco de dados MySQL para armazenar informações, em especial, sobre peça. Além disso, o sistema faz uso das bibliotecas mysqlconnector e jcalendar para facilitar a conexão com o banco de dados e fornecer uma interface gráfica amigável para o usuário.

<h2>Pré-requisitos</h2>
<p></p>Antes de executar o sistema, certifique-se de ter os seguintes pré-requisitos instalados:  </p>



* Java Development Kit (JDK)
* MySQL
* Biblioteca mysqlconnector para Java
* Biblioteca jcalendar para Java (normalmente disponível como um arquivo .jar)
* Crie um banco de dados MySQL para o sistema de controle de estoque.

### Execute o script SQL fornecido para criar a tabela necessária no banco de dados:

```sql
  CREATE DATABASE estoque;
  USE estoque;
  
  CREATE TABLE controle_estoque (
      COD_PECA INT AUTO_INCREMENT PRIMARY KEY,
      NOME_PECA VARCHAR(255), 
      QUANT_PECA INT,
  );
```


### Altere os parâmetros do arquivo "ConectionString.xml", com as informações do seu banco de dados:

```xml
    //informações de conexão
    <connectionStrings>
	    <add name="MySQLConnection" connectionString="jdbc:mysql://localhost:3306/controle_estoque?user=root&amp;password=l123&amp;useSSL=false&amp;allowPublicKeyRetrieval=true" providerName="MySql.Data.MySqlClient" />
    </connectionStrings>
```

<h3>Uso do Sistema</h3>
<p></p></P>O sistema oferece funcionalidades para:  </p>

* Cadastrar novas peças e Excluir peças existentes no estoque;
* Retirar e Adicionar quantidade de peças no estoque;
* Verificar q quantidade de peças em uma tabela;

<hr>
<p>Se desejar contribuir para este projeto, sinta-se à vontade para implementar melhorias e enviar um pull request.</p>

