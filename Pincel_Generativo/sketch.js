// ==========================================
// VARIÁVEIS GLOBAIS
// ==========================================
let video; // Guarda o feed da nossa webcam
let handPose; // O modelo de Inteligência Artificial
let hands = new Array(); // A lista onde a IA guarda as coordenadas das mãos
let pintura; // A nossa "folha de vidro" invisível onde a tinta fica guardada

// Memória do pincel para fazer linhas contínuas e prever falhas (Predictive Tracking)
let pincelAnterior = new Array(
  { x: 0, y: 0, pintando: false },
  { x: 0, y: 0, pintando: false }
);

// Variáveis para a Interface de Utilizador (UI) e sensores
let luzMedia = 255; 
let avisoEscuro = false;
let mostrarAjuda = true; 
let ferramentaAtiva = "À PROCURA DE MÃOS...";

// ==========================================
// PRELOAD: Carrega a IA antes do programa iniciar
// ==========================================
function preload() {
  let opcoes = {
    maxHands: 2, // Forçamos a IA a procurar ativamente até 2 mãos
    modelType: "full" // Usamos o modelo mais pesado e preciso para evitar falhas
  };
  handPose = ml5.handPose(opcoes);
}

// ==========================================
// SETUP: Configurações Iniciais
// ==========================================
function setup() {
  // Criamos o ecrã principal e o vídeo com medidas fixas
  createCanvas(640, 480);
  video = createCapture(VIDEO);
  video.size(640, 480);
  video.hide(); // Escondemos o vídeo original em HTML para o desenharmos nós no p5.js
  
  // A camada permanente: tem de ter o mesmo tamanho do ecrã
  pintura = createGraphics(640, 480);
  
  // Ligamos a câmara à IA e guardamos os resultados na variável 'hands'
  handPose.detectStart(video, results => {
    hands = results;
  });
}

// ==========================================
// DRAW: O loop principal que corre 60 vezes por segundo
// ==========================================
function draw() {
  background(0);
  
  // -----------------------------------------------------
  // MODO ESPELHO: Inverte a imagem para ser natural desenhar
  // -----------------------------------------------------
  push(); // Inicia as transformações visuais
  translate(width, 0);
  scale(-1, 1); // Vira o eixo X ao contrário (Efeito Espelho)
  
  image(video, 0, 0, width, height); // Desenha a câmara
  image(pintura, 0, 0, width, height); // Desenha a tinta permanente por cima
  
  // Atualiza o texto do menu consoante as mãos detetadas
  if (hands.length > 0) {
    ferramentaAtiva = "REPOUSO";
  } else {
    ferramentaAtiva = "À PROCURA DE MÃOS...";
  }
  
  let laserAtivo = false;
  
  // ==========================================
  // FERRAMENTA 1: RAIO LASER (2 Mãos)
  // ==========================================
  if (hands.length >= 2) {
    let h1 = hands.at(0);
    let h2 = hands.at(1);
    
    // Calcula o tamanho da palma da mão para termos uma "régua" matemática adaptável
    let tamanhoPalma1 = dist(h1.keypoints[0].x, h1.keypoints[0].y, h1.keypoints[9].x, h1.keypoints[9].y);
    let tamanhoPalma2 = dist(h2.keypoints[0].x, h2.keypoints[0].y, h2.keypoints[9].x, h2.keypoints[9].y);
    
    // Filtro Anti-Cara: A palma tem de ter pelo menos 15 píxeis (ignora rostos/óculos confundidos com mãos)
    if (tamanhoPalma1 > 15 && tamanhoPalma2 > 15) {
      let ind1 = h1.keypoints[8];
      let med1 = h1.keypoints[12];
      let ind2 = h2.keypoints[8];
      let med2 = h2.keypoints[12];
      
      // Proporções: Dedo esticado = 140% da palma | Dedo dobrado = 110% da palma
      let esticado1 = tamanhoPalma1 * 1.4; 
      let dobrado1 = tamanhoPalma1 * 1.1;  
      let esticado2 = tamanhoPalma2 * 1.4;
      let dobrado2 = tamanhoPalma2 * 1.1;
      
      // Verifica se as DUAS mãos estão apenas com o indicador esticado
      let mao1Aponta = (dist(h1.keypoints[0].x, h1.keypoints[0].y, ind1.x, ind1.y) > esticado1 && dist(h1.keypoints[0].x, h1.keypoints[0].y, med1.x, med1.y) < dobrado1);
      let mao2Aponta = (dist(h2.keypoints[0].x, h2.keypoints[0].y, ind2.x, ind2.y) > esticado2 && dist(h2.keypoints[0].x, h2.keypoints[0].y, med2.x, med2.y) < dobrado2);
      
      if (mao1Aponta && mao2Aponta) {
        laserAtivo = true;
        ferramentaAtiva = "⚡ RAIO LASER ⚡"; 
        
        // Desenha o Laser DIRETAMENTE no ecrã (não na 'pintura'), logo é temporário
        stroke(random(100, 255), 255, random(200, 255), 150); // Brilho exterior (Glow)
        strokeWeight(25); 
        line(ind1.x, ind1.y, ind2.x, ind2.y);
        
        stroke(255); // Núcleo branco sólido do laser
        strokeWeight(6); 
        line(ind1.x, ind1.y, ind2.x, ind2.y);
        
        fill(255); noStroke(); circle(ind1.x, ind1.y, 25); circle(ind2.x, ind2.y, 25); // Bolas nas pontas
      }
    }
  }
  
  // ==========================================
  // LÓGICA DOS PINCEIS INDIVIDUAIS (1 Mão)
  // ==========================================
  for (let i = 0; i < hands.length; i++) {
    let hand = hands.at(i);
    let pulso = hand.keypoints[0]; 
    let palma = hand.keypoints[9]; // O ponto 9 é o centro geométrico da mão
    
    // A nossa "Régua Relativa": Permite que os gestos funcionem perto ou longe da câmara
    let tamanhoPalma = dist(pulso.x, pulso.y, palma.x, palma.y);
    if (tamanhoPalma < 15) continue; // Escudo Anti-Cara
    
    let dedoEsticado = tamanhoPalma * 1.4; 
    let dedoDobrado = tamanhoPalma * 1.1;  
    
    // Desenha o Esqueleto Verde de debugging
    for (let j = 0; j < hand.keypoints.length; j++) {
      let ponto = hand.keypoints[j];
      fill(0, 255, 0); noStroke(); circle(ponto.x, ponto.y, 8);
    }
    
    // Atribui coordenadas às pontas dos dedos
    let indicador = hand.keypoints[8];
    let dedoMedio = hand.keypoints[12];
    let anelar = hand.keypoints[16];
    let mindinho = hand.keypoints[20];
    
    // Calcula a distância da ponta do dedo até ao pulso
    let distIndicador = dist(pulso.x, pulso.y, indicador.x, indicador.y);
    let distMedio = dist(pulso.x, pulso.y, dedoMedio.x, dedoMedio.y);
    let distAnelar = dist(pulso.x, pulso.y, anelar.x, anelar.y);
    let distMindinho = dist(pulso.x, pulso.y, mindinho.x, mindinho.y);
    
    let anterior = pincelAnterior.at(i);
    if (!anterior) continue; 
    
    // Só permite pintar se o Laser não estiver ligado (evita conflitos de desenho)
    if (!laserAtivo) {
      
      let centroDoisDedosX = (indicador.x + dedoMedio.x) / 2;
      let centroDoisDedosY = (indicador.y + dedoMedio.y) / 2;
      
      // ------------------------------------------------
      // FERRAMENTA 2: PINCEL PRETO (Apenas Indicador Esticado)
      // ------------------------------------------------
      if (distIndicador > dedoEsticado && distMedio < dedoDobrado) {
        ferramentaAtiva = "🖌️ PINCEL PRETO";
        
        if (anterior.pintando) { // Se já estava a pintar no frame passado
          // O salto mede a distância entre a posição anterior e a atual
          if (dist(anterior.x, anterior.y, indicador.x, indicador.y) < 150) {
            // LERP (Linear Interpolation): Suaviza o movimento para evitar traços robóticos
            let sx = lerp(anterior.x, indicador.x, 0.4);
            let sy = lerp(anterior.y, indicador.y, 0.4);
            
            pintura.stroke(0); pintura.strokeWeight(30); 
            pintura.line(anterior.x, anterior.y, sx, sy); // Desenha a linha na camada permanente
            anterior.x = sx; anterior.y = sy;
          } else { 
            // PREVENÇÃO DE GLITCHES: Se o salto foi enorme, a IA falhou. Teleporta sem riscar!
            anterior.x = indicador.x; anterior.y = indicador.y; 
          }
        } else {
          // Se começou a pintar AGORA, faz só um ponto inicial
          pintura.noStroke(); pintura.fill(0); pintura.circle(indicador.x, indicador.y, 30);
          anterior.x = indicador.x; anterior.y = indicador.y;
        }
        anterior.pintando = true;
        fill(255, 0, 0); circle(indicador.x, indicador.y, 25); // Feedback visual na unha
      } 
      // ------------------------------------------------
      // FERRAMENTA 3: PINCEL MÁGICO (Sinal de "V" / Paz)
      // ------------------------------------------------
      else if (distIndicador > dedoEsticado && distMedio > dedoEsticado && distAnelar < dedoDobrado) {
        ferramentaAtiva = "✨ PINCEL MÁGICO";
        
        // MATEMÁTICA GENERATIVA: Usa Senos e Cossenos baseados no tempo para mudar de cor sozinho
        let r = (sin(frameCount * 0.05) * 127) + 128;
        let g = (cos(frameCount * 0.05) * 127) + 128;
        
        if (anterior.pintando) {
          if (dist(anterior.x, anterior.y, centroDoisDedosX, centroDoisDedosY) < 150) {
            let sx = lerp(anterior.x, centroDoisDedosX, 0.4);
            let sy = lerp(anterior.y, centroDoisDedosY, 0.4);
            pintura.stroke(r, g, 255); pintura.strokeWeight(15); 
            pintura.line(anterior.x, anterior.y, sx, sy);
            anterior.x = sx; anterior.y = sy;
          } else { anterior.x = centroDoisDedosX; anterior.y = centroDoisDedosY; }
        } else {
          pintura.noStroke(); pintura.fill(r, g, 255); pintura.circle(centroDoisDedosX, centroDoisDedosY, 15);
          anterior.x = centroDoisDedosX; anterior.y = centroDoisDedosY;
        }
        anterior.pintando = true;
        fill(0, 200, 255); circle(centroDoisDedosX, centroDoisDedosY, 20); // Ponto a flutuar entre os dedos
      }
      // ------------------------------------------------
      // FERRAMENTA 4: BORRACHA (Mão Totalmente Aberta)
      // ------------------------------------------------
      else if (distIndicador > dedoEsticado && distMedio > dedoEsticado && distAnelar > dedoEsticado && distMindinho > dedoEsticado) {
        ferramentaAtiva = "🧽 BORRACHA";
        anterior.pintando = false; 
        
        // A função erase() recorta os píxeis transparentes na tela de pintura
        pintura.erase(); pintura.fill(255); pintura.noStroke();
        pintura.circle(palma.x, palma.y, 100); pintura.noErase(); 
        
        fill(255, 255, 255, 120); circle(palma.x, palma.y, 100); // Feedback visual semi-transparente
      } 
      else { 
        anterior.pintando = false; // Se não faz nenhum gesto conhecido, para de pintar
      }
    } else { 
      anterior.pintando = false; 
    }
  }
  pop(); // FECHA O MODO ESPELHO (Tudo a partir daqui não é invertido, útil para os textos)
  
  // ==========================================
  // DASHBOARD E INTERFACE (UI)
  // ==========================================
  
  // Barra Inferior escura para esconder os limites do ecrã e falhas de rastreamento no pulso
  fill(15, 15, 15, 230); noStroke(); rect(0, height - 80, width, 80); 
  
  fill(150); textSize(14); textAlign(CENTER, BOTTOM); 
  text("FERRAMENTA ATUAL", width / 2, height - 50);
  
  fill(0, 255, 255); textSize(24); textAlign(CENTER, TOP); 
  text(ferramentaAtiva, width / 2, height - 45); // Mostra o que estamos a fazer no momento

  // Corre o Sensor de Luz a cada 60 frames (1 segundo) para poupar processamento
  if (frameCount % 60 === 0) medirLuzAmbiente();
  
  // Mostra um aviso vermelho se a sala estiver muito escura
  if (avisoEscuro && frameCount % 60 < 30) {
    fill(255, 0, 0); noStroke(); rect(0, 0, width, 40); 
    fill(255); textSize(18); textAlign(CENTER, CENTER); text("⚠️ AVISO: Pouca Luz!", width / 2, 20);
  }

  // Desenho do Menu de Ajuda
  if (mostrarAjuda) {
    fill(0, 0, 0, 200); noStroke(); rect(20, 50, 300, 200, 15); 
    fill(255); textAlign(LEFT, TOP); textSize(18); text("📖 MENU DE AJUDA", 40, 65);
    textSize(14); fill(200); text("(Pressiona 'H' para Ocultar)", 40, 85);
    fill(255); text("☝️ 1 Dedo: Preto", 40, 120); text("✌️ 2 Dedos: Mágico", 40, 145);
    text("🖐️ Mão Aberta: Borracha", 40, 170); text("☝️〰️☝️ 2 Mãos: Laser", 40, 195);
    fill(255, 255, 0); text("💾 'S' para Guardar", 40, 220);
  }
}

// ==========================================
// EVENTOS DE TECLADO
// ==========================================
function keyPressed() {
  if (key === 'h' || key === 'H') mostrarAjuda = !mostrarAjuda;
  // A função saveCanvas descarrega APENAS a nossa camada de 'pintura' (sem a webcam por trás!)
  if (key === 's' || key === 'S') saveCanvas(pintura, 'MinhaArte', 'png');
}

// ==========================================
// SENSOR DE LUZ (Performance Otimizada)
// ==========================================
function medirLuzAmbiente() {
  if (video.width > 0 && video.height > 0) {
    let soma = 0, conta = 0;
    
    // Em vez de ler todos os píxeis, lê apenas 1 píxel a cada 100 (amostragem)
    for (let y = 50; y < video.height; y += 100) {
      for (let x = 50; x < video.width; x += 100) {
        let c = video.get(x, y);
        if (c) { 
          soma += (c[0] + c[1] + c[2]) / 3; // Calcula a média RGB (Luminosidade)
          conta++; 
        }
      }
    }
    if (conta > 0) avisoEscuro = (soma / conta < 60); // Menos de 60 de brilho ativa o aviso
  }
}