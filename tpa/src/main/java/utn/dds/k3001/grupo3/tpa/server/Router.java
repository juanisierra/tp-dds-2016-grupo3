package utn.dds.k3001.grupo3.tpa.server;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.DireccionHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;
import utn.dds.k3001.grupo3.tpa.controllers.LoginController;
import utn.dds.k3001.grupo3.tpa.controllers.TerminalController;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("mostrarDir", DireccionHelper.mostrarDireccion)
				.build();

		Spark.staticFiles.location("/public");
		LoginController loginC = new LoginController();
		TerminalController terminalC = new TerminalController();
		Spark.get("/login", loginC::mostrarLogin,engine);
		Spark.post("/login", loginC::iniciarSesion,engine);
		Spark.get("/terminal/buscar", terminalC::buscar,engine);
		Spark.post("/logout",loginC::logout);
	}

}