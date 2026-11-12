# Leafy - Documentação🍃
<p align="center">
  <img src="https://github.com/user-attachments/assets/d407086c-cad2-4eef-a695-a985f2955cc2" width="130" alt="Logo Leafy">
</p>

<h3 align="center">Faça crescer um futuro melhor!</h3>

## Sumário
- [Resumo](#resumo)
- [1. Introdução](#1-introdução)
- [2. Visão Geral do Sistema](#2-visão-geral-do-sistema)
- [3. Metodologia](#3-metodologia)
  - [3.1 Materiais](#31-materiais)
  - [3.2 Métodos](#32-métodos)
- [4. Escopo e Requisitos - Desenvolvimento](#4-escopo-e-requisitos---desenvolvimento)
  - [4.1 Escopo do Projeto](#41-escopo-do-projeto)
  - [4.2 História de Usuários](#42-história-de-usuários)
  - [4.3 Requisitos Funcionais](#43-requisitos-funcionais)
  - [4.4 Requisitos Não Funcionais](#44-requisitos-não-funcionais)
- [5. Modelo de Dados](#5-modelo-de-dados)
  - [5.1 Diagrama Relacional](#51-diagrama-relacional)
  - [5.2 Repositório do Script de Banco de Dados](#52-repositório-do-script-de-banco-de-dados)
- [6. Arquitetura do Sistema](#6-arquitetura-do-sistema)
  - [6.1 Descrição do Padrão Arquitetural](#61-descrição-do-padrão-arquitetural)
  - [6.2 Wireframe](#62-wireframe)
  - [6.3 Repositório do Código-Fonte](#63-repositório-do-código-fonte)
  - [6.4 Publicação da Aplicação](#64-publicação-da-aplicação)
  - [6.5 Teste Unitário](#65-teste-unitário)
- [7. Resultados](#7-resultados)
- [8. Conclusão](#8-conclusão)
- [Referências](#referências)

<a id="introducao"></a>
## 1. Introdução
O descarte incorreto de resíduos é um problema que pode gerar impactos ambientais e sociais, principalmente quando as
pessoas não possuem informações suficientes sobre a forma adequada de destinar diferentes tipos de materiais.
Nesse contexto, a tecnologia pode ser utilizada como uma ferramenta para facilitar o acesso à informação e auxiliar na
adoção de práticas mais sustentáveis.

Diante desse cenário, foi desenvolvido o aplicativo Leafy, com o objetivo de auxiliar os usuários no descarte correto de 
resíduos e facilitar o acesso a informações relacionadas à destinação adequada dos materiais. O projeto utiliza recursos 
da área de desenvolvimento de sistemas para aproximar a tecnologia de uma necessidade ambiental presente no cotidiano,
contribuindo para a conscientização sobre reciclagem e descarte responsável.

O objetivo geral do trabalho é desenvolver um aplicativo Android que auxilia os usuários a identificar resíduos,
consultar informações sobre suas características e instruções de descarte e localizar pontos de coleta. Para alcançar esse
objetivo, o sistema reúne funcionalidades de pesquisa manual, leitura de códigos de barras, consulta a uma base de dados, 
visualização de pontos de coleta e acesso a conteúdos educativos.

A escolha do tema justifica-se pela necessidade de facilitar o acesso a informações sobre o descarte adequado e incentivar 
práticas sustentáveis. Dessa forma, o projeto também se relaciona ao Objetivo de Desenvolvimento Sustentável 12 (ODS 12),
da Organização das Nações Unidas, que trata do consumo e da produção responsáveis.

Dessa forma, o Leafy propõe uma solução tecnológica de caráter informativo e educativo, voltada a apoiar as decisões dos 
usuários sobre a destinação dos resíduos. O aplicativo não realiza a coleta física dos materiais, mas busca facilitar o 
acesso às informações necessárias para que o descarte seja realizado de maneira mais consciente.

<a id="visao-geral"></a>
## 2. Visão Geral do Sistema
O Leafy é um aplicativo Android desenvolvido para auxiliar os usuários no descarte adequado de resíduos, reunindo recursos 
de consulta, identificação de materiais, localização de pontos de coleta e educação ambiental em uma única plataforma.

Entre suas principais funcionalidades, o Leafy permite a consulta de resíduos, que permite pesquisar informações sobre os
materiais cadastrados no sistema. Entre os dados disponibilizados estão o nome do resíduo, o tipo de material, a indicação
de reciclabilidade, as instruções de descarte, o tempo de decomposição e os impactos ambientais. Conforme os dados 
disponíveis para cada registro, também podem ser apresentadas explicações sobre reciclagem, dicas de descarte e curiosidades.

O aplicativo também conta com um recurso de escaneamento de códigos de barras por meio da câmera do dispositivo
. Quando o código identificado corresponde a um registro existente, o sistema pode direcionar o usuário às informações do 
resíduo correspondente. Quando não há um registro correspondente, o aplicativo contempla o encaminhamento para a sugestão de
cadastro de um novo resíduo, conforme o fluxo implementado.

Outra funcionalidade é o mapa interativo de pontos de coleta, que apresenta locais cadastrados com informações como nome,
endereço, categoria, descrição, coordenadas geográficas e materiais aceitos. O mapa utiliza recursos de visualização
geográfica e permite aplicar filtros para facilitar a consulta dos locais. O aplicativo tem como finalidade apresentar os 
pontos disponíveis, não calcular distâncias nem traçar rotas até eles.

O Leafy disponibiliza ainda conteúdos educativos relacionados à reciclagem, à coleta seletiva e a práticas sustentáveis.
O sistema também possui uma área de acompanhamento do usuário, destinada à apresentação de informações de progresso e 
conquistas previstas na aplicação.

Para utilizar as funcionalidades que exigem identificação, o usuário pode realizar cadastro e login.
O sistema também contempla um perfil administrativo, destinado ao gerenciamento de recursos específicos, incluindo a 
análise de sugestões de resíduos quando esse fluxo está habilitado.

Dessa forma, o Leafy integra funcionalidades de consulta, escaneamento, visualização de pontos de coleta e educação 
ambiental, com o objetivo de facilitar o acesso à informação e apoiar a adoção de práticas de descarte mais conscientes.

<a id="Metodologia"></a>
## 3. Metadologia

<a id="materiais"></a>
### 3.1 Materiais
Para o aplicativo Leafy, vários materiais serão utilizados em seu processo de composição. Como material base de uso, 
será usado o Android Studio, um espaço para a criação dos códigos que estruturarão o aplicativo e darão forma a ele, 
com grande parte do tempo destinado ao desenvolvimento do projeto sendo passado no Android Studio. Para a criação das
partes visuais do aplicativo, o site Figma foi de extrema importância para o desenvolvimento dos Wireframes, as
representações da aplicação, criando telas para smartphones com grande qualidade e beleza, expressando exatamente a ideia
de reciclagem e sustentabilidade buscada pelo projeto. 

O banco de dados é uma parte muito relevante para o funcionamento do aplicativo, e para sua produção será usado o VS Code e
o MySQL Workbench. O MySQL Workbench é responsável pela arquitetura do banco, sendo o local onde será criado todo o banco de
dados do aplicativo e seus requeridos recursos, como Trigger, Procedure e Function, partes essenciais do banco, com a
utilização conjunta do Xampp, que aciona o funcionamento do MySQL, para que o banco de dados possa estar rodando 
corretamente
. Para a conexão do banco de dados com o Leafy, são necessários códigos no VS Code que farão essa integração entre os dois 
códigos e que levará a habilidade do aplicativo de guardar dados e informações que adicionam recursos essenciais do 
aplicativo, como a informação de locais onde possuem lixeiras, por exemplo.

Todas as ferramentas citadas, compõem um grande sistema onde elas são fundamentais para o funcionamento e formação do 
aplicativo Leafy. A linguagem de programação que será usada como base do projeto é a linguagem Kotlin, que permite seu
uso para o desenvolvimento de aplicativos móveis. A linguagem também permite o seu uso para desenvolvimento web e back-end,
com foco em produtividade e segurança. Para a construção da conexão entre o Leafy e o banco de dados, será utilizado o 
JavaScript que, como dito anteriormente, estará sendo manuseado no VS Code, para que assim, o aplicativo possa rodar e 
funcionar devidamente. Bibliotecas de frameworks usadas durante a produção do programa são próprias do Android, extraídas
durante a codificação no Android Studio, como emojis ou personagens usados como representações visuais na plataforma.

O banco de dados é, como todas as outras, uma peça crucial do funcionamento da programação, sendo extremamente útil para o
armazenamento de informações que cooperam para o uso e desempenho da aplicação. O banco de dados, operado e produzido 
através do MySQL Workbench, conterá elementos importantes, como dados e informações de usuários, dados sobre nível dos 
usuários, lixeiras próximas de locais, tipos de metais e seus devidos locais de reciclagem e os tipos de reciclados e
lixos. Todos esses registros são primordiais para a execução correta da aplicação, para que, como planejado por todo o grupo,
tudo ocorra da forma visualizada.

<a id="metodos"></a>
### 3.2 Métados
Durante o decorrer de criação do projeto, vários métodos foram e serão utilizados, a fim de que o tempo seja usado de 
maneira inteligente e que possa ser aproveitado o máximo possível para o desenvolvimento e aprimoramento do aplicativo Leafy.
O processo de prototipagem do aplicativo foi feito logo no começo, com o intuito de obter uma visualização clara do programa
logo no início e não ter nenhum atraso ao longo da programação do aplicativo. Essa prototipagem foi feita, como dito, no
website Figma, como uma interface bem-acabada que transmite a mensagem procurada pelo grupo.

Durante o transcorrer de criação do Leafy, o grupo se vê necessidade de usar uma metodologia na qual possa dividir tarefas
de forma eficiente entre os integrantes do grupo e estipular prazos de entrega para essas tarefas, para que o processo de 
criação não se atrase ou encontre o mínimo de empecilhos possível durante o decorrer das aulas. A metodologia utilizada é a
metodologia ágil, na qual busca a gestão de projetos baseado em ciclos rápidos, flexibilidade e colaboração, focada na 
entrega contínua de valor e melhoria contínua. Esse tipo de metodologia proporcionou o uso de pequenos ciclos na qual seja
possível fazer o máximo de tarefas possíveis, eles são chamados de sprints e auxiliam o grupo a seguir um tempo estipulado
para completar tal tarefa. A partir da metodologia, também se torna um objetivo a entrega contínua de partes funcionais,
focando em valor e funcionalidade do aplicativo, buscando sempre sua melhoria. Como parte de sua metodologia que também foi
adotada pela equipe, é a flexibilidade, buscando sempre estar preparado para qualquer mudança no escopo que seja necessária,
com o fito de evitar problemas futuros que possam comprometer o andamento do projeto.

UML também é utilizado, é um padrão visual utilizado na engenharia de software para especificar, documentar, visualizar e
construir sistemas complexos, sendo usado durante o desenvolvimento para uma melhor comunicação entre os membros da equipe,
para que problemas de comunicação e entendimento em relação ao programa sejam minimizados a zero. Todos esses métodos são 
usados para o avanço do decorrer da programação, pensando em melhoria na qualidade de todos e o aproveitamento de tempo ao
máximo.

<a id="escopo-e-requisitos"></a>
## 4. Escopo e Requisitos - Desenvolvimento

<a id = "escopo-do-projeto"></a>
### 4.1 Escopo do Projeto
O escopo do projeto compreende o desenvolvimento de um aplicativo Android voltado ao auxílio no descarte adequado de 
resíduos. O sistema terá como principais funcionalidades a consulta de informações sobre produtos e resíduos, a 
identificação de materiais por meio do escaneamento de produtos, a apresentação de instruções sobre o descarte adequado e
a localização de pontos de coleta próximos ao usuário por meio de recursos de geolocalização.

Também fará parte do sistema um mapa interativo com pontos de coleta organizados de acordo com o tipo de resíduo,
permitindo ao usuário visualizar e identificar locais próximos. O aplicativo contará ainda com conteúdos educativos 
relacionados à reciclagem, incluindo informações sobre tipos de reciclagem, coleta seletiva, dicas sustentáveis e 
curiosidades ambientais. Além disso, será disponibilizado um sistema de acompanhamento do progresso do usuário, com 
recursos relacionados a conquistas e tempo de uso da aplicação. Ficam fora do escopo do projeto a realização física 
da coleta ou do transporte dos resíduos, a prestação de serviços de reciclagem, o gerenciamento dos estabelecimentos
responsáveis pelos pontos de coleta e a realização de denúncias de descarte irregular. O aplicativo também não  ira 
criar e mostrar rotas para o local de coleta de lixo, garantir o funcionamento dos pontos de coleta cadastrados ou 
disponibilizar serviços de transporte até esses locais.

Dessa forma, o Leafy terá como foco principal fornecer informações sobre os resíduos e facilitar a localização de pontos 
de coleta adequados, utilizando recursos de desenvolvimento de software e geolocalização para oferecer uma solução prática
de orientação ao usuário.

<a id = "historia-de-usuarios"></a>
### 4.2 História de Usuários

| HU Código | Descrição |
|:---:|---|
| HU-01 | Como usuário, quero escanear um produto para identificar seu tipo de material e saber se ele é reciclável. |
| HU-02 | Como usuário, quero consultar informações sobre um produto para entender suas características. |
| HU-03 | Como usuário, quero localizar pontos de coleta próximos para descartar corretamente. |
| HU-04 | Como usuário, quero acessar informações sobre reciclagem para aprender boas práticas. |
| HU-05 | Como usuário, quero acompanhar meu progresso no aplicativo para visualizar minhas conquistas e tempo de uso do aplicativo. |
| HU-06 | Como usuário, quero cadastrar uma conta e realizar login para acessar as funcionalidades que exigem autenticação. |
| HU-07 | Como administrador, quero analisar sugestões de novos resíduos para decidir quais informações poderão ser incorporadas à base de dados. |

### 4.3 Requisitos Funcionais

| RF Código | Descrição | Prioridade | HU |
|:---:|---|:---:|:---:|
| RF-01 | O sistema deve permitir a leitura de códigos de barras e consultar as informações do resíduo identificado. | Obrigatório | HU-01 |
| RF-02 | O sistema deve permitir a pesquisa manual de resíduos e apresentar os dados disponíveis para o material consultado. | Obrigatório | HU-02 |
| RF-03 | O sistema deve apresentar pontos de coleta cadastrados em um mapa, incluindo as informações disponíveis sobre os locais. | Obrigatório | HU-03 |
| RF-04 | O sistema deve disponibilizar conteúdos educativos relacionados à reciclagem e à sustentabilidade. | Obrigatório | HU-04 |
| RF-05 | O sistema deve disponibilizar a área de progresso e as informações de conquistas do usuário. | Desejável | HU-05 |
| RF-06 | O sistema deve permitir o cadastro e a autenticação de usuários por e-mail e senha. | Obrigatório | HU-06 |
| RF-07 | O sistema deve permitir que o usuário envie sugestões de novos resíduos e que o administrador analise essas sugestões. | Desejável | HU-07 |

### 4.4 Requisitos Não Funcionais

| RNF Código | Descrição | Prioridade |
|:---:|---|:---:|
| RNF-01 | O aplicativo deve apresentar uma interface simples, intuitiva e organizada. | Obrigatório |
| RNF-02 | O aplicativo deve apresentar tempo de resposta adequado durante a utilização de suas principais funcionalidades. | Obrigatório |
| RNF-03 | O aplicativo deve funcionar de forma estável, evitando encerramentos inesperados e falhas. | Obrigatório |
| RNF-04 | O aplicativo deve restringir o acesso às funcionalidades administrativas e às informações protegidas, de acordo com as permissões definidas. | Obrigatório |
| RNF-05 | O sistema deve validar os dados informados antes de registrar novas informações. | Obrigatório |
| RNF-06 | O aplicativo deve solicitar e utilizar a permissão de localização do dispositivo de acordo com as configurações do usuário. | Obrigatório |
| RNF-07 | O sistema deve manter a consistência dos registros armazenados no Cloud Firestore. | Obrigatório |
| RNF-08 | O código-fonte deve ser organizado de forma a facilitar a manutenção e as futuras atualizações. | Desejável |
| RNF-09 | O aplicativo deve apresentar mensagens apropriadas quando ocorrerem falhas de conexão ou indisponibilidade dos dados. | Desejável |

<a id="modelos-de-dados"></a>
## 5.Modelos de Dados

<a id="diagrama-relacional"></a>
### 5.1 Diagrama Relacional
<img width="500" height="500" alt="Captura de tela 2026-11-03 101424" src="https://github.com/user-attachments/assets/ef8bdf53-3782-41cb-b6e3-53033450769c" />

<a id="repositorio-script"></a>
### 5.2 Repositório do Script de Banco de Dados
O aplicativo Leafy utiliza o Firebase Cloud Firestore como sistema de gerenciamento de banco de dados. Por ser um banco de dados NoSQL, sua estrutura é organizada em coleções
e documentos, não sendo necessária a utilização de um script SQL para a criação das tabelas.
As principais coleções utilizadas pelo sistema armazenam informações sobre resíduos, usuários e pontos de coleta, além de 
dados relacionados às funcionalidades complementares do aplicativo.

#### Link do Repositório/Arquivo: https://github.com/MariaFernanda-08/Leafy

<a id="arquitetura-do-sistema"></a>
## 6. Arquitetura do Sistema

<a id ="descricao-do-padrao"></a>
### 6.1 Descrição do Padrão Arquitetural
O Leafy utiliza o padrão arquitetural Model-View-ViewModel (MVVM) para organizar o código do aplicativo Android.
Esse padrão separa as responsabilidades da interface, do gerenciamento dos estados da aplicação e da representação
e manipulação dos dados, contribuindo para a organização e a manutenção do sistema.

A camada View é composta pelas telas desenvolvidas com Jetpack Compose, que são responsáveis por apresentar as informações
e receber as interações do usuário. A camada ViewModel gerencia os estados utilizados pelas telas, executa as ações 
relacionadas à lógica de apresentação e solicita operações de dados. A camada Model reúne as estruturas de dados e os 
componentes responsáveis pelas operações relacionadas ao domínio da aplicação.

Os repositórios atuam como intermediários entre os ViewModels e os serviços de armazenamento, concentrando operações como
consulta, cadastro e atualização dos dados. No projeto, os repositórios integram o aplicativo aos serviços do Firebase, 
principalmente ao Cloud Firestore e ao Firebase Authentication.
O processo de autenticação é realizado pelo Firebase Authentication, enquanto os dados dos resíduos, dos usuários, dos 
pontos de coleta e das sugestões são armazenados no Cloud Firestore. O acesso a esses serviços ocorre por meio dos SDKs 
do Firebase para Android, sem a necessidade de uma API REST própria para as operações implementadas dessa forma.

A leitura de códigos de barras utiliza a câmera do dispositivo do usuário, integrada por meio do CameraX, e o processamento
do código é realizado pelo Google ML Kit. O mapa utiliza a biblioteca Leaflet para exibir os pontos de coleta cadastrados,
com informações geográficas obtidas do banco de dados.

Essa organização permite dividir o sistema em componentes com responsabilidades específicas, facilitando a identificação de
erros, a manutenção do código e a implementação de melhorias futuras.


| Perfil | Descrição | Permissões básicas |
|:---:|---|:---:|
| Usuário | Usuário final que utiliza o aplicativo para obter informações sobre resíduos e localizar pontos de coletas. | Consultar resíduos, escanear produtos, visualizar pontos de coleta, acessar conteúdos educativos, acompanhar seu progresso e enviar sugestões de novos resíduos. |
| Administrador | Responsável pelo gerenciamento das informações e pela análise das sugestões enviadas pelos usuários. | Analisar, aprovar ou rejeitar sugestões de resíduos e gerenciar as informações conforme as permissões definidas no sistema.  |

<a id = "wireframe"></a>
### 6.2 Wireframe/Protótipo
<img width="1920" height="1080" alt="Wireframe" src="https://github.com/user-attachments/assets/52a699b0-a84a-4605-9fe3-b89178d24d21" />

<a id = "repositorio-do-codigo-fonte"></a>
### 6.3 Repositório do Código Fonte
O código-fonte do Leafy está hospedado no GitHub, que foi utilizado para versionamento e acompanhamento das alterações das alterações realizadas durante o desenvolvimento.

O repositório contém os arquivos do projeto Android e os componentes de código associados á implementação das funcionalidades.

https://github.com/MariaFernanda-08/Leafy

<a id = "Publicao-da-aplicacao"></a>
### 6.4 Publicação da Aplicação(Deploy)
[Descreva onde e como a aplicação foi publicada. Ex: AWS, Firebase, Heroku, infraestrutura, local, etc. Inclua o link de acesso, se houver.]

<a id="teste-unitario"></a>
### 6.5 Teste Unitário
Os testes do Leafy têm como finalidade verificar o funcionamento das funcionalidades implementadas, a consistência dos dados 
apresentados e o comportamento do aplicativo em diferentes condições de utilização.

Os testes devem ser organizados de acordo com os módulos do sistema, incluindo autenticação, consulta de resíduos, 
leitura de códigos de barras, visualização dos pontos de coleta e gerenciamento das sugestões. Também podem ser avaliados 
aspectos como tratamento de erros, permissões do dispositivo e estabilidade da navegação.

É importante distinguir os testes unitários, que verificam componentes ou funções de forma isolada, dos testes de 
integração, que avaliam a interação entre componentes, e dos testes funcionais, que verificam o comportamento do
aplicativo a partir das ações do usuário.

Os casos de teste apresentados a seguir descrevem os procedimentos e os resultados esperados. O status de execução e o 
resultado obtido em cada caso devem ser registrados após a realização dos testes no ambiente disponível.

<a id="casos-de-testes"></a>
### 6.5.1 Casos de Testes

#### Caso de Teste: CT-FUNC-01

| Campo | Descrição |
|---|---|
| **Título de Teste** | Pesquisa Parcial de Produto |
| **Módulo** | Consulta de Resíduos |
| **Pré-Condições** | Aplicativo em execução e resíduo cadastrado no Firestore. |
| **Dados de Entrada** | Barra de Pesquisa: Garra |
| **Passos de Execução** | 1. Acessar a tela de busca de produtos.<br>2. Digitar um nome parcial (Garra).<br>3. Realizar a pesquisa. |
| **Resultado Esperado** | O sistema deve retornar o resultado "Garrafa de Vidro" na lista de resultados sem apresentar erros. |
| **Evidência** |<img width="280" height="auto" alt="Screenshot_20261029_084218_Leafy" src="https://github.com/user-attachments/assets/1af10b24-8c22-48f3-8001-74987f0a91de" /> |

#### Caso de Teste: CT-FUNC-02

| Campo | Descrição |
|---|---|
| **Título de Teste** | Leitura de código de barras |
| **Módulo** | Scanner |
| **Pré-Condições** | Câmera disponível, permissão concedida e código de barras legível. |
| **Dados de Entrada** | Código de barras de um produto cadastrado. |
| **Passos de Execução** | 1. Abrir o scanner.<br>2. Posicionar o código diante da câmera. |
| **Resultado Esperado** | O aplicativo identifica o código e direciona o usuário às informações correspondentes. |
| **Evidência** | <img width="280" height="auto" alt="Screenshot_20261029_084327_Leafy" src="https://github.com/user-attachments/assets/149737f2-4b9e-4efd-afd4-ecb4eb3d61bb" /> |

#### Caso de Teste: CT-FUNC-03

| Campo | Descrição |
|---|---|
| **Título de Teste** | Código de barras não cadastrado |
| **Módulo** | Scanner e sugestões |
| **Pré-Condições** | Aplicativo em execução e código não cadastrado na coleção de resíduos. |
| **Dados de Entrada** | Código de barras de um produto sem registro correspondente. |
| **Passos de Execução** | 1. Escanear o código.<br>2. Verificar a navegação apresentada. |
| **Resultado Esperado** | O aplicativo encaminha o usuário ao fluxo de sugestão ou cadastro previsto para resíduos desconhecidos, sem apresentar informações de outro produto. |
| **Evidência** |<img width="280" height="auto" alt="Screenshot_20261029_084431_Leafy" src="https://github.com/user-attachments/assets/0abc9563-051e-44f8-b880-886f1643af36" /> |

#### Caso de Teste: CT-FUNC-04

| Campo | Descrição |
|---|---|
| **Título de Teste** | Visualização de pontos de coleta |
| **Módulo** | Mapa |
| **Pré-Condições** | Pontos de coleta cadastrados no Firestore e conexão disponível. |
| **Dados de Entrada** | Registros de pontos com coordenadas geográficas válidas. |
| **Passos de Execução** | 1. Abrir o mapa.<br>2. Verificar os marcadores.<br>3. Aplicar os filtros disponíveis. |
| **Resultado Esperado** | Os pontos cadastrados são apresentados conforme os dados e os filtros selecionados. |
| **Evidência** | <img width="280" height="auto" alt="Screenshot_20261029_084528_Leafy" src="https://github.com/user-attachments/assets/696d52ea-1e0d-435d-8a6f-71246cdc2f4b" />|

#### Caso de Teste: CT-FUNC-05

| Campo | Descrição |
|---|---|
| **Título de Teste** | Análise de sugestões de resíduos |
| **Módulo** | Administração de sugestões |
| **Pré-Condições** | Sugestão registrada no banco de dados e conta administrativa autorizada. |
| **Dados de Entrada** | Sugestão de um resíduo ainda não cadastrado. |
| **Passos de Execução** | 1. Acessar a área administrativa.<br>2. Visualizar a sugestão.<br>3. Executar a ação de aprovação ou rejeição disponibilizada. |
| **Resultado Esperado** | O sistema atualiza a situação da sugestão e, quando aprovada, incorpora os dados à coleção principal conforme as regras implementadas. |
| **Evidência** |<img width="290" height="auto" alt="Captura de tela " src="https://github.com/user-attachments/assets/461a8b3c-c532-4142-91e6-8318d84f2541" /> |

#### Caso de Teste: CT-SEG-01

| Campo | Descrição |
|---|---|
| **Título de Teste** | Acesso às funcionalidades administrativas |
| **Módulo** | Autenticação e administração |
| **Pré-Condições** | Existência de uma conta comum e de uma conta administrativa de teste. |
| **Dados de Entrada** | Contas com diferentes perfis de acesso. |
| **Passos de Execução** | 1. Autenticar com cada conta.<br>2. Tentar acessar as funcionalidades administrativas. |
| **Resultado Esperado** | Somente a conta com as permissões administrativas necessárias pode executar as operações restritas. |
| **Evidência** | Usuário Normal e Administrador <img width="500" height="auto" alt="pagina iniical" src="https://github.com/user-attachments/assets/49f7e0b6-f480-456a-9b3c-ba8fa53b9531" /> |

#### Caso de Teste: CT-SEG-02

| Campo | Descrição |
|---|---|
| **Título de Teste** | Manipulação da Localização |
| **Módulo** | Mapa de Pontos de Coleta |
| **Pré-Condições** | Permissão de localização negada nas configurações do dispositivo. |
| **Dados de Entrada** | Permissão de localização: Negada. GPS: Desativado. |
| **Passos de Execução** | 1. Acessar a tela "Pontos".<br>2. Tentar localizar os pontos de coleta próximos. |
| **Resultado Esperado** | O aplicativo deve informar que a localização é necessária para localizar os pontos de coleta, solicitando novamente a permissão de acesso à localização ou orientando o usuário a habilitá-la manualmente nas configurações, sem apresentar falhas. |
| **Evidência** | <img width="280" height="auto" alt="Screenshot_20261029_091142_Leafy" src="https://github.com/user-attachments/assets/c75f79d6-8cb8-41cb-8905-317de0fdd267" /> |

#### Caso de Teste: CT-PERF-01

| Campo | Descrição |
|---|---|
| **Título de Teste** | Tempo de Resposta Padrão |
| **Módulo** | Home |
| **Pré-Condições** | Aplicativo funcionando corretamente, dispositivo conectado à internet, servidor disponível e banco de dados operacional. |
| **Dados de Entrada** | Carga: 1 usuário simultâneo. Conexão: Internet ativa. |
| **Passos de Execução** | 1. Abrir o aplicativo.<br>2. Aguardar o carregamento completo da tela inicial.<br>3. Medir o tempo necessário para que a tela seja exibida. |
| **Resultado Esperado** | A tela inicial do aplicativo deve ser carregada em até 3 segundos, sem travamentos ou falhas. |
| **Tentativas** | 1. Tempo: 2,50s — Aprovado.<br>2. Tempo: 2,13s — Aprovado.<br>3. Tempo: 1,80s — Aprovado.<br>4. Tempo: 2,46s — Aprovado.<br>5. Tempo: 2,31s — Aprovado. |

#### Caso de Teste: CT-PERF-02

| Campo | Descrição |
|---|---|
| **Título de Teste** | Teste de Carga - Avaliar o comportamento do mapa com múltiplos usuários acessando os pontos de coleta |
| **Módulo** | Mapa de Pontos de Coleta |
| **Pré-Condições** | Aplicativo funcionando corretamente, conexão com a internet disponível, Firebase Cloud Firestore operacional e pontos de coleta cadastrados. |
| **Dados de Entrada** | Carga: 50 usuários simultâneos acessando o mapa e as informações dos pontos de coleta. |
| **Passos de Execução** | 1. Simular 50 usuários acessando simultaneamente o mapa de pontos de coleta.<br>2. Monitorar o tempo de resposta das consultas ao Firestore.<br>3. Verificar se os dados dos pontos de coleta são retornados corretamente.<br>4. Observar o comportamento do aplicativo durante a carga. |
| **Resultado Esperado** | As consultas aos pontos de coleta devem retornar os dados corretamente, sem erros inesperados. O comportamento do aplicativo e o tempo de resposta devem ser registrados durante o teste, permitindo avaliar sua estabilidade sob carga. |

#### Caso de Teste: CT-PERF-03

| Campo | Descrição |
|---|---|
| **Título de Teste** | Tempo de Estresse - Avaliar o comportamento do Leafy sob aumento progressivo de acessos. |
| **Módulo** | Mapa de Pontos de Coleta e serviços Firebase |
| **Pré-Condições** | Aplicativo funcionando corretamente, conexão com a internet disponível, Firebase Cloud Firestore operacional e pontos de coleta cadastrados. |
| **Dados de Entrada** | Carga: Aumento progressivo até o limite do sistema (10, 25, 50 e 100 usuários). |
| **Passos de Execução** | 1. Iniciar a simulação com 10 acessos simultâneos.<br>2. Aumentar progressivamente a carga para 25, 50 e 100 acessos.<br>3. Monitorar o tempo de resposta, as operações concluídas e os erros apresentados.<br>4. Observar o comportamento do aplicativo e dos serviços Firebase durante o teste.<br>5. Encerrar a simulação caso sejam identificados erros excessivos ou condições que comprometam a execução. |
| **Resultado Esperado** | O sistema pode apresentar lentidão ou indisponibilidade sob carga extrema, porém não deve perder dados, encerrar inesperadamente ou apresentar comportamento inconsistente. |

#### Caso de Teste: CT-PERF-04

| Campo | Descrição |
|---|---|
| **Título de Teste** | Recuperação após Pico de Estresse |
| **Módulo** | Mapa de Pontos de Coleta e serviços Firebase |
| **Pré-Condições** | Aplicativo funcionando corretamente, Firebase Cloud Firestore operacional e teste de estresse CT-PERF-03 executado anteriormente, com os registros de carga aplicada. |
| **Dados de Entrada** | Carga: A mesma utilizada no CT-PERF-03, seguida pelo encerramento da simulação de acessos simultâneos. |
| **Passos de Execução** | 1. Submeter os serviços utilizados pelo aplicativo à carga definida.<br>2. Encerrar a simulação de carga.<br>3. Realizar novas consultas aos pontos de coleta e informações de resíduos.<br>4. Monitorar o tempo de resposta e possíveis erros após a redução da carga.<br>5. Verificar se as funcionalidades do aplicativo continuam operando corretamente. |
| **Resultado Esperado** | Após o encerramento da carga, as operações devem continuar funcionando corretamente. Os tempos de resposta e possíveis erros devem ser registrados para avaliar o comportamento após o pico de acessos, sem presumir recuperação antes da execução do teste. |

#### Caso de Teste: CT-PERF-05

| Campo | Descrição |
|---|---|
| **Título de Teste** | Navegação Concorrente - Avaliar o comportamento do aplicativo durante operações simultâneas |
| **Módulo** | Home, Mapa de Pontos de Coleta e Consulta de Resíduos. |
| **Pré-Condições** | Aplicativo funcionando corretamente, conexão com a internet disponível, Firebase Cloud Firestore operacional e funcionalidades de mapa e consulta disponíveis. |
| **Dados de Entrada** | Carga: Operações simultâneas propostas, como acesso ao mapa, consulta de resíduos e visualização de informações de descarte. |
| **Passos de Execução** | 1. Iniciar a simulação de acessos simultâneos às funcionalidades do aplicativo.<br>2. Realizar consultas de resíduos e acessos ao mapa durante a execução da carga.<br>3. Observar o comportamento das telas e o tempo de resposta das operações.<br>4. Verificar se as informações são exibidas corretamente.<br>5. Registrar eventuais erros, lentidão ou interrupções durante a navegação. |
| **Resultado Esperado** | As funcionalidades devem apresentar comportamento consistente durante as operações simultâneas. O tempo de resposta, os erros e possíveis impactos na navegação devem ser registrados para avaliar o desempenho do aplicativo e dos serviços utilizados. |

<a id="resultados"></a>
## 7. Resultados
O desenvolvimento do Leafy resultou na construção de um aplicativo Android voltado à orientação sobre o descarte adequado 
de resíduos. Durante o projeto, foram implementadas funcionalidades e componentes destinados à consulta de materiais, à 
autenticação dos usuários, à gamificação de ações do cliente, à visualização de pontos de coleta e à disponibilização de 
informações educativas.

A implementação utilizou Kotlin, Android Studio e Jetpack Compose, com organização do código baseada no padrão MVVM 
(Model-View-View-Model). A adoção do Firebase Authentication e do Cloud Firestore permitiu integrar a autenticação e o
armazenamento dos dados ao aplicativo. Essa mudança representou uma adaptação em relação à idealização inicial do projeto,
que previa a utilização de MySQL e de uma API própria.

O aplicativo também contém a integração com recursos de câmera para a leitura de códigos de barras e a apresentação de
pontos de coleta por meio de um mapa interativo. Essas funcionalidades foram concebidas para facilitar o acesso às 
informações sobre os materiais e os locais de descarte cadastrados.

Em relação aos objetivos propostos, o projeto estabeleceu uma estrutura que reúne diferentes recursos de orientação 
ambiental em uma única aplicação. A consulta de resíduos permite acessar informações sobre os materiais, enquanto o mapa
auxilia na visualização dos pontos de coleta disponíveis no banco de dados. Os conteúdos educativos complementam essas
funcionalidades, oferecendo informações relacionadas à reciclagem e às práticas sustentáveis.

A comparação entre o planejamento inicial e a implementação mostra que o desenvolvimento exigiu adaptações tecnológicas 
e ajustes em seu escopo. A substituição do banco de dados relacional pelo Cloud Firestore modificou a organização dos dados
e a forma de comunicação entre o aplicativo e os serviços de armazenamento. A decisão de não implementar cálculo de 
distâncias ou geração de rotas foi mantida, concentrando o recurso geográfico na apresentação dos pontos cadastrados.

A avaliação final das funcionalidades deve considerar os resultados dos testes realizados, a estabilidade da aplicação e
a correspondência entre os requisitos definidos e os recursos entregues. Capturas de tela das funcionalidades implementadas,
registros dos testes e exemplos de consulta aos dados podem ser utilizados como evidências dos resultados alcançados.

Dessa maneira, o Leafy constitui uma aplicação de papel informativo e educativo, desenvolvida para facilitar o acesso a
informações sobre resíduos e apoiar a identificação de locais apropriados para o descarte. As limitações identificadas e as 
funcionalidades que não foram concluídas devem ser consideradas na interpretação dos resultados e no planejamento de
melhorias futuras.

<a id = "conclusao"></a>
## 8. Conclução

O desenvolvimento do Leafy permitiu aplicar conhecimentos da área de Desenvolvimento de Sistemas na construção de uma
aplicação Android voltada a uma necessidade ambiental. O projeto teve como objetivo facilitar o acesso a informações 
sobre resíduos, orientar os usuários quanto ao descarte adequado e apresentar pontos de coleta cadastrados.

Durante a implementação, foram utilizados Kotlin, Android Studio, Jetpack Compose e o padrão arquitetural MVVM. A integração
com o Firebase Authentication e o Cloud Firestore possibilitou estruturar os recursos de autenticação e armazenamento de dados.
Também foram incorporadas tecnologias destinadas à leitura de códigos de barras e à apresentação de informações geográficas.

O processo de desenvolvimento exigiu adaptações em relação ao planejamento inicial, especialmente na escolha das tecnologias
de armazenamento e comunicação de dados. A adoção do Firebase substituiu a abordagem inicialmente baseada em MySQL e em uma
API própria, o que demonstra a importância de avaliar as necessidades do projeto e ajustar as decisões técnicas ao longo de 
sua execução.

Como contribuição, o Leafy reúne funcionalidades de consulta, identificação de resíduos, visualização de pontos de coleta e 
educação ambiental em uma única aplicação. Entretanto, sua utilidade depende da qualidade e da atualização das informações 
cadastradas, além do funcionamento adequado das funcionalidades implementadas. O aplicativo também não realiza coleta física,
cálculo de distâncias ou geração de rotas, mantendo seu foco na orientação informativa do usuário.

Conclui-se que o projeto representa uma aplicação prática de conceitos de desenvolvimento móvel, integração com serviços em
nuvem e organização arquitetural. Como possibilidades de evolução, podem ser considerados o aprimoramento dos mecanismos de
validação dos dados, a ampliação da base de resíduos e pontos de coleta, a realização de testes adicionais e a implementação
de novas funcionalidades que contribuam para a experiência do usuário.

A avaliação definitiva do alcance dos objetivos deve considerar os testes efetivamente realizados e as funcionalidades
disponíveis na versão final entregue, permitindo identificar os resultados obtidos e as melhorias que ainda podem ser 
desenvolvidas.

<a id="referencias"></a>
## Referências
- ANDROID DEVELOPERS. Jetpack Compose. Disponível em: https://developer.android.com/develop/ui/compose. Acesso em: 11 ago. 2026.

- GOOGLE. Firebase Authentication. Disponível em: https://firebase.google.com/docs/auth. Acesso em: 8 out. 2026.

- GOOGLE. Cloud Firestore. Disponível em: https://firebase.google.com/docs/firestore. Acesso em: 8 out. 2026.

- GOOGLE. ML Kit: leitura de códigos de barras. Disponível em: https://developers.google.com/ml-kit/vision/barcode-scanning. Acesso em: 17 set. 2026.

- GOOGLE. CameraX. Disponível em: https://developer.android.com/media/camera/camerax. Acesso em: 17 set. 2026.

- JETBRAINS. Kotlin Programming Language. Disponível em: https://kotlinlang.org/. Acesso em: 11 ago. 2026.

- LEAFLET. Leaflet: an open-source JavaScript library for mobile-friendly interactive maps. Disponível em: https://leafletjs.com/. Acesso em: 3 set. 2026.

- OPENSTREETMAP. OpenStreetMap. Disponível em: https://www.openstreetmap.org/. Acesso em: 3 set. 2026.

- ORGANIZAÇÃO DAS NAÇÕES UNIDAS. Objetivo de Desenvolvimento Sustentável 12: consumo e produção responsáveis. Disponível em: https://sdgs.un.org/goals/goal12. Acesso em: 4 ago. 2026.

- BRASIL. Ministério do Meio Ambiente. O que é reciclagem. Disponível em: https://www.gov.br/mma/pt-br/assuntos/educacao-ambiental/glossario-ea/o-que-e-reciclagem. Acesso em: 6 ago. 2026.

- BRASIL. Instituto Brasileiro do Meio Ambiente e dos Recursos Naturais Renováveis. Pilhas e baterias. Disponível em: https://www.gov.br/ibama/pt-br/assuntos/emissoes-e-residuos/residuos/pilhas-e-baterias. Acesso em: 6 ago. 2026.

- MARIA FERNANDA-08. Leafy: repositório do código-fonte do aplicativo. GitHub. Disponível em: https://github.com/MariaFernanda-08/Leafy. Acesso em: 1 set. 2026.

- ANDROID DEVELOPERS. Guide to app architecture. Disponível em: Android Developers – App architecture. Acesso em: 8 ago. 2026

- ANDROID DEVELOPERS. Request location permissions. Disponível em: Android Developers – Location permissions. Acesso em: 3 set. 2026

- GOOGLE. Firebase Security Rules. Disponível em: Firebase Security Rules. Acesso em: 10 out. 2026.

- ANDROID DEVELOPERS. Build web apps in WebView. Disponível em: Android Developers – WebView. Acesso em: 8 ago. 2026

  
