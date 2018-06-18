package sge.persistencia.json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import sge.Administrador;
import sge.Suministro.ZonaGeografica;
import sge.categorias.CategoriaResidencial;
import sge.clientes.Cliente;
import sge.persistencia.AlmacenamientoPersistente;
import sge.persistencia.repos.RepoAdmins;
import sge.persistencia.repos.RepoCatResidenciales;
import sge.persistencia.repos.RepoClientes;
import sge.persistencia.repos.RepoZonas;
import sge.persistencia.json.JSONWrapper;

public class CargaDatosWrapper implements AlmacenamientoPersistente {
	
	private String archivoAdmins = ".\\src\\main\\resources\\Administradores.json";
	private String archivoClientes = ".\\src\\main\\resources\\Clientes.json";
	private String archivoCategoriasResidenciales = ".\\src\\main\\resources\\CategoriasResidenciales.json";
	private String archivoTransformadores = ".\\src\\\\main\\\\resources\\\\Transformador.json";
	
	public void cargarAdmins() throws FileNotFoundException {
		List<Administrador> admins = JSONWrapper.cargar(archivoAdmins);
		RepoAdmins.getInstance().addAll(admins);
	}

	public void guardarAdmins() throws IOException {
		JSONWrapper.guardar(archivoAdmins, RepoAdmins.getInstance().get());
	}
	public void cargarZona() throws FileNotFoundException{
		List<ZonaGeografica> zona = JSONWrapper.cargar(archivoTransformadores);
		RepoZonas.getInstance().addAll(zona);
		
	}
	
	public void guardarZona() throws FileNotFoundException{
		JSONWrapper.guardar(archivoTransformadores, RepoZonas.getInstance().get());
	}
	
	public void cargarClientes() throws FileNotFoundException {
		List<Cliente> clientes = JSONWrapper.cargar(archivoClientes);
		RepoClientes.getInstance().addAll(clientes);
	}
	

	public void guardarClientes() throws IOException {
		JSONWrapper.guardar(archivoClientes, RepoClientes.getInstance().get());
	}

	public void cargarCategorias() throws FileNotFoundException {
		List<CategoriaResidencial> categorias = JSONWrapper.cargar(archivoCategoriasResidenciales);
		RepoCatResidenciales.getInstance().addAll(categorias);
	}

	public void guardarCategorias() throws IOException {
		JSONWrapper.guardar(archivoCategoriasResidenciales, RepoCatResidenciales.getInstance().get());
	}

}
