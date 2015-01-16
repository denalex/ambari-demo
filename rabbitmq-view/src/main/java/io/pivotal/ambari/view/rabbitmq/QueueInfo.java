package io.pivotal.ambari.view.rabbitmq;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueInfo {

	private String name;
	private int totalMessages;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalMessages() {
		return totalMessages;
	}
	public void setTotalMessages(int totalMessages) {
		this.totalMessages = totalMessages;
	}
}
