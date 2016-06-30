package fiuba.algo3.controladores;

import fiuba.algo3.modelos.Casillero;
import fiuba.algo3.modelos.Jugador;
import fiuba.algo3.modelos.algoformers.Algoformer;
import fiuba.algo3.modelos.excepciones.*;
import fiuba.algo3.vistas.AlgoformerVista;
import fiuba.algo3.vistas.MostrarAlUsuarioError;
import javafx.event.EventHandler;
import fiuba.algo3.vistas.TableroVista;
import javafx.scene.input.MouseEvent;
import fiuba.algo3.vistas.CasilleroVista;
import fiuba.algo3.modelos.Posicion;


public class MouseClickHandler {

	private TableroVista tableroVista;

	public MouseClickHandler(TableroVista tableroVista) {

		this.tableroVista = tableroVista;
		
		this.tableroVista.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			Algoformer algoformerQueRealizaAccion;
            Posicion posicionDestinoDeLaAccion;

            @Override
            public void handle(MouseEvent e) {
                if (tableroVista.getAccion() != TableroVista.ACCION_NADA) {

                    if (e.getTarget() instanceof AlgoformerVista) {

                        Algoformer algoformer = ((AlgoformerVista) e.getTarget()).getAlgoformer();

                        if (this.algoformerQueRealizaAccion == null) {
                            this.algoformerQueRealizaAccion = algoformer;
                        } else {
                            this.posicionDestinoDeLaAccion = algoformer.getPosicion();
                        }

                    } else if (e.getTarget() instanceof CasilleroVista) {
                        if (this.algoformerQueRealizaAccion != null) {
                            this.posicionDestinoDeLaAccion = ((CasilleroVista) e.getTarget()).getCasillero().getPosicion();
                        }
                    }

                    if (this.algoformerQueRealizaAccion != null &&
                            (this.posicionDestinoDeLaAccion != null
                                    || tableroVista.getAccion() == TableroVista.ACCION_TRANSFORMAR
                                    || tableroVista.getAccion() == TableroVista.ACCION_COMBINAR)) {

                        this.realizarAccion();
                        this.algoformerQueRealizaAccion = null;
                        this.posicionDestinoDeLaAccion = null;
                    }
                }

            }


            public void realizarAccion() {

                switch (tableroVista.getAccion()) {
                    case TableroVista.ACCION_MOVER:
                       
                        try {
                            this.algoformerQueRealizaAccion.mover(this.posicionDestinoDeLaAccion);
                            tableroVista.actualizarRobotsYChispa();

                        } catch (MovimientoInvalidoCasilleroOcupadoExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (MovimientoInvalidoDistanciaNoValidaExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (MovimientoInvalidoIncapazDeAtravezarSuperficieExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (AlgoformerInmovilizadoExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (MovimientoInvalidoCasilleroInvalidoExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (NoEsElTurnoDelJugadorExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (Exception e) {
                            new MostrarAlUsuarioError(new ErrorDesconocidoEnLaVistaExcepcion() );
                        };

                        break;

                    case TableroVista.ACCION_ATACAR:
                        try {
                            Casillero casillero = tableroVista.getTablero().getCasillero(this.posicionDestinoDeLaAccion);

                            if (casillero.estaVacio() || casillero.getContenido().esChispa()) {
                                String titulo = "Ataque Inv\u00e1lido";
                                String header = "El casillero de destino no tiene un algoformer.";
                                String content = "Debes atacar casilleros ocupados por robots del otro jugador.";
                                new AlertHandler(titulo, header, content);
                            } else {
                                this.algoformerQueRealizaAccion.atacar((Algoformer) casillero.getContenido());
                                tableroVista.actualizarRobotsYChispa();
                            }

                        } catch (AtaqueInvalidoDistanciaInsuficienteExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (NoEsElTurnoDelJugadorExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (AtaqueInvalidoFriendlyFireNoEstaHabilitadoExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (Exception e) {
                            new MostrarAlUsuarioError(new ErrorDesconocidoEnLaVistaExcepcion() );
                        }
                        break;
                        
                    case TableroVista.ACCION_TRANSFORMAR:
                    	try {
                    		this.algoformerQueRealizaAccion.cambiarModo();
                            tableroVista.actualizarRobotsYChispa();

                    	} catch (MenasorNoPuedeTransformarseExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                    	} catch (SuperionNoPuedeTransformarseExcepcion excepcion){
                            new MostrarAlUsuarioError(excepcion);

                        } catch (NoEsElTurnoDelJugadorExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (AlgoformerCombinadoNoPuedeTransformarseExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (Exception e) {
                            new MostrarAlUsuarioError(new ErrorDesconocidoEnLaVistaExcepcion() );
                        }
                        break;
                        
                    case TableroVista.ACCION_COMBINAR:
                    	try {
                    		Jugador jugador = this.algoformerQueRealizaAccion.getTurno().getJugadorDelQueEsElTurno();
                           	jugador.combinarAlgoformers(this.algoformerQueRealizaAccion.getTurno());
                           	tableroVista.actualizarRobotsYChispa();

                        } catch (AlgoformersNoAlineadosException excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (CantidadDeAlgoformersInsuficienteException excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (NoEsElTurnoDelJugadorExcepcion excepcion) {
                            new MostrarAlUsuarioError(excepcion);

                        } catch (Exception e) {
                            new MostrarAlUsuarioError(new ErrorDesconocidoEnLaVistaExcepcion() );
		                }
                    	break;

                }

                tableroVista.setAccion(TableroVista.ACCION_NADA);
            }

        });
	}

}
