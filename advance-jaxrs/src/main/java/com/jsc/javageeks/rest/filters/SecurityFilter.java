package com.jsc.javageeks.rest.filters;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURE_API = "secured";

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		if (requestContext.getUriInfo().getPath().contains(SECURE_API)) {
			List<String> authHeader = requestContext.getHeaders().get(
					AUTHORIZATION_HEADER_KEY);
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX,
						"");
				String decodedToken = Base64.decodeAsString(authToken);
				StringTokenizer tokenizer = new StringTokenizer(decodedToken,
						":");
				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				if ("user".equals(username) && "password".equals(password)) {
					return;
				}
			}

			Response response = Response.status(Response.Status.UNAUTHORIZED)
					.entity("User is not authorize").build();

			requestContext.abortWith(response);
		}
	}

}
