class ConnectionScene extends Phaser.Scene {
    constructor() { super({ key: 'ConnectionScene' }); }
    init(data) { this.isSpectatorMode = data.isSpectatorMode; this.nomeJogador = data.nome; }
    create() {
        let w = this.cameras.main.width; let h = this.cameras.main.height;
        this.add.image(0, 0, 'bg_2').setOrigin(0, 0).setDisplaySize(w, h).setAlpha(0.2);
        this.add.text(w / 2, h / 2 - 100, 'A INVOCAR AS TREVAS...\n(A Ligar ao Servidor)', { fontFamily: retroFont, fontSize: '42px', fill: '#8a2be2', align: 'center' }).setOrigin(0.5);
        this.loadingSprite = this.add.sprite(w / 2, h / 2 + 20, 'loading_bar').setOrigin(0.5);
        this.tweens.addCounter({ from: 0, to: 11, duration: 800, repeat: -1, onUpdate: (tween) => { this.loadingSprite.setFrame(Math.floor(tween.getValue())); } });

        let queryStr = this.isSpectatorMode ? "tipo=espectador" : "";
        this.socket = io({ query: queryStr, reconnectionAttempts: 1, timeout: 5000 });

        this.socket.on('connect', () => {
            this.time.delayedCall(600, () => { 
                this.socket.removeAllListeners(); 
                // NOTA: Mudámos 'TestScene' para 'L1' aqui também!
                this.scene.start('L1', { modo: 'multiplayer', socketObj: this.socket, isSpectatorMode: this.isSpectatorMode, nome: this.nomeJogador });
            });
        });

        this.socket.on('nomeDuplicado', (data) => {
            this.socket.disconnect(); 
            this.scene.start('MenuScene', { erro: `[CÓDIGO ${data.code}]\nErro: O nome "${data.nome}" já está a ser usado.\nPor favor, clica em "Mudar de Nome" no menu.` });
        });

        this.socket.on('connect_error', () => { this.socket.disconnect(); this.scene.start('MenuScene', { erro: '[ERR-500]\nO servidor Host está offline ou inacessível.' }); });
    }
}