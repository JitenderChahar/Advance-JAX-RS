package com.jsc.javageeks.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger.model.Message;

public class GenericTypeDemo {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client
				.target("http://localhost:8080/advance-jaxrs/webapi/")
				.path("messages").queryParam("year", 2015);

		List<Message> list = baseTarget.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Message>>() {
				});

		System.out.println(list);

	}

}
