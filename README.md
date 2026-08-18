# Leafy 🌿
<img width="100" height="100" alt="ChatGPT Image 13 de ago  de 2026, 11_00_47" src="https://github.com/user-attachments/assets/3940561a-bef0-419d-b680-451aa7e4453d" />

(RESUMO)

## Sumário
- [1. Introdução](#1-introdução)
- [2. Visão Geral do Sistema](#2-visão-geral-do-sistema)
- [3. Escopo e Requisitos](#3-escopo-e-requisitos)
  - [3.1 Escopo do Projeto](#31-escopo-do-projeto)
  - [3.2 História de Usuários](#32-história-de-usuários)
  - [3.3 Requisitos Funcionais](#33-requisitos-funcionais)
  - [3.4 Requisitos Não Funcionais](#34-requisitos-não-funcionais)
- [4. Modelo de Dados](#4-modelo-de-dados)
  - [4.1 Diagrama Relacional - DER](#41-diagrama-relacional---der)
  - [4.2 Repositório do Script de Banco de Dados](#42-repositório-do-script-de-banco-de-dados)
- [5. Arquitetura do Sistema](#5-arquitetura-do-sistema)
  - [5.1 Descrição do Padrão Arquitetural](#51-descrição-do-padrão-arquitetural)
  - [5.2 Wireframes](#52-wireframes)
  - [5.3 Repositório do Código Fonte](#53-repositório-do-código-fonte)
  - [5.4 Publicação da Aplicação (Deploy)](#54-publicação-da-aplicação-deploy)
  - [5.5 Teste Unitário](#55-teste-unitário)
    - [5.5.1 Casos de Testes](#551-casos-de-testes)
- [6. Resultados](#6-resultados)
- [7. Conclusão](#7-conclusão)
- [Referências](#referências)

<a id="introducao"></a>
## 1. Introdução
O descarte incorreto de resíduos é um problema que pode gerar impactos ambientais e sociais, principalmente quando as pessoas não possuem informações suficientes sobre a forma adequada de destinar diferentes tipos de materiais. Nesse contexto, a tecnologia pode ser utilizada como uma ferramenta para facilitar o acesso à informação e auxiliar na adoção de práticas mais sustentáveis.

Diante desse cenário, foi desenvolvido o aplicativo **Leafy**, com o objetivo de auxiliar os usuários no descarte correto de resíduos e facilitar o acesso a informações relacionadas à destinação adequada dos materiais. O projeto busca utilizar recursos da área de desenvolvimento de sistemas para aproximar a tecnologia de uma necessidade ambiental presente no cotidiano.

Como objetivo geral, o Leafy busca desenvolver uma solução que auxilie o usuário a identificar a forma adequada de descarte dos resíduos e a encontrar locais apropriados para sua destinação. Para alcançar esse objetivo, o sistema conta com recursos voltados à consulta e identificação de resíduos, localização de pontos de coleta e disponibilização de informações relacionadas à reciclagem.

A escolha do tema justifica-se pela necessidade de facilitar o acesso a informações sobre o descarte adequado e incentivar práticas sustentáveis. Dessa forma, o projeto relaciona o desenvolvimento de software a uma questão ambiental e social, buscando oferecer uma solução prática para uma dificuldade presente no cotidiano.

<a id="visao-geral"></a>
## 2. Visão Geral do Sistema
O Leafy é um aplicativo Android desenvolvido para auxiliar os usuários no descarte adequado de resíduos, reunindo diferentes recursos em uma única plataforma. O sistema utiliza informações sobre os materiais e recursos de localização para orientar o usuário sobre como e onde realizar o descarte de seus resíduos.

Entre suas principais funcionalidades, o Leafy permite que o usuário consulte produtos para obter informações sobre seu tipo de resíduo, reciclabilidade e instruções de descarte. O sistema também conta com um recurso de escaneamento de produtos, que possibilita a identificação do material e a consulta das informações correspondentes no banco de dados. Dessa forma, o usuário pode obter informações que auxiliem na identificação dos materiais e na compreensão das formas adequadas de descarte. Outra funcionalidade é o mapa interativo com pontos de descarte organizados de acordo com o tipo de resíduo. Por meio da geolocalização, o usuário pode visualizar locais próximos disponíveis e compatíveis com o tipo de lixo que deseja despejar. O sistema também permite a utilização de filtros de acordo com o tipo de resíduo, facilitando a localização de pontos de coleta adequados.

Além disso, o Leafy busca disponibilizar informações que auxiliem na identificação dos materiais e na compreensão das formas adequadas de descarte, contribuindo para a educação ambiental. O sistema disponibiliza conteúdos sobre reciclagem, como tipos de reciclagem, cores da coleta seletiva, dicas sustentáveis e curiosidades ambientais. Também possui recursos de acompanhamento do progresso do usuário, permitindo visualizar conquistas e tempo de uso da aplicação.

Dessa forma, o sistema integra consulta de informações, identificação de resíduos, geolocalização e educação ambiental em uma aplicação voltada à orientação do usuário. Suas funcionalidades foram estruturadas para atender às necessidades identificadas no problema apresentado na introdução, oferecendo recursos que facilitam tanto a compreensão sobre os resíduos quanto a localização de locais apropriados para seu descarte.

<a id="escopo"></a>
## 3. Escopo e Requisitos

<a id="escopo-projeto"></a>
### 3.1 Escopo do Projeto 

<a id="historia-usuarios"></a>
### 3.2 História de Usuário

| HU Código | Descrição |
|-----------|-----------|
| HU-01 | Como usuário, quero escanear um produto para identificar seu tipo de material e saber se ele é reciclável. |
| HU-02 | Como usuário, quero consultar informações sobre um produto para entender suas características. |
| HU-03 | Como usuário, quero localizar pontos de coleta próximos para descartar corretamente. |
| HU-04 | Como usuário, quero acessar informações sobre reciclagem para aprender boas práticas. |
| HU-05 | Como usuário, quero acompanhar meu progresso no aplicativo para visualizar minhas conquistas e tempo de uso do aplicativo. |

<a id="requisitos-funcionais"></a>
### 3.3 Requisitos Funcionais
| RF Código | Descrição | Prioridade | HU |
|-----------|-----------|------------|----|
| RF-01 | O sistema deve permitir o escaneamento de produtos e fornecer informações sobre seu tipo, reciclabilidade e instruções de descarte. | Obrigatório | HU-01 |
| RF-02 | O sistema deve consultar o banco de dados para retornar informações sobre o produto pesquisado pelo usuário. | Obrigatório | HU-02 |
| RF-03 | O sistema deve localizar e informar ao usuário sobre pontos de coleta mais próximos. | Obrigatório | HU-03 |
| RF-04 | O sistema deve deixar disponível informações sobre a reciclagem a fim de educar o usuário. | Obrigatório | HU-04 |
| RF-05 | O sistema deve exibir o progresso do usuário, incluindo tempo de uso e conquistas obtidas. | Desejável | HU-05 |

<a id="requisitos-nao-funcionais"></a>
### 3.4 Requisitos Não Funcionais

<a id ="modelo-dados"></a>
## 4. Modelo de Dados

<a id="der"></a>
### 4.1 Diagrama Relacional - DER 
<img width="869" height="509" alt="Captura de tela 2026-08-18 110358" src="https://github.com/user-attachments/assets/92d69a16-4fb6-4c2e-9bce-0367f83060c1" />

<a id="script-banco"></a>
### 4.2 Repositório do Script de Banco de Dados

<a id="arquitetura"></a>
## 5. Arquitetura do Sistema

<a id="padrao-arquitetural"></a>
### 5.1 Descrição do Padrão Arquitetural

<a id="wireframes"></a>
### 5.2 Wireframes
<img width="848" height="802" alt="Captura de tela 2026-08-18 110608" src="https://github.com/user-attachments/assets/748b01c0-6eee-4924-bd63-55fb2746906f" />

<a id="codigo-fonte"></a>
### 5.3 Repositório do Código Fonte

<a id="deploy"></a>
### 5.4 Publicação da Aplicação (Deploy)

<a id="teste-unitario"></a>
### 5.5 Teste Unitário

<a id="casos-de-testes"></a>
#### 5.5.1 Casos de Testes

**CT-FUNC-04 - Testes Funcionais e de Regressão**
| Campo | Descrição |
|---|---|
| **Título de Teste** | **Pesquisa Parcial de Produto** - Verificar se a busca encontra produtos utilizando apenas parte do nome. |
| **Módulo** | Consulta de Resíduos |
| **Pré-Condições** | Banco contendo o produto desejado (exemplo usando “Garrafa PET”). |
| **Dados de Entrada** | **Barra de Pesquisa:** Garra |
| **Passos de Execução** | **1.** Acessar a tela de busca de produtos.<br>**2.** Digitar um nome parcial (Garra).<br>**3.** Realizar a pesquisa. |
| **Resultado Esperado** | O sistema deve retornar o resultado “Garrafa PET” na lista de resultados sem apresentar erros. |


**CT-SEG-03 - Testes de Segurança**
| Campo | Descrição |
|---|---|
| **Título de Teste** | **Manipulação de Requisições da API** - Verificar se o servidor valida corretamente os dados recebidos pela API, impedindo que informações inválidas ou manipuladas sejam armazenadas no banco de dados. |
| **Módulo** | API de Cadastro de Produtos |
| **Pré-Condições** | Usuário autenticado, banco de dados, API de cadastro e tela de cadastro de produtos em funcionamento. |
| **Dados de Entrada** | **Nome do produto:** Pilha AA<br>**Tipo de resíduo:** Alienígena |
| **Passos de Execução** | **1.** Acessar a tela “Cadastrar Produto”.<br>**2.** Informar um nome de produto válido e um tipo de resíduo que não existe entre as opções permitidas.<br>**3.** Enviar a requisição ao servidor.<br>**4.** Tentar concluir o cadastro. |
| **Resultado Esperado** | O servidor deve validar os dados recebidos, a API deve rejeitar a requisição e o produto não deve ser cadastrado no banco de dados. |


**CT-SEG-04 - Testes de Segurança**
| Campo | Descrição |
|---|---|
| **Título de Teste** | **Manipulação da Localização** - Verificar se o aplicativo protege as funcionalidades que dependem da localização do usuário quando a permissão é negada. |
| **Módulo** | Mapa de Pontos de Coleta |
| **Pré-Condições** | Permissão de localização negada nas configurações do dispositivo. |
| **Dados de Entrada** | **Permissão de localização:** Negada<br>**GPS:** Desativado |
| **Passos de Execução** | **1.** Acessar a tela “Pontos”.<br>**2.** Tentar localizar os pontos de coleta próximos. |
| **Resultado Esperado** | O aplicativo deve informar que a localização é necessária para localizar os pontos de coleta, solicitando novamente a permissão de acesso à localização ou orientando o usuário a habilitá-la manualmente nas configurações, sem apresentar falhas. |


**CT-PERF-01 - Testes de Desempenho, Carga e Estresse**
| Campo | Descrição |
|---|---|
| **Título de Teste** | **Tempo de Resposta Padrão** - Medir o tempo de carregamento da tela inicial. |
| **Módulo** | Home |
| **Pré-Condições** | Aplicativo funcionando corretamente, dispositivo conectado à internet, servidor disponível e banco de dados operacional. |
| **Dados de Entrada** | **Carga:** 1 usuário simultâneo<br>**Conexão:** Internet ativa |
| **Passos de Execução** | **1.** Abrir o aplicativo.<br>**2.** Aguardar o carregamento completo da tela inicial.<br>**3.** Medir o tempo necessário para que a tela seja exibida. |
| **Resultado Esperado** | A tela inicial do aplicativo deve ser carregada em até 3 segundos, sem travamentos ou falhas. |


**CT-PERF-02 - Testes de Desempenho, Carga e Estresse**
| Campo | Descrição |
|---|---|
| **Título de Teste** | **Teste de Carga** - Avaliar o comportamento do mapa com múltiplos usuários acessando simultaneamente os pontos de coleta. |
| **Módulo** | Mapa de Pontos de Coleta |
| **Pré-Condições** | Aplicativo funcionando corretamente, servidor e banco de dados operacionais, mapa de pontos de coleta carregado e pontos de coleta já cadastrados no banco de dados. |
| **Dados de Entrada** | **Carga:** 50 usuários simultâneos acessando o mapa. |
| **Passos de Execução** | **1.** Simular 50 usuários acessando simultaneamente o mapa de pontos de coleta.<br>**2.** Monitorar o tempo de resposta e o comportamento da aplicação durante o teste. |
| **Resultado Esperado** | O mapa deve permanecer disponível, exibindo corretamente os pontos de coleta e mantendo um tempo de resposta aceitável, sem falhas ou travamentos. |


**CT-PERF-03 - Testes de Desempenho, Carga e Estresse**
| Campo | Descrição |
|---|---|
| **Título de Teste** | **Tempo de Estresse** - Identificar o comportamento do aplicativo quando submetido a uma carga muito superior à prevista. |
| **Módulo** | Mapa de Pontos de Coleta |
| **Pré-Condições** | Servidor disponível e mapa funcionando corretamente. |
| **Dados de Entrada** | **Carga:** Aumento progressivo até o limite do sistema (100, 200, 300… usuários). |
| **Passos de Execução** | **1.** Simular um aumento contínuo de acessos ao mapa.<br>**2.** Continuar aumentando a carga até que o sistema apresente falhas, travamentos ou deixe de responder.<br>**3.** Monitorar o comportamento da memória, CPU e tempo de resposta do sistema. |
| **Resultado Esperado** | O sistema pode apresentar lentidão ou indisponibilidade sob carga extrema, porém não deve perder dados, encerrar inesperadamente ou apresentar comportamento inconsistente. |


**CT-PERF-04 - Testes de Desempenho, Carga e Estresse **
| Campo | Descrição |
|---|---|
| **Título de Teste** | **Recuperação após Pico de Estresse** - Verificar a recuperação do desempenho normal após várias requisições. |
| **Módulo** | Servidor/Backend |
| **Pré-Condições** | Sistema funcionando, servidor e banco de dados disponíveis, aplicação conectada ao backend e o teste de estresse (**CT-PERF-03**) executado anteriormente, gerando uma alta demanda de requisições. |
| **Dados de Entrada** | **Carga:** A mesma aplicada no **CT-PERF-03**. |
| **Passos de Execução** | **1.** Submeter o sistema a uma carga elevada de requisições.<br>**2.** Encerrar o pico de carga.<br>**3.** Monitorar o tempo de resposta do sistema após a redução da carga.<br>**4.** Verificar se as funcionalidades voltam ao comportamento esperado. |
| **Resultado Esperado** | Após o término do pico de carga, o sistema deve recuperar gradualmente seu desempenho normal, mantendo as funcionalidades disponíveis e sem perda de dados. |

**CT-PERF-05 - Testes de Desempenho, Carga e Estresse**
| Campo | Descrição |
|---|---|
| **Título de Teste** | **Navegação Concorrente** - Verificar se operações pesadas afetam funcionalidades simples. |
| **Módulo** | Home |
| **Pré-Condições** | Aplicativo funcionando corretamente, servidor e banco de dados operacionais, funcionalidades de mapa e consulta de produtos disponíveis para uso. |
| **Dados de Entrada** | **Carga:** Usuários e operações simultâneas, como múltiplos acessos à tela de pontos de coleta e consultas de informações sobre descarte e produtos. |
| **Passos de Execução** | **1.** Iniciar a simulação de dezenas de usuários utilizando funcionalidades de alto consumo.<br>**2.** Durante a execução da carga, acessar outras telas e testar as funções do aplicativo.<br>**3.** Observar o tempo de resposta das demais telas após o início da simulação. |
| **Resultado Esperado** | As telas e suas funções devem carregar normalmente e ser concluídas sem falhas, mantendo o aplicativo responsivo. O aplicativo não deve apresentar travamentos ou degradação perceptível de desempenho. |

<a id="resultados"></a>
## 6. Resultados

<a id="conclusao"></a>
## 7. Conclusão

<a id="referencias"></a>
## Referências

**O que é IDE (ambiente de desenvolvimento integrado)**. Disponível em: <https://www.redhat.com/pt-br/topics/platform-engineering/what-is-ide>.

**JETBRAINS. Kotlin Programming Language**. Disponível em: <https://kotlinlang.org/>.

**Metodologia Ágil - O que é?** Disponível em: <https://www.alura.com.br/artigos/o-que-e-metodologia-agil>.

**Metodologia ágil: o que é e como implementar**. Disponível em: <https://www.totvs.com/blog/negocios/metodologia-agil/>.
