package br.donizete.mobile.sccv.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.donizete.mobile.sccv.services.CurriculumVitaeService;
import br.donizete.mobile.sccv.services.UsuarioService;

public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> recursos = new HashSet<>();
		recursos.add(UsuarioService.class);
		recursos.add(CurriculumVitaeService.class);
		return recursos;
	}
}
