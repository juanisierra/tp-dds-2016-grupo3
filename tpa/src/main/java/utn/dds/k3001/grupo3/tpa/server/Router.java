package utn.dds.k3001.grupo3.tpa.server;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.DireccionHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;
import utn.dds.k3001.grupo3.tpa.busquedas.AccionesBusqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
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
				.build();
		
		Terminal terminal = new Terminal("miTerminal", Mapa.getInstance());   //para que haya datos que mostrar, dsp se borra
		terminal.agregarObserverBusqueda(AccionesBusqueda.GUARDARBUSQUEDA);
		terminal.buscar("parada");
		terminal.buscar("114");
		
		Spark.staticFiles.location("/public");
		LoginController loginC = new LoginController();
		POISController terminalC = new POISController();
		BusquedasController busquedaC = new BusquedasController();
		TerminalesController terminales = new TerminalesController();
		
		Spark.get("/",loginC::mostrarLogin,engine);
		Spark.get("/login", loginC::mostrarLogin,engine);
		Spark.post("/login", loginC::iniciarSesion,engine);
		Spark.get("/pois", terminalC::buscar,engine);
		Spark.get("/busquedas", busquedaC::buscar, engine);
		//Spark.post("/busquedas/verPois", busquedaC::mostrarPois, engine);
		Spark.post("/logout",loginC::logout);
		Spark.post("/pois/eliminar/:id", terminalC::eliminar);
		Spark.get("/pois/eliminar/:id",terminalC::getEliminar,engine);
		Spark.get("/pois/modificar/:id",terminalC::getModificar,engine);
		Spark.post("/pois/modificar/:id",terminalC::modificar,engine);
		Spark.get("/terminales", terminales::listar,engine);
		Spark.get("/terminales/eliminar/:id",terminales::getEliminar,engine);
		Spark.post("/terminales/eliminar/:id", terminales::eliminar);
		Spark.get("/pois/:id", terminalC::verPOI,engine);
	}

}