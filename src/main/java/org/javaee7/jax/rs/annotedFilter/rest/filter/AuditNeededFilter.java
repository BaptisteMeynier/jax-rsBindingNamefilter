package org.javaee7.jax.rs.annotedFilter.rest.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;



@Provider
@AuditNeeded
@Priority(Priorities.AUTHENTICATION)
public class AuditNeededFilter implements ContainerRequestFilter {

	@Inject
	private Logger logger;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("#### Audit : " + requestContext.getRequest().getMethod() + " " + requestContext.getUriInfo().getPath());
	}

}
