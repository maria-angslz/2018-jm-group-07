package sge.persistencia.json;

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

	public void cargarAdmins() {
		try {
			List<Administrador> admins = JSONWrapper.cargarConClase(archivoAdmins, Administrador[].class);
			RepoAdmins.getInstance().addAll(admins);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public void guardarAdmins() {
		try {
			JSONWrapper.guardar(archivoAdmins, RepoAdmins.getInstance().get());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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

	public void cargarClientes() {
		try {
			List<Cliente> clientes = JSONWrapper.cargarConClase(archivoClientes, Cliente[].class);
			RepoClientes.getInstance().addAll(clientes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public void guardarClientes() {
		try {
			JSONWrapper.guardar(archivoClientes, RepoClientes.getInstance().get());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void cargarCategorias() {
		try {
			List<CategoriaResidencial> categorias = JSONWrapper.cargarConClase(archivoCategoriasResidenciales,CategoriaResidencial[].class);
			RepoCatResidenciales.getInstance().addAll(categorias);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void guardarCategorias() {
		try {
			JSONWrapper.guardar(archivoCategoriasResidenciales, RepoCatResidenciales.getInstance().get());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
