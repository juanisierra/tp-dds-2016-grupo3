package utn.dds.k3001.grupo3.tpa.server;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.AccionesBusquedaHelper;
import spark.utils.AccionesQueryParamHelper;
import spark.utils.DireccionHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;
import utn.dds.k3001.grupo3.tpa.controllers.BusquedasController;
import spark.utils.PointHelper;
import spark.utils.ServiciosHelper;
import utn.dds.k3001.grupo3.tpa.controllers.LoginController;
import utn.dds.k3001.grupo3.tpa.controllers.POISController;
import utn.dds.k3001.grupo3.tpa.controllers.TerminalesController;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("mostrarDir", DireccionHelper.mostrarDireccion)
				.withHelper("getCalle", DireccionHelper.getCalle)
				.withHelper("getBarrio", DireccionHelper.getBarrio)
				.withHelper("getNumero", DireccionHelper.getNumero)
				.withHelper("mostrarCoord", PointHelper.mostrarCoord)
				.withHelper("mostrarServicio", ServiciosHelper.mostrarServicio)
				.withHelper("mostrarAccion", AccionesBusquedaHelper.mostrarAccion)
				.withHelper("parametrizarAccion", AccionesQueryParamHelper.parametrizarAccion)
				.build();
		
		Spark.staticFiles.location("/public");
		LoginController loginController = new LoginController();
		POISController poisController = new POISController();
		BusquedasController busquedasController = new BusquedasController();
		TerminalesController terminalesController = new TerminalesController();
		Spark.get("/",loginController::mostrarLogin,engine);
		Spark.get("/login", loginController::mostrarLogin,engine);
		Spark.post("/login", loginController::iniciarSesion,engine);
		Spark.get("/pois", poisController::buscar,engine);
		Spark.get("/busquedas", busquedasController::buscar, engine);
		Spark.get("/busquedas/:id/pois", busquedasController::mostrarPois, engine);
		Spark.post("/logout",loginController::logout);
		Spark.post("/pois/eliminar/:id", poisController::eliminar);
		Spark.get("/pois/:id", poisController::verPOI,engine);
		Spark.get("/pois/eliminar/:id",poisController::getEliminar,engine);
		Spark.get("/pois/modificar/:id",poisController::getModificar,engine);
		Spark.post("/pois/modificar/:id",poisController::modificar,engine);
		Spark.get("/terminales", terminalesController::listar,engine);
		Spark.get("/terminales/eliminar/:id",terminalesController::getEliminar,engine);
		Spark.post("/terminales/eliminar/:id", terminalesController::eliminar);
		Spark.get("/terminales/modificar/:id",terminalesController::getModificar,engine);
		Spark.post("/terminales/modificar/:id",terminalesController::modificar,engine);		
		Spark.get("/terminales/agregar",terminalesController::getAgregar,engine);
		Spark.post("/terminales/agregar",terminalesController::agregar,engine);
		Spark.get("/terminales/acciones/:id",terminalesController::getAcciones,engine);
		Spark.post("/terminales/acciones/:id",terminalesController::actualizarAcciones,engine);
		Spark.after(Filters.BorrarCache);
		Spark.before(Filters.Login);
		Spark.before("/busquedas", Filters.Admin);
		Spark.before("/busquedas/verPois/:id", Filters.Admin);
		Spark.before("/pois/eliminar/:id", Filters.Admin);
		Spark.before("/pois/modificar/:id", Filters.Admin);
		Spark.before("/terminales", Filters.Admin);
		Spark.before("/terminales/eliminar/:id", Filters.Admin);
		Spark.before("/terminales/modificar/:id", Filters.Admin);
		Spark.before("/terminales/agregar", Filters.Admin);
		Spark.before("/terminales/acciones/:id", Filters.Admin);
	}

}