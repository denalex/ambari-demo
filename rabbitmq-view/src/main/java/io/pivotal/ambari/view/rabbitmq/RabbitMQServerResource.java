package io.pivotal.ambari.view.rabbitmq;

import org.apache.ambari.view.ViewContext;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.io.IOException;
import java.util.List;

/**
 * The RabbitMQ Server resource.
 */
public class RabbitMQServerResource {

	/**
	 * The view context.
	 */
	@Inject
	ViewContext context;

	private RabbitMQService service = new RabbitMQService();

	/**
	 * Handles: GET /calculator Get the calculator usage.
	 *
	 * @param headers
	 *            http headers
	 * @param ui
	 *            uri info
	 *
	 * @return the response including the usage of the calculator resource
	 */
	@GET
	@Path("/queues")
	@Produces({ "text/html" })
	public Response listQueues(@Context HttpHeaders headers, @Context UriInfo ui) throws IOException {
		String response;
		try {
			List<QueueInfo> queues = service.getQueues("c6404", 15672);
			response = buildResponse(queues);
		} catch (Exception e) {
			response = "ERROR while getting queue list : " + e.getMessage();
		}

		return Response.ok(response).type("text/html").build();
	}

	private String buildResponse(List<QueueInfo> queues) {
		StringBuilder response = new StringBuilder("<h2>List of Queues:</h2><ul>");
		for (QueueInfo queue : queues) {
			response.append(String.format("<li><b>%s</b> - %d total messages.</li>", queue.getName(), queue.getTotalMessages()));
		}
		response.append("</ul>");
		String result = response.toString();
		System.out.println("Prepared response : " + result);
		return result;
	}

}
