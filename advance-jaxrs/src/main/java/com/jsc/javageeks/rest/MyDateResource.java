package com.jsc.javageeks.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jsc.javageeks.domain.MyDate;

@Path("date/{dateValue}")
public class MyDateResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getRequestedDate(@PathParam("dateValue") MyDate myDate){
		return "Date " + myDate.toString();
	}
}
