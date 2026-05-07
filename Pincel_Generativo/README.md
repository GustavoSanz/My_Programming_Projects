# O Pincel Generativo 🎨

## Declaração de Intenção
Esta obra é uma experiência interativa abstrata onde o corpo do utilizador se torna a própria ferramenta de criação. Ao utilizar visão computacional, o projeto quebra a barreira física entre o artista e a tela digital, permitindo pintar através de gestos orgânicos no ar.

## Funcionalidades e Interação
O sistema utiliza reconhecimento de mãos em tempo real. A interface responde dinamicamente aos seguintes gestos:

* **☝️ 1 Dedo (Indicador no ar):** Ativa o Pincel Preto (traço contínuo).
* **✌️ 2 Dedos (Sinal de "V"):** Ativa o Pincel Mágico (cores generativas e dinâmicas).
* **🖐️ Mão Aberta:** Ativa a Borracha Gigante.
* **☝️〰️☝️ 2 Mãos a apontar:** Cria um Raio Laser Néon entre os dois indicadores.

**Controlos de Teclado:**
* `H`: Oculta ou mostra o Menu de Ajuda e a UI.
* `S`: Guarda a pintura atual como uma imagem `.png` com fundo transparente.

## Detalhes Técnicos e UI/UX
* **Prevenção de Falhas (Predictive Tracking):** O código inclui um algoritmo de suavização (`lerp`) e proteção contra falhas da IA (*glitches* por sobreposição de dedos).
* **Sensor de Luz:** O sistema avalia a luminosidade do ambiente em tempo real e avisa o utilizador se as condições forem ideais para o rastreamento da webcam.
* **Dashboard Reativo:** Uma barra inferior que informa o utilizador sobre o estado atual da ferramenta.

## Tecnologias Utilizadas
* **HTML5 / CSS3** (Interface e estilização do ambiente)
* **JavaScript** (Lógica de programação)
* **p5.js** (Renderização gráfica do canvas e manipulação visual)
* **ml5.js (HandPose)** (Modelo de Inteligência Artificial para reconhecimento esquelético das mãos)

## Como Executar o Projeto
1. Fazer o download ou clonar a pasta do projeto.
2. Abrir a pasta num editor de código (ex: Visual Studio Code).
3. Iniciar um servidor local (recomenda-se a extensão *Live Server*).
4. Permitir o acesso à webcam no browser.

---
**Autor:** Gustavo Sanz Nº36640