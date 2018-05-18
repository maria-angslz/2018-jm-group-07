package sge.estados;
import sge.dispositivosInteligentes.DispositivoInteligente;

public interface EstadoDispositivo {
	public boolean encendido();
	public void apagarse(DispositivoInteligente disp);
	public void encenderse(DispositivoInteligente disp);
	public void entrarEnModoAhorroDeEnergia(DispositivoInteligente disp);
}
