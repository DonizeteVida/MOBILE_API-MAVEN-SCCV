package br.donizete.mobile.sccv.services;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.donizete.mobile.sccv.dao.UsuarioDao;
import br.donizete.mobile.sccv.entities.Usuario;

@Path("/ws/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioService {

	UsuarioDao usuarioDao;

	public UsuarioService() {
		try {
			usuarioDao = new UsuarioDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GET
	@Path("/{cpf}/busca")
	public Usuario busca(@PathParam("cpf") String cpf) {
		try {
			return usuarioDao.buscaUsuarioPorCpf(cpf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
