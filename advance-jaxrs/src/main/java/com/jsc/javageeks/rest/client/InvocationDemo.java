package com.jsc.javageeks.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvocationDemo {

	public static void main(String[] args) {
		InvocationDemo demo = new InvocationDemo();
		Invocation invocation = demo.prepareRequestForMessageByYear(2015);
		Response response = invocation.invoke();
		System.out.println(response.getStatus());
	}

	public Invocation prepareRequestForMessageByYear(int year) {
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client
				.target("http://localhost:8080/advance-jaxrs/webapi/")
				.path("messages").queryParam("year", year);

		return baseTarget.request(MediaType.APPLICATION_JSON).buildGet();
	}
}
