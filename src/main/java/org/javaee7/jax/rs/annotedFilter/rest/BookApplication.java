package org.javaee7.jax.rs.annotedFilter.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.javaee7.jax.rs.annotedFilter.rest.filter.AuditNeededFilter;



@ApplicationPath("/api")
public class BookApplication extends Application{
	@Override
	public Set<Class<?>> getClasses() {
	    Set<Class<?>> s = new HashSet<Class<?>>();
	    s.add(BookEndpoint.class);
	    s.add(AuditNeededFilter.class);
	    return s;
	}
	
	@Override
    public Set<Object> getSingletons() {
	    Set<Object> s = new HashSet<Object>();
	   // s.add(AuditNeededFilter.class);
	    return s;
    }

}
