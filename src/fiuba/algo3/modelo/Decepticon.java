package fiuba.algo3.modelo;

import fiuba.algo3.modelo.Algoformer;

public abstract class Decepticon extends Algoformer {

	public boolean puedeAtacarA(Algoformer otroAlgoformer) {
		return !otroAlgoformer.esDecepticon();
	}

	public boolean esDecepticon() {
		return true;
	}

	public void agregarATablero(){
		//AL TABLERO LE TENGO QUE MANDAR UBICARDECEPTICON(THIS);
	}
}
