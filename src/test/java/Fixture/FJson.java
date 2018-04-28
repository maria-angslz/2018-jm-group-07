package Fixture;


import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.mockito.Mock;

import sge.Administrador;
import sge.Documento;
import sge.TipoDocumento;
import sge.categorias.Categoria;
import sge.categorias.CategoriaResidencial;
import sge.clientes.Cliente;
import sge.dispositivos.Dispositivo;
import sge.persistencia.ServiceLocator;
import sge.persistencia.json.JSONWrapper;

public class FJson {
	@Mock
	protected
	JSONWrapper mockWrapper;
	List<Dispositivo> dispositivos;
	List<List<Dispositivo>> listasDeDispositivos;
	List<Cliente> clientes;
	protected List<List<Cliente>> listasDeClientes;
	List<Administrador> admins;
	protected List<List<Administrador>> listasDeAdmins;
	List<CategoriaResidencial> categorias;
	protected List<List<CategoriaResidencial>> listasDeCategorias;

	@Before
	public void init() throws FileNotFoundException {
		// Administradores
		admins = new ArrayList<Administrador>();
		LocalDate now = LocalDate.now();
		admins.add(new Administrador("Juan Perez", "Belgrano 2251",
				now.minusMonths(2)));
		admins.add(new Administrador("Pepe Perez", "Belgrano 21",
				now.minusMonths(3)));
		admins.add(new Administrador("Martin Perez", "Belgrano 251",
				now.minusMonths(4)));
		admins.add(new Administrador("Jose Perez",  "Belgrano 25",
				now.minusMonths(5)));
		// Listas de administradores
		listasDeAdmins = new ArrayList<List<Administrador>>();
		listasDeAdmins.add(new ArrayList<Administrador>());
		listasDeAdmins.add(Arrays.asList(admins.get(0)));
		listasDeAdmins.add(Arrays.asList(admins.get(1), admins.get(2)));
		listasDeAdmins.add(Arrays.asList(admins.get(3)));

		// Categorias
		categorias = new ArrayList<CategoriaResidencial>();
		categorias.add(new CategoriaResidencial(60.71, 0.681, 325, 400));
		categorias.add(new CategoriaResidencial(60.71, 0.681, 325, 400));
		categorias.add(new CategoriaResidencial(60.71, 0.681, 325, 400));
		categorias.add(new CategoriaResidencial(60.71, 0.681, 325, 400));
		// Listas de categorias
		listasDeCategorias = new ArrayList<List<CategoriaResidencial>>();
		listasDeCategorias.add(new ArrayList<CategoriaResidencial>());
		listasDeCategorias.add(Arrays.asList(categorias.get(0)));
		listasDeCategorias.add(Arrays.asList(categorias.get(1), categorias.get(2)));
		listasDeCategorias.add(Arrays.asList(categorias.get(3)));

		// Dispositivos
		dispositivos = new ArrayList<Dispositivo>();
		dispositivos.add(new Dispositivo("Microondas", 4.0, true));
		dispositivos.add(new Dispositivo("TV", 5.0, true));
		dispositivos.add(new Dispositivo("Heladera", 40.0, false));
		dispositivos.add(new Dispositivo("Parlante", 2.0, false));
		// Listas de dispositivos
		listasDeDispositivos = new ArrayList<List<Dispositivo>>();
		listasDeDispositivos.add(new ArrayList<Dispositivo>());
		listasDeDispositivos.add(Arrays.asList(dispositivos.get(0)));
		listasDeDispositivos.add(Arrays.asList(dispositivos.get(1), dispositivos.get(2)));
		listasDeDispositivos.add(Arrays.asList(dispositivos.get(3)));

		// Clientes
		Categoria r1 = new CategoriaResidencial(60.71, 0.681, 325, 400);
		clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente( "Martin Perez" ,
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 2251", "01149212334", r1,
				listasDeDispositivos.get(0)));
		clientes.add(new Cliente("Juan Lopez" ,
				new Documento(40732178, TipoDocumento.DNI), "Santa Fe 1781", "01141131234", r1,
				new ArrayList<Dispositivo>()));
		clientes.add(new Cliente( "Pepe Mitre" ,
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 241", "01149231234", r1,
				listasDeDispositivos.get(1)));
		clientes.add(new Cliente( "Jose Hernandez" ,
				new Documento(47465078, TipoDocumento.DNI), "Belgrano 2252", "01149212334", r1,
				listasDeDispositivos.get(2)));
		// Listas de Clientes
		listasDeClientes = new ArrayList<List<Cliente>>();
		listasDeClientes.add(new ArrayList<Cliente>());
		listasDeClientes.add(Arrays.asList(clientes.get(0)));
		listasDeClientes.add(Arrays.asList(clientes.get(1), clientes.get(0)));
		listasDeClientes.add(Arrays.asList(clientes.get(3)));

		// Cambiamos el servicio de almacenamiento
		ServiceLocator.getInstance().setAlmacenamiento(mockWrapper);
	}

}
