package utn.dds.k3001.grupo3.tpa.server;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import spark.Filter;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.usuarios.Usuario;

public enum Filters implements Filter, WithGlobalEntityManager,TransactionalOps {
	Login {

		@Override
		public void handle(Request request, Response response) throws Exception {
			if(request.session().attribute("user")==null && !request.pathInfo().equals("/") && !request.pathInfo().equals("/login")) response.redirect("/login",403);
			
		}
		
	},
	Admin {
		@Override
		public void handle(Request request, Response response) throws Exception {
			if(!((Usuario) request.session().attribute("user")).esAdmin())response.redirect("/login",403);
		}
		},
	BorrarCache {
		@Override
		public void handle(Request request, Response response) throws Exception {
			if(!request.pathInfo().equals("/") && !request.pathInfo().equals("/login"))
			{	
				entityManager().clear();
			}
		}
	}
	
}
