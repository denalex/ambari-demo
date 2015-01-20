package io.pivotal.ambari.view.rabbitmq;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.ambari.view.ViewContext;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * The RabbitMQ Server resource.
 */
public class RabbitMQServerResource {

	@Inject	ViewContext context;
		
	@GET
	@Path("/queues")
	@Produces({ "text/plain" })
	public Response listQueues(@Context HttpHeaders headers, @Context UriInfo ui) throws IOException {
		
		String response;
		try {
			response = getResponse("c6404", 15672, "/api/queues");
		} catch (Exception e) {
			response = "ERROR while getting queue list : " + e.getMessage();
		}

		return Response.ok(response).type("text/plain").build();
	}
	
	private String getResponse(String host, int port, String url) throws Exception {

		String result = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
        	        	
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(host, port),
                    new UsernamePasswordCredentials("guest", "guest"));

            HttpGet httpget = new HttpGet(String.format("http://%s:%d%s", host, port, url));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            result = httpclient.execute(httpget, responseHandler);            
            
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return result;
	}

}
