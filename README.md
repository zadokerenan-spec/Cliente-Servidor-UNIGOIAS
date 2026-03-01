# Cliente-Servidor-UNIGOIAS
Trabalho de Infraestrutura de Redes para fazer um Cliente-Servidor
# como compilar e executar o servidor java pelo terminal

este guia explica passo a passo como compilar e executar o arquivo `server.java` corretamente pelo terminal (powershell ou cmd), evitando o erro:

erro: não foi possível localizar nem carregar a classe principal server
causada por: java.lang.classnotfoundexception: server

---

## 1. verificar se o java está instalado

no terminal, digite:

java -version
javac -version

se aparecer a versão do java, está tudo certo.
se não aparecer, é necessário instalar o jdk.

---

## 2. acessar a pasta correta do projeto

no terminal, navegue até a pasta onde está o arquivo `server.java`.

exemplo:

cd C:\Users\Zado\Cliente-Servidor-UNIGOIAS\cliente-servidor\servidor

para confirmar que o arquivo está na pasta, utilize:

dir

deve aparecer o arquivo:

server.java

---

## 3. compilar o arquivo

agora compile o arquivo java com o comando:

javac server.java

se não aparecer nenhum erro, será gerado um novo arquivo chamado:

server.class

confirme usando:

dir

---

## 4. executar o servidor

agora execute o servidor com o comando:

java server

observações importantes:
- não coloque .class no comando
- o nome deve ser exatamente igual ao nome da classe principal
- letras maiúsculas e minúsculas fazem diferença

---

## 5. possíveis problemas e soluções

### erro: classnotfoundexception

esse erro normalmente acontece quando:

- o arquivo não foi compilado
- você não está na pasta correta
- o nome da classe dentro do código é diferente do nome do arquivo
- existe declaração de package no código

---

### caso exista package no início do arquivo

se o seu `server.java` começar com:

package servidor;

você precisa:

1. estar na pasta anterior à pasta servidor
2. compilar usando:

javac servidor/server.java

3. executar usando:

java servidor.server

---

## 6. estrutura básica correta do arquivo

o arquivo deve conter algo semelhante a:

public class server {
    public static void main(String[] args) {
        System.out.println("servidor iniciado");
    }
}

o nome da classe deve ser exatamente igual ao nome do arquivo.

---

## resumo do processo

1. acessar a pasta do arquivo
2. compilar com javac server.java
3. executar com java server

seguindo esses passos corretamente, o servidor será compilado e executado sem o erro de classe não encontrada.