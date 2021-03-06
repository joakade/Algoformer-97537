package fiuba.algo3.modelos;

import fiuba.algo3.modelos.superficies.*;
import org.json.simple.JSONObject;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import fiuba.algo3.vistas.CasilleroVista;

public class Casillero {
	private static final String JSON_FIELD_KEY_TIERRA = "tierra";
	private static final String JSON_FIELD_KEY_AIRE = "aire";


	private Contenido contenido;
	
	private Tierra tierra;
	private Aire aire;
	private Posicion posicion;

	public Casillero(Posicion posicion) {
		this.contenido = Vacio.getInstancia();
		this.posicion = posicion;
	}

	public void setSuperficies(JSONObject superficies) {

		String superficieTierra = superficies.get(JSON_FIELD_KEY_TIERRA).toString();
		String superficieAire = superficies.get(JSON_FIELD_KEY_AIRE).toString();

		switch (superficieTierra) {
			case Rocas.NOMBRE_JSON:
				this.tierra = new Rocas();
				break;
			case Pantano.NOMBRE_JSON:
				this.tierra = new Pantano();
				break;
			case Espinas.NOMBRE_JSON:
				this.tierra = new Espinas();
				break;
		}

		switch (superficieAire) {
			case Nube.NOMBRE_JSON:
				this.aire = new Nube();
				break;
			case NebulosaDeAndromeda.NOMBRE_JSON:
				this.aire = new NebulosaDeAndromeda();
				break;
			case TormentaPsionica.NOMBRE_JSON:
				this.aire = new TormentaPsionica();
				break;
		}
	}


	public Contenido getContenido() {
		return this.contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}

	public Tierra getSuperficieTierra() {
		return this.tierra;
	}

	public Aire getSuperficieAire() {
		return this.aire;
	}
	
	public Superficie getSuperficie (String superficie) {
		switch(superficie) {
			case JSON_FIELD_KEY_TIERRA: return this.tierra; 
			case JSON_FIELD_KEY_AIRE: return this.aire; 
		}
		return this.tierra;
	}
	
	public boolean estaVacio(){ 
		return (this.contenido==Vacio.getInstancia());
	}
	
	public void quitarContenido(){
		this.contenido = Vacio.getInstancia();
	}

	public Posicion getPosicion() {
		return this.posicion;
	}
	
}
