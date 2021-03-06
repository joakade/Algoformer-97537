package fiuba.algo3.tests.unitarios.excepciones;

import fiuba.algo3.modelos.Posicion;
import fiuba.algo3.modelos.algoformers.Algoformer;
import fiuba.algo3.modelos.Jugador;
import fiuba.algo3.modelos.excepciones.NoEsElTurnoDelJugadorExcepcion;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelos.Juego;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class NoEsElTurnoDelJugadorExcepcionTest {

	@Test(expected = NoEsElTurnoDelJugadorExcepcion.class)
	public void testMuevoDosVecesSeguidasMismoAlgoformerYLanzaExcepcion() {
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonTablero = (JSONObject) parser.parse(new FileReader("mapas/1.json"));
			Juego juego = new Juego(jsonTablero, "Pedro", "Juan");
			Jugador jugador1 = juego.getJugador1();
			Iterator<Algoformer> robots = jugador1.getAlgoformersIterator();

			Algoformer unRobotJugador1 = robots.next();
			unRobotJugador1.mover( new Posicion(2,0) );

			unRobotJugador1.mover( new Posicion(4,0) );

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testMuevoDosAlgoformersDeDistintoJugadorYNoLanzaExcepcion() {
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonTablero = (JSONObject) parser.parse(new FileReader("mapas/1.json"));
			Juego juego = new Juego(jsonTablero, "Pedro", "Juan");

			Jugador jugador1 = juego.getJugador1();
			Iterator<Algoformer> robotsJugador1 = jugador1.getAlgoformersIterator();
			Algoformer unRobotJugador1 = robotsJugador1.next();
			unRobotJugador1.mover( new Posicion(2,0) );

			Jugador jugador2 = juego.getJugador2();
			Iterator<Algoformer> robotsJugador2 = jugador2.getAlgoformersIterator();
			Algoformer unRobotJugador2 = robotsJugador2.next();

			try {
				unRobotJugador2.mover( new Posicion(8,3) );
			} catch (NoEsElTurnoDelJugadorExcepcion e) {
				Assert.fail();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	@Test(expected = NoEsElTurnoDelJugadorExcepcion.class)
	public void testAtacoDosVecesSeguidasConElMismoAlgoformerYLanzaExcepcion() {
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonTablero = (JSONObject) parser.parse(new FileReader("mapas/mapaTestAtaques.json"));
			Juego juego = new Juego(jsonTablero, "Pedro", "Juan");

			Jugador jugador1 = juego.getJugador1();
			Iterator<Algoformer> robots = jugador1.getAlgoformersIterator();
			robots.next();
			robots.next();
			Algoformer robotJugador1 = robots.next();

			Jugador jugador2 = juego.getJugador2();
			Iterator<Algoformer> robotsJugador2 = jugador2.getAlgoformersIterator();
			Algoformer unRobotJugador2 = robotsJugador2.next();

			robotJugador1.atacar(unRobotJugador2);

			robotJugador1.atacar(unRobotJugador2);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testAtacoDesdeUnAlgoformerYLuegoDesdeUnoDelOtroJugadorYNoLanzaExcepcion() {
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonTablero = (JSONObject) parser.parse(new FileReader("mapas/mapaTestAtaques.json"));
			Juego juego = new Juego(jsonTablero, "Pedro", "Juan");

			Jugador jugador1 = juego.getJugador1();
			Iterator<Algoformer> robots = jugador1.getAlgoformersIterator();
			robots.next();
			robots.next();
			Algoformer unRobotJugador1 = robots.next();

			Jugador jugador2 = juego.getJugador2();
			Iterator<Algoformer> robotsJugador2 = jugador2.getAlgoformersIterator();
			robotsJugador2.next();
			robotsJugador2.next();
			Algoformer unRobotJugador2 = robotsJugador2.next();

			unRobotJugador1.atacar(unRobotJugador2);

			try {
				unRobotJugador2.atacar(unRobotJugador1);
			} catch (NoEsElTurnoDelJugadorExcepcion e) {
				Assert.fail();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}


}
