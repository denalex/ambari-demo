package io.pivotal.ambari.view.rabbitmq;

import java.util.List;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;



public class RabbitMQService {

	private static final TypeReference<List<QueueInfo>> typeRef = new TypeReference<List<QueueInfo>>(){};

	public List<QueueInfo> getQueues(String host, int port) throws Exception {
		
		String rawResponse = getResponse(host, port, "/api/queues");
		ObjectMapper mapper = new ObjectMapper();
		
		List<QueueInfo> result = mapper.readValue(rawResponse, typeRef); // new ArrayList<QueueInfo>();
		System.out.println("Parsed response : " + result);
		/*
		QueueInfo queue1 = new QueueInfo();
		queue1.setName("queue-1");
		queue1.setTotalMessages(15);
		result.add(queue1);
		*/
		return result;
	}
	
	private String getResponse(String host, int port, String url) throws Exception {
		String result = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
        	        	
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(host, port),
                    new UsernamePasswordCredentials("guest", "guest"));

            System.out.println("Before calling rabbit");
            HttpGet httpget = new HttpGet(String.format("http://%s:%d%s", host, port, url));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            result = httpclient.execute(httpget, responseHandler);            
            System.out.println("Received rabbit response : " + result);
            
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return result;
	}
}
