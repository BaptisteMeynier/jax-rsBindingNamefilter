package org.javaee7.jax.rs.annotedFilter.rest;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.javaee7.jax.rs.annotedFilter.domain.Book;
import org.javaee7.jax.rs.annotedFilter.rest.filter.AuditNeeded;


@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookEndpoint {

	@Inject
	private Logger log;

	private List<Book> library = 
			Stream.of(
					new Book(1l,"La Métamorphose"),
					new Book(2l,"La Colonie pénitentiaire"),
					new Book(3l,"Le Procès"),
					new Book(4l,"Le Château"),
					new Book(5l,"L'Amérique")
					).collect(Collectors.toList()); 


	@GET
	public Response findAllBooks() {
		log.info("findAllBooks");
		if (this.library == null)
			return Response.status(NOT_FOUND).build();

		return Response.ok(this.library).build();
	}

	@GET
	@Path("/{id}")
	@AuditNeeded
	public Response findBookById(@PathParam("id") final long id) {
		log.info("findBookById");
		Optional<Book> book = this.library.stream().filter(aBook->{return aBook.getId()==id;}).findFirst();

		if (!book.isPresent())
			return Response.status(NOT_FOUND).build();

		return Response.ok(book.get()).build();
	}

}
