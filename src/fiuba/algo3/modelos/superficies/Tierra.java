package fiuba.algo3.modelos.superficies;

import fiuba.algo3.modelos.algoformers.Algoformer;

public abstract class Tierra extends Superficie{

	public static final String NOMBRE_JSON = "Tierra";

	public abstract void afectarAlgoformerHumanoide(Algoformer algoformer);

	public abstract void afectarAlgoformerAlterno(Algoformer algoformer);
	
	public String getNombreJSON() {
		return NOMBRE_JSON;
	}
}
