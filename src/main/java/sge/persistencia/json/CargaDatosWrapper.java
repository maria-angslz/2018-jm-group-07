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
		List<Administrador> admins = JSONWrapper.cargarConClase(archivoAdmins, Administrador[].class);
		RepoAdmins.getInstance().addAll(admins);
	}

	public void guardarAdmins() throws IOException {
		JSONWrapper.guardar(archivoAdmins, RepoAdmins.getInstance().get());
	}

	public void cargarZona() {
		try {
			List<ZonaGeografica> zona = JSONWrapper.cargarConClase(archivoTransformadores, ZonaGeografica[].class);
			RepoZonas.getInstance().addAll(zona);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void guardarZona() {
		try {
			JSONWrapper.guardar(archivoTransformadores, RepoZonas.getInstance().get());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void cargarClientes() throws FileNotFoundException {
		List<Cliente> clientes = JSONWrapper.cargarConClase(archivoClientes, Cliente[].class);
		RepoClientes.getInstance().addAll(clientes);
	}

	public void guardarClientes() throws IOException {
		JSONWrapper.guardar(archivoClientes, RepoClientes.getInstance().get());
	}

	public void cargarCategorias() throws FileNotFoundException {
		List<CategoriaResidencial> categorias = JSONWrapper.cargarConClase(archivoCategoriasResidenciales,CategoriaResidencial[].class);
		RepoCatResidenciales.getInstance().addAll(categorias);
	}

	public void guardarCategorias() throws IOException {
		JSONWrapper.guardar(archivoCategoriasResidenciales, RepoCatResidenciales.getInstance().get());
	}

}
