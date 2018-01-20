package org.javaee7.jax.rs.annotedFilter.rest;




import org.javaee7.jax.rs.annotedFilter.domain.Book;
import org.javaee7.jax.rs.annotedFilter.rest.filter.AuditNeeded;
import org.javaee7.jax.rs.annotedFilter.rest.filter.AuditNeededFilter;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import producer.LoggerProducer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URI;
import static org.junit.Assert.assertEquals;


@RunWith(Arquillian.class)
@RunAsClient
public class BookEndpointTest {

    // ======================================
    // =             Attributes             =
    // ======================================
    private Client client;
    private WebTarget bookTarget;

    // ======================================
    // =          Injection Points          =
    // ======================================

    @ArquillianResource
    private URI baseURL;

    // ======================================
    // =         Deployment methods         =
    // ======================================

    @Deployment(testable = false)
    public static WebArchive createDeployment() {

        return ShrinkWrap.create(WebArchive.class)
                .addClasses(BookApplication.class,BookEndpoint.class, Book.class)
                .addClasses(AuditNeeded.class,AuditNeededFilter.class)
                .addClasses(LoggerProducer.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @Before
    public void initWebTarget() {
        client = ClientBuilder.newClient();
        bookTarget = client.target(baseURL).path("api").path("books");
    }

    // ======================================
    // =            Test methods            =
    // ======================================

    @Test
    public void shouldFindAll() throws Exception {
        Response response = bookTarget
        		.request(MediaType.APPLICATION_JSON_TYPE)
        		.get();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldFindBookById() throws Exception {
    	
        Response response = bookTarget
        		.path("{id}")
        		.resolveTemplate("id", 1l)
        		.request(MediaType.APPLICATION_JSON_TYPE)
        		.get();

        assertEquals(200, response.getStatus());
    }

}