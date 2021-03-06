package fiuba.algo3.modelos.algoformers;

import fiuba.algo3.modelos.Tablero;

public class ModoHumanoide extends Modo {

    public ModoHumanoide(Algoformer algoformer, Tablero tablero) {
        super(algoformer, tablero);
        this.movimiento = algoformer.getMovimientoHumanoide(tablero);
    }

    public int getAtaqueInicial() {
        return this.algoformer.getAtaqueInicialHumanoide();
    }

    public int getDistanciaAtaque() {
        return this.algoformer.getDistanciaAtaqueHumanoide();
    }

    public int getVelocidad() {
        return this.algoformer.getVelocidadHumanoide();
    }

    public boolean esHumanoide() {
        return true;
    }
    
    public int getVida() {
    	return this.algoformer.getVidaInicial();
    }
}
