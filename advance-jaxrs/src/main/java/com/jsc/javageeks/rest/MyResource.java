package com.jsc.javageeks.rest;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
//@Singleton
// @Singleton to make resource scope singleton i.e one instance per server
public class MyResource {
	private int count;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		count = count + 1;
		return "Works fine! this method is called " + count + " time(s)";
	}
	
	@GET
	@Path("date")
	@Produces(value = { MediaType.TEXT_PLAIN, "text/shortdate" })
	public Date getCurrentDate(){
		return Calendar.getInstance().getTime();
	}
}
