package utn.dds.k3001.grupo3.tpa.server;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.DireccionHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;
import utn.dds.k3001.grupo3.tpa.controllers.LoginController;
import utn.dds.k3001.grupo3.tpa.controllers.POISController;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("mostrarDir", DireccionHelper.mostrarDireccion)
				.withHelper("getCalle", DireccionHelper.getCalle)
				.withHelper("getBarrio", DireccionHelper.getBarrio)
				.withHelper("getNumero", DireccionHelper.getNumero)
				.build();

		Spark.staticFiles.location("/public");
		LoginController loginC = new LoginController();
		POISController terminalC = new POISController();
		Spark.get("/",loginC::mostrarLogin,engine);
		Spark.get("/login", loginC::mostrarLogin,engine);
		Spark.post("/login", loginC::iniciarSesion,engine);
		Spark.get("/pois", terminalC::buscar,engine);
		Spark.post("/logout",loginC::logout);
		Spark.post("/pois/eliminar/:id", terminalC::eliminar);
		Spark.get("/pois/eliminar/:id",terminalC::getEliminar,engine);
		Spark.get("/pois/modificar/:id",terminalC::getModificar,engine);
		Spark.post("/pois/modificar/:id",terminalC::modificar,engine);
	}

}