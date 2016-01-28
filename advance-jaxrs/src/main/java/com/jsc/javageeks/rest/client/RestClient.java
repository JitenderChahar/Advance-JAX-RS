package com.jsc.javageeks.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.koushik.javabrains.messenger.model.Message;

public class RestClient {

	public static void main(String[] args) {
		/*
		 * Client client = ClientBuilder.newClient(); Message message = client
		 * .target("http://localhost:8080/advance-jaxrs/webapi/messages/1")
		 * .request(MediaType.APPLICATION_JSON) .get(Message.class); //Message
		 * message = response.readEntity(Message.class);
		 * System.out.println(message.getMessage());
		 */

		// best practices
		//for GET Method
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client
				.target("http://localhost:8080/advance-jaxrs/webapi/");
		WebTarget messageTarget = baseTarget.path("messages/");
		WebTarget singleMessageTarget = messageTarget.path("{messageId}");

		Message message1 = singleMessageTarget.
				resolveTemplate("messageId", "1")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		
		Message message2 = singleMessageTarget.
				resolveTemplate("messageId", "2")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		
		System.out.println(message1.getMessage());
		System.out.println(message2.getMessage());
		
		//post Method request
		Message newMessage = new Message(3, "newly created message in jax-rs", "jitender");
		
		Response postResponse = messageTarget.request().post(
				Entity.json(newMessage));
		if(postResponse.getStatus() == 201){
			Message createdMessage = postResponse.readEntity(Message.class);
			System.out.println(createdMessage.getMessage());
		}

	}

}
