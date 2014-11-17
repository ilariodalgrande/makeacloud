package com.makeacoffee.cloud;

import static com.makeacoffee.cloud.dao.OfyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

public class ObjectifySaveServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ofy();
		
		// Oggetto di prova per testare gli embedded
		A a = new A("a1");
		
		// Salvataggio oggetti su DS
		ofy().save().entity(a).now();
		
		// Lettura degli oggetti di prova
//		MakeACoffeUser loadedUser = ofy().load().type(MakeACoffeUser.class).id(macchi.getId()).now();
		
		// Output risultati
		resp.setContentType("text/plain");
		PrintWriter pw = resp.getWriter();
		pw.println("Hello, GAE Objectify");
		pw.println("a [" + a.getId() + "] = " + a);
	}

	@Entity
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@ToString
	public static class A {
		@Id
		@Getter private String id;
		@Load
		private Map<String, B> bs = new Hashtable<String, B>();
		
		public A(String id) {
			this.id = id;
			bs.put("b1", new B());
		}
	}
	
	@Getter @Setter
	public static class B {
		private String id;
		private Map<String, C> cs = new Hashtable<String, C>();
		
		public B() {
			id = "b1";
			cs.put("c1", new C());
		}
	}
	
	@Getter @Setter
	public static class C {
		private String id;
		private String message;
		
		public C() {
			id = "c1";
			message = "messaggio della classe C";
		}
	}
}
