# Mimic Game

Mimic Game é uma aplicação web interativa que permite aos jogadores participarem de um jogo de mímica. Os jogadores são organizados em rodadas e recebem sugestões aleatórias de mímicas para realizar. Após cada rodada, os jogadores podem pontuar e, ao final, um ranking é gerado, destacando o primeiro colocado ou jogadores empatados.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.0**
  - Spring Web
  - Spring Data JPA
  - Thymeleaf
  - H2 Database
- **Maven**
- **Thymeleaf** para templates HTML
- **Bootstrap** para estilização

## Funcionalidades

1. **Adicionar Jogadores**: Adicione jogadores à partida e inicie o jogo.
2. **Rodadas de Mímica**: Cada jogador recebe uma sugestão de mímica para realizar em cada rodada.
3. **Pontuação**: Pontuações são atribuídas aos jogadores vencedores de cada rodada.
4. **Ranking Final**: Ao final do jogo, o ranking dos jogadores é exibido, incluindo a detecção de empates.
5. **Banco de Dados em Memória (H2)**: Utilizado para armazenar temporariamente os dados dos jogadores e suas pontuações.

## Como Executar o Projeto

### Pré-requisitos

- **Java 17** ou superior instalado
- **Maven** instalado para gerenciar dependências e build do projeto

### Executando o Projeto

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/seu-usuario/mimic-game.git
