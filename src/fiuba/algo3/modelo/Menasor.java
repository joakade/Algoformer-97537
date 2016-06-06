package fiuba.algo3.modelo;

import fiuba.algo3.modelo.Decepticon;
import fiuba.algo3.modelo.MovimientoHumanoide;
import fiuba.algo3.modelo.TransformacionIncorrectaMenasorNoPuedeTransformarse;

public class Menasor extends Decepticon {
	private static final int DISTANCIA_ATAQUE = 2;
	private static final int ATAQUE = 115;
	private static final int VELOCIDAD = 2;
	private static final int VIDA = 2;
	
	public Menasor() {
		this.vida = VIDA;
		this.ataque = ATAQUE;
		this.distanciaDeAtaque = DISTANCIA_ATAQUE;
		this.velocidad = VELOCIDAD;
		this.movimiento = new MovimientoHumanoide();
	}

	public void transformarHumanoide() {
		throw new TransformacionIncorrectaMenasorNoPuedeTransformarse();
	}
	
	public void transformarAlterno(){
		throw new TransformacionIncorrectaMenasorNoPuedeTransformarse();
	}
}
