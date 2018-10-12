package br.donizete.mobile.sccv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.donizete.mobile.sccv.config.ConnectionDB;
import br.donizete.mobile.sccv.entities.CurriculumVitae;
import br.donizete.mobile.sccv.entities.Usuario;
import br.donizete.mobile.sccv.subentities.Experiencia;
import br.donizete.mobile.sccv.subentities.Formacao;

public class CurriculoDao {

	private Connection conn;

	public CurriculoDao() throws SQLException {
		conn = ConnectionDB.getConnection();
	}

	public boolean deletarFormacao(Formacao form) throws SQLException {
		String sql = "DELETE FROM formacao WHERE id = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, form.getId());

		return ps.executeUpdate() > 0;
	}

	public List<Formacao> listarFormacoes(CurriculumVitae curriculumVitae) throws SQLException {
		String sql = "SELECT * FROM formacao WHERE id_curriculum_vitae = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, curriculumVitae.getId());

		ResultSet rs = ps.executeQuery();

		List<Formacao> formacoes = new ArrayList<>();

		while (rs.next()) {

			Formacao f = new Formacao();

			f.setId(rs.getInt("id"));
			f.setNome(rs.getString("nome"));
			f.getData_inicio().setTimeInMillis(rs.getLong("data_inicio"));
			f.getData_fim().setTimeInMillis(rs.getLong("data_fim"));
			f.setEscola(rs.getString("escola"));
			f.setId_curriculum_vitae(rs.getInt("id_curriculum_vitae"));

			formacoes.add(f);

		}

		return formacoes;
	}

	public boolean editarFormacao(Formacao formacao) throws SQLException {
		String sql = "UPDATE formacao SET nome = ?, data_inicio = ?, data_fim = ?, escola = ? WHERE id = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, formacao.getNome());
		ps.setLong(2, formacao.getData_inicio().getTimeInMillis());
		ps.setLong(3, formacao.getData_fim().getTimeInMillis());
		ps.setString(4, formacao.getEscola());
		ps.setInt(5, formacao.getId());

		return ps.executeUpdate() > 0;
	}

	public boolean inserirFormacao(Formacao formacao, CurriculumVitae curriculumVitae) throws SQLException {
		String sql = "INSERT INTO formacao(nome, data_inicio, data_fim, escola, id_curriculum_vitae) VALUES (?, ?, ?, ?, ?);";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, formacao.getNome());
		ps.setLong(2, formacao.getData_inicio().getTimeInMillis());
		ps.setLong(3, formacao.getData_fim().getTimeInMillis());
		ps.setString(4, formacao.getEscola());
		ps.setInt(5, curriculumVitae.getId());

		return ps.executeUpdate() > 0;
	}

	public boolean deletarExperiencia(Experiencia exp) throws SQLException {
		String sql = "DELETE FROM experiencia WHERE id = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, exp.getId());

		return ps.executeUpdate() > 0;
	}

	public List<Experiencia> listarExperiencias(CurriculumVitae curriculumVitae) throws SQLException {
		String sql = "SELECT * FROM experiencia WHERE id_curriculum_vitae = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, curriculumVitae.getId());

		ResultSet rs = ps.executeQuery();

		List<Experiencia> experiencias = new ArrayList<>();

		while (rs.next()) {

			Experiencia exp = new Experiencia();
			exp.setId(rs.getInt("id"));
			exp.setNome(rs.getString("nome"));
			exp.getData_inicio().setTimeInMillis(rs.getLong("data_inicio"));
			exp.getData_fim().setTimeInMillis(rs.getLong("data_fim"));
			exp.setCargo(rs.getString("cargo"));
			exp.setEmpresa(rs.getString("empresa"));
			exp.setFuncoes(rs.getString("funcoes"));
			exp.setId_curriculum_vitae(rs.getInt("id_curriculum_vitae"));

			experiencias.add(exp);
		}

		return experiencias;
	}

	public boolean inserirExperiencia(Experiencia experiencia, CurriculumVitae curriculumVitae) throws SQLException {
		String sql = "INSERT INTO experiencia(nome, data_inicio, data_fim, cargo, empresa, funcoes, id_curriculum_vitae) VALUES (?, ?, ?, ?, ?, ?, ?);";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, experiencia.getNome());
		ps.setLong(2, experiencia.getData_inicio().getTimeInMillis());
		ps.setLong(3, experiencia.getData_fim().getTimeInMillis());
		ps.setString(4, experiencia.getCargo());
		ps.setString(5, experiencia.getEmpresa());
		ps.setString(6, experiencia.getFuncoes());
		ps.setInt(7, curriculumVitae.getId());

		return ps.executeUpdate() > 0;
	}

	public boolean editarExperiencia(Experiencia experiencia) throws SQLException {
		String sql = "UPDATE experiencia SET nome = ?, data_inicio = ?, data_fim = ?, cargo = ?, empresa = ?, funcoes = ? WHERE id = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, experiencia.getNome());
		ps.setLong(2, experiencia.getData_inicio().getTimeInMillis());
		ps.setLong(3, experiencia.getData_fim().getTimeInMillis());
		ps.setString(4, experiencia.getCargo());
		ps.setString(5, experiencia.getEmpresa());
		ps.setString(6, experiencia.getFuncoes());
		ps.setInt(7, experiencia.getId());

		return ps.executeUpdate() > 0;
	}

	public boolean criarCurriculo(CurriculumVitae c, Usuario u) throws SQLException {
		String sql = "INSERT INTO curriculum_vitae(data_criacao, id_curso, id_turma, semestre, id_usuario, id_categoria) VALUES (?, ?, ?, ?, ?, ?);";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, Calendar.getInstance().getTimeInMillis());
		ps.setInt(2, c.getCurso().getId());
		ps.setInt(3, c.getTurma().getId());
		ps.setInt(4, c.getSemestre());
		ps.setInt(5, u.getId());
		ps.setInt(6, c.getCategoria().getId());

		return ps.executeUpdate() > 0;
	}

	public List<CurriculumVitae> listarCurriculo(Integer id_usuario) throws SQLException {
		String sql = "SELECT c.*, cur.nome AS nomeCurso, tur.nome AS nomeTurma, sts.nome AS nomeStatus, cat.nome AS nomeCategoria FROM curriculum_vitae AS c "
				+ "INNER JOIN curso AS cur ON cur.id = c.id_curso " + "INNER JOIN turma AS tur ON tur.id = c.id_turma "
				+ "INNER JOIN status_ AS sts ON sts.id = c.id_status "
				+ "INNER JOIN categoria AS cat ON cat.id = c.id_categoria " + "WHERE c.id_usuario = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id_usuario);

		ResultSet rs = ps.executeQuery();

		List<CurriculumVitae> lista = new ArrayList<CurriculumVitae>();

		while (rs.next()) {
			CurriculumVitae c = new CurriculumVitae();

			c.setId(rs.getInt("id"));
			c.getData_criacao().setTimeInMillis(rs.getLong("data_criacao"));
			c.getCurso().setId(rs.getInt("id_curso"));
			c.getCurso().setNome(rs.getString("nomeCurso"));
			c.getTurma().setId(rs.getInt("id_turma"));
			c.getTurma().setNome(rs.getString("nomeTurma"));
			c.setSemestre(rs.getInt("semestre"));
			c.getUsuario().setId(rs.getInt("id_usuario"));
			c.getStatus().setId(rs.getInt("id_status"));
			c.getStatus().setNome(rs.getString("nomeStatus"));
			c.getCategoria().setId(rs.getInt("id_categoria"));
			c.getCategoria().setNome(rs.getString("nomeCategoria"));

			lista.add(c);
		}

		return lista;

	}

	public List<CurriculumVitae> listarCurriculo(Integer id_usuario, Integer id_curso) throws SQLException {
		String sql = "SELECT c.*, cur.nome AS nomeCurso, tur.nome AS nomeTurma, sts.nome AS nomeStatus, cat.nome AS nomeCategoria FROM curriculum_vitae AS c "
				+ "INNER JOIN curso AS cur ON cur.id = c.id_curso " + "INNER JOIN turma AS tur ON tur.id = c.id_turma "
				+ "INNER JOIN status_ AS sts ON sts.id = c.id_status "
				+ "INNER JOIN categoria AS cat ON cat.id = c.id_categoria "
				+ "WHERE c.id_usuario = ? AND c.id_curso = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id_usuario);
		ps.setInt(2, id_curso);
		ResultSet rs = ps.executeQuery();
		List<CurriculumVitae> lista = new ArrayList<CurriculumVitae>();

		while (rs.next()) {
			CurriculumVitae c = new CurriculumVitae();

			c.setId(rs.getInt("id"));
			c.getData_criacao().setTimeInMillis(rs.getLong("data_criacao"));
			c.getCurso().setId(rs.getInt("id_curso"));
			c.getCurso().setNome(rs.getString("nomeCurso"));
			c.getTurma().setId(rs.getInt("id_turma"));
			c.getTurma().setNome(rs.getString("nomeTurma"));
			c.setSemestre(rs.getInt("semestre"));
			c.getUsuario().setId(rs.getInt("id_usuario"));
			c.getStatus().setId(rs.getInt("id_status"));
			c.getStatus().setNome(rs.getString("nomeStatus"));
			c.getCategoria().setId(rs.getInt("id_categoria"));
			c.getCategoria().setNome(rs.getString("nomeCategoria"));

			lista.add(c);
		}

		return lista;

	}

	public boolean updateCurriculum(CurriculumVitae cv) throws SQLException {
		String sql = "UPDATE curriculum_vitae AS cv SET id_turma = ?, semestre = ? WHERE cv.id = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, cv.getTurma().getId());
		ps.setInt(2, cv.getSemestre());
		ps.setInt(3, cv.getId());

		return ps.executeUpdate() > 0;
	}

}
