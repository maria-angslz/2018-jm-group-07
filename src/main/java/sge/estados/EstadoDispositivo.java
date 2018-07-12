package sge.estados;
import sge.dispositivos.inteligentes.DispositivoInteligente;

public interface EstadoDispositivo {
	public boolean encendido();
	public void apagarse(DispositivoInteligente disp);
	public void encenderse(DispositivoInteligente disp);
	public void entrarEnModoAhorroDeEnergia(DispositivoInteligente disp);
}
