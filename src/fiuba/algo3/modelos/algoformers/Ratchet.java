package fiuba.algo3.modelos.algoformers;

import fiuba.algo3.modelos.Posicion;
import fiuba.algo3.modelos.Tablero;
import fiuba.algo3.modelos.Turno;
import fiuba.algo3.modelos.movimientos.Movimiento;
import fiuba.algo3.modelos.movimientos.MovimientoAlternoAereo;

public class Ratchet extends Autobot {
	public static final String nombreAlgoformer = "Ratchet";
	private static final int ATAQUE_HUMANOIDE = 5;
	private static final int DISTANCIA_ATAQUE_HUMANOIDE = 5;
	private static final int VELOCIDAD_HUMANOIDE = 1;
	private static final int ATAQUE_ALTERNO = 35;
	private static final int DISTANCIA_ATAQUE_ALTERNO = 2;
	private static final int VELOCIDAD_ALTERNO = 8;
	private static final int VIDA = 150;
	
	public Ratchet(Posicion posicion, Tablero tablero, Turno turno) {
		super(posicion, tablero, turno);
		this.vida = VIDA;
	}

	public int getAtaqueInicialHumanoide() {
		return ATAQUE_HUMANOIDE;
	}
	public int getDistanciaAtaqueHumanoide() {
		return DISTANCIA_ATAQUE_HUMANOIDE;
	}
	public int getVelocidadHumanoide() {
		return VELOCIDAD_HUMANOIDE;
	}
	public int getAtaqueInicialAlterno() {
		return ATAQUE_ALTERNO;
	}
	public int getDistanciaAtaqueAlterno() {
		return DISTANCIA_ATAQUE_ALTERNO;
	}
	public int getVelocidadAlterno() {
		return VELOCIDAD_ALTERNO;
	}
	
	public int getVidaInicial() {
		return VIDA;
	}

	@Override
	public Algoformer clonarAlgoformer() {
		Algoformer clon = new Ratchet(this.getPosicion(), this.modo.getTablero(), this.turno);
		this.copiarA(clon);
		return clon;
	}

	public Movimiento getMovimientoAlterno() {
		return new MovimientoAlternoAereo(this.modo.getTablero());
	}

	public String getNombre() {
		return this.nombreAlgoformer;
	}
}
