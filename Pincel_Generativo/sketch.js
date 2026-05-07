let video;
let handPose;
let hands = new Array();
let pintura;

let pincelAnterior = new Array(
  { x: 0, y: 0, pintando: false },
  { x: 0, y: 0, pintando: false }
);

let luzMedia = 255; 
let avisoEscuro = false;
let mostrarAjuda = true; 
let ferramentaAtiva = "À PROCURA DE MÃOS...";

function preload() {
  let opcoes = {
    maxHands: 2,
    modelType: "full"
  };
  handPose = ml5.handPose(opcoes);
}

function setup() {
  createCanvas(640, 480);
  video = createCapture(VIDEO);
  video.size(640, 480);
  video.hide();
  
  pintura = createGraphics(640, 480);
  
  handPose.detectStart(video, results => {
    hands = results;
  });
}

function draw() {
  background(0);
  
  push();
  translate(width, 0);
  scale(-1, 1);
  
  image(video, 0, 0, width, height);
  image(pintura, 0, 0, width, height); 
  
  if (hands.length > 0) {
    ferramentaAtiva = "REPOUSO";
  } else {
    ferramentaAtiva = "À PROCURA DE MÃOS...";
  }
  
  let laserAtivo = false;
  
  // ==========================================
  // GESTO DE 2 MÃOS: O RAIO LASER (AGORA DINÂMICO!)
  // ==========================================
  if (hands.length >= 2) {
    let h1 = hands.at(0);
    let h2 = hands.at(1);
    
    // Calcula o tamanho real da palma de cada mão
    let tamanhoPalma1 = dist(h1.keypoints[0].x, h1.keypoints[0].y, h1.keypoints[9].x, h1.keypoints[9].y);
    let tamanhoPalma2 = dist(h2.keypoints[0].x, h2.keypoints[0].y, h2.keypoints[9].x, h2.keypoints[9].y);
    
    // O novo filtro anti-cara: a palma tem de ser maior que 15px
    if (tamanhoPalma1 > 15 && tamanhoPalma2 > 15) {
      let ind1 = h1.keypoints[8];
      let med1 = h1.keypoints[12];
      let ind2 = h2.keypoints[8];
      let med2 = h2.keypoints[12];
      
      // As nossas novas "Réguas Mágicas" baseadas em percentagem
      let esticado1 = tamanhoPalma1 * 1.4; // 140% do tamanho da palma
      let dobrado1 = tamanhoPalma1 * 1.1;  // 110% do tamanho da palma
      
      let esticado2 = tamanhoPalma2 * 1.4;
      let dobrado2 = tamanhoPalma2 * 1.1;
      
      let mao1Aponta = (dist(h1.keypoints[0].x, h1.keypoints[0].y, ind1.x, ind1.y) > esticado1 && dist(h1.keypoints[0].x, h1.keypoints[0].y, med1.x, med1.y) < dobrado1);
      let mao2Aponta = (dist(h2.keypoints[0].x, h2.keypoints[0].y, ind2.x, ind2.y) > esticado2 && dist(h2.keypoints[0].x, h2.keypoints[0].y, med2.x, med2.y) < dobrado2);
      
      if (mao1Aponta && mao2Aponta) {
        laserAtivo = true;
        ferramentaAtiva = "⚡ RAIO LASER ⚡"; 
        stroke(random(100, 255), 255, random(200, 255), 150); 
        strokeWeight(25); 
        line(ind1.x, ind1.y, ind2.x, ind2.y);
        stroke(255); 
        strokeWeight(6); 
        line(ind1.x, ind1.y, ind2.x, ind2.y);
        fill(255); noStroke(); circle(ind1.x, ind1.y, 25); circle(ind2.x, ind2.y, 25);
      }
    }
  }
  
  // ==========================================
  // LÓGICA INDIVIDUAL DOS PINCEIS (DINÂMICA)
  // ==========================================
  for (let i = 0; i < hands.length; i++) {
    let hand = hands.at(i);
    let pulso = hand.keypoints[0]; 
    let palma = hand.keypoints[9]; // Ponto central da mão (a nossa base)
    
    // A nossa NOVA RÉGUA PROPORCIONAL
    let tamanhoPalma = dist(pulso.x, pulso.y, palma.x, palma.y);
    
    // FILTRO ANTI-CARA RELATIVO
    if (tamanhoPalma < 15) continue; 
    
    // Os limites flexíveis (adaptam-se à distância)
    let dedoEsticado = tamanhoPalma * 1.4; // Exige que o dedo saia bem fora da palma
    let dedoDobrado = tamanhoPalma * 1.1;  // Se estiver perto da palma, está dobrado
    
    // Desenha esqueleto
    for (let j = 0; j < hand.keypoints.length; j++) {
      let ponto = hand.keypoints[j];
      fill(0, 255, 0); noStroke(); circle(ponto.x, ponto.y, 8);
    }
    
    let indicador = hand.keypoints[8];
    let dedoMedio = hand.keypoints[12];
    let anelar = hand.keypoints[16];
    let mindinho = hand.keypoints[20];
    
    // Medimos as pontas dos dedos até ao pulso
    let distIndicador = dist(pulso.x, pulso.y, indicador.x, indicador.y);
    let distMedio = dist(pulso.x, pulso.y, dedoMedio.x, dedoMedio.y);
    let distAnelar = dist(pulso.x, pulso.y, anelar.x, anelar.y);
    let distMindinho = dist(pulso.x, pulso.y, mindinho.x, mindinho.y);
    
    let anterior = pincelAnterior.at(i);
    if (!anterior) continue; 
    
    if (!laserAtivo) {
      
      let centroDoisDedosX = (indicador.x + dedoMedio.x) / 2;
      let centroDoisDedosY = (indicador.y + dedoMedio.y) / 2;
      
      // 1. PINCEL PRETO
      if (distIndicador > dedoEsticado && distMedio < dedoDobrado) {
        ferramentaAtiva = "🖌️ PINCEL PRETO";
        if (anterior.pintando) {
          if (dist(anterior.x, anterior.y, indicador.x, indicador.y) < 150) {
            let sx = lerp(anterior.x, indicador.x, 0.4);
            let sy = lerp(anterior.y, indicador.y, 0.4);
            pintura.stroke(0); pintura.strokeWeight(30); 
            pintura.line(anterior.x, anterior.y, sx, sy);
            anterior.x = sx; anterior.y = sy;
          } else { anterior.x = indicador.x; anterior.y = indicador.y; }
        } else {
          pintura.noStroke(); pintura.fill(0); pintura.circle(indicador.x, indicador.y, 30);
          anterior.x = indicador.x; anterior.y = indicador.y;
        }
        anterior.pintando = true;
        fill(255, 0, 0); circle(indicador.x, indicador.y, 25);
      } 
      // 2. PINCEL MÁGICO
      else if (distIndicador > dedoEsticado && distMedio > dedoEsticado && distAnelar < dedoDobrado) {
        ferramentaAtiva = "✨ PINCEL MÁGICO";
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
        fill(0, 200, 255); circle(centroDoisDedosX, centroDoisDedosY, 20);
      }
      // 3. BORRACHA
      else if (distIndicador > dedoEsticado && distMedio > dedoEsticado && distAnelar > dedoEsticado && distMindinho > dedoEsticado) {
        ferramentaAtiva = "🧽 BORRACHA";
        anterior.pintando = false; 
        pintura.erase(); pintura.fill(255); pintura.noStroke();
        pintura.circle(palma.x, palma.y, 100); pintura.noErase(); 
        fill(255, 255, 255, 120); circle(palma.x, palma.y, 100);
      } 
      else { anterior.pintando = false; }
    } else { anterior.pintando = false; }
  }
  pop(); 
  
  // --- UI INTERFACE ---
  fill(15, 15, 15, 230); noStroke(); rect(0, height - 80, width, 80); 
  fill(150); textSize(14); textAlign(CENTER, BOTTOM); text("FERRAMENTA ATUAL", width / 2, height - 50);
  fill(0, 255, 255); textSize(24); textAlign(CENTER, TOP); text(ferramentaAtiva, width / 2, height - 45);

  if (frameCount % 60 === 0) medirLuzAmbiente();
  if (avisoEscuro && frameCount % 60 < 30) {
    fill(255, 0, 0); noStroke(); rect(0, 0, width, 40); 
    fill(255); textSize(18); textAlign(CENTER, CENTER); text("⚠️ AVISO: Pouca Luz!", width / 2, 20);
  }

  if (mostrarAjuda) {
    fill(0, 0, 0, 200); noStroke(); rect(20, 50, 300, 200, 15); 
    fill(255); textAlign(LEFT, TOP); textSize(18); text("📖 MENU DE AJUDA", 40, 65);
    textSize(14); fill(200); text("(Pressiona 'H' para Ocultar)", 40, 85);
    fill(255); text("☝️ 1 Dedo: Preto", 40, 120); text("✌️ 2 Dedos: Mágico", 40, 145);
    text("🖐️ Mão Aberta: Borracha", 40, 170); text("☝️〰️☝️ 2 Mãos: Laser", 40, 195);
    fill(255, 255, 0); text("💾 'S' para Guardar", 40, 220);
  }
}

function keyPressed() {
  if (key === 'h' || key === 'H') mostrarAjuda = !mostrarAjuda;
  if (key === 's' || key === 'S') saveCanvas(pintura, 'MinhaArte', 'png');
}

function medirLuzAmbiente() {
  if (video.width > 0 && video.height > 0) {
    let soma = 0, conta = 0;
    for (let y = 50; y < video.height; y += 100) {
      for (let x = 50; x < video.width; x += 100) {
        let c = video.get(x, y);
        if (c) { soma += (c[0] + c[1] + c[2]) / 3; conta++; }
      }
    }
    if (conta > 0) avisoEscuro = (soma / conta < 60);
  }
}