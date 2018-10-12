package br.donizete.mobile.sccv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.donizete.mobile.sccv.config.ConnectionDB;
import br.donizete.mobile.sccv.subentities.ClasseGenerica;

public class ClasseGenericaDao {

	private Connection conn;

	public ClasseGenericaDao() throws SQLException {
		conn = ConnectionDB.getConnection();
	}

	public List<ClasseGenerica> buscaSexo() throws SQLException {
		String sql = "SELECT * FROM sexo;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> sexos = new ArrayList<ClasseGenerica>();

		while (rs.next()) {
			sexos.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return sexos;
	}

	public List<ClasseGenerica> buscaCategoria() throws SQLException {
		String sql = "SELECT * FROM categoria;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> cursos = new ArrayList<ClasseGenerica>();

		while (rs.next()) {
			cursos.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return cursos;
	}

	public List<ClasseGenerica> buscaCurso(Integer id_categoria) throws SQLException {
		String sql = "SELECT * FROM curso WHERE id_categoria = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id_categoria);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> cursos = new ArrayList<ClasseGenerica>();

		while (rs.next()) {
			cursos.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return cursos;
	}

	public List<ClasseGenerica> buscaTurma(Integer id) throws SQLException {
		String sql = "SELECT t.id, t.nome FROM turma AS t WHERE id_curso = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> turmas = new ArrayList<ClasseGenerica>();
		while (rs.next()) {
			turmas.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return turmas;
	}

	public List<ClasseGenerica> buscaEstado() throws SQLException {
		String sql = "SELECT e.id, e.nome FROM estado AS e;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> estados = new ArrayList<ClasseGenerica>();
		while (rs.next()) {
			estados.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return estados;
	}

	public List<ClasseGenerica> buscaCidade(Integer id) throws SQLException {
		String sql = "SELECT c.id, c.nome FROM cidade AS c WHERE c.estado = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> cidades = new ArrayList<ClasseGenerica>();
		while (rs.next()) {
			cidades.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return cidades;
	}

}
