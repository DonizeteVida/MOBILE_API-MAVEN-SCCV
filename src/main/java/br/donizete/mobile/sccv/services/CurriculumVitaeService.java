package br.donizete.mobile.sccv.services;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.donizete.mobile.sccv.dao.CurriculoDao;
import br.donizete.mobile.sccv.entities.CurriculumVitae;
import br.donizete.mobile.sccv.subentities.Experiencia;
import br.donizete.mobile.sccv.subentities.Formacao;

@Path("/ws/curriculumvitae")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CurriculumVitaeService {

	CurriculoDao curriculoDao;

	public CurriculumVitaeService() {
		try {
			curriculoDao = new CurriculoDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GET
	@Path("/{idUser}/busca")
	public List<CurriculumVitae> lista(@PathParam("idUser") Integer idUser) {
		try {
			return curriculoDao.listarCurriculo(idUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@GET
	@Path("/{idCurriculo}/experiencia/busca")
	public List<Experiencia> listaExperiencia(@PathParam("idCurriculo") Integer idCurriculo) {

		CurriculumVitae c = new CurriculumVitae();

		c.setId(idCurriculo);

		try {
			return curriculoDao.listarExperiencias(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/{idCurriculo}/formacao/busca")
	public List<Formacao> listaFormacao(@PathParam("idCurriculo") Integer idCurriculo) {
		CurriculumVitae c = new CurriculumVitae();

		c.setId(idCurriculo);

		try {
			return curriculoDao.listarFormacoes(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
