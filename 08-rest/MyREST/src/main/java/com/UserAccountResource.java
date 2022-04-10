package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.JAXBElement;

@Path("/userAccount")
public class UserAccountResource {
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello dear customer. You are an anonymous one. To get list of all user accounts, choose different media type. You are now viewing plain text.";
	}
	
	@Context
	UriInfo uriInfo;

	private static Map<Integer, UserAccount> accounts = new HashMap<Integer, UserAccount>();
    
	public UserAccountResource() {
	    accounts.put(1, new UserAccount(1, "Jane Doe", "januD", "myStrongPassword258", "jane.doe@yahoo.com", 0.0));
	    accounts.put(2, new UserAccount(1, "Johnny Blackwood", "blackWoody", "cantGuessThat123", "johnBlackwood@yahoo.com", 0.0));
	    accounts.put(3, new UserAccount(3, "Amelia Strike", "meliStrike", "nopsswd", "amelia.strike@gmail.com", 0.0));
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<UserAccount> getUserAccounts() {
	    List<UserAccount> returnedUserAccounts = new ArrayList<UserAccount>();
	    returnedUserAccounts.addAll( accounts.values() );
	    return returnedUserAccounts;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{userAccountId}")
	public UserAccount getUserAccount(
	         @PathParam("userAccountId") String userAccountId) {
		int id;
		System.out.println(userAccountId);
		try {
			id = Integer.parseInt(userAccountId);
		} catch(Exception e) {
			id = 0;
		}
		System.out.println("id: " + id);
		if(accounts.get(id) != null) {
			System.out.println("userAccountId name: " + accounts.get(id).getFullname());
		}
		
		return accounts.get(id);
	}
	
	
	
	private boolean updateUserAccount(String strid, UserAccount c) {
		int id = Integer.parseInt(strid);
			accounts.get(id).setEmail(c.getEmail());
			accounts.get(id).setFullname(c.getFullname());
			accounts.get(id).setPasswd(c.getPasswd());
			return true;
		
	}
	
	private boolean postUserAccount(String strid, UserAccount c) {
		int id = Integer.parseInt(strid);
		if(accounts.get(id) != null) {
			return false;
		} else {
			c.setId(id);
			accounts.put(id, c);

			return true;
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{userAccount}")
	public Response putUserAccount(
	            @PathParam("userAccount") String userAccountID, JAXBElement<UserAccount> userAccount) {
		System.out.println(userAccount);
		UserAccount c = userAccount.getValue();
		System.out.println(c.getFullname());
		Response res;
		String mes;
		if(c == null) {
			res = Response.status(Response.Status.BAD_REQUEST).entity(c).build();
		} else if (updateUserAccount(userAccountID, c)){
			mes = "User Account of id=" + Integer.parseInt(userAccountID) + " has been updated.";
			res = Response.status(Response.Status.OK).entity(c).build();
			// created(uriInfo.getAbsolutePath())

		} else {
			mes = "Numbers don't match";
			res = Response.status(Response.Status.CONFLICT)
			         .entity(mes).build();
		}
		

		
		return res;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{userAccount}")
	public Response postUserAccount(
	            @PathParam("userAccount") String userAccountId, JAXBElement<UserAccount> userAccount) {
		System.out.println(userAccount);
		UserAccount c = userAccount.getValue();
		System.out.println(c.getFullname());
		Response res;
		String mes;
		if(c == null) {
			res = Response.status(Response.Status.BAD_REQUEST).entity(c).build();
		} else if (postUserAccount(userAccountId, c)){
			mes = "User Account of id=" + Integer.parseInt(userAccountId) + " has been inserted.";
			res = Response.created(uriInfo.getAbsolutePath()).build();
			// created(uriInfo.getAbsolutePath())

		} else {
			mes = "User Account with this ID already exists";
			res = Response.status(Response.Status.CONFLICT)
			         .entity(mes).build();
		}
		

		
		return res;
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{userAccount}")
	public Response deleteUserAccount(
	            @PathParam("userAccount") String userAccountId) {
		System.out.println(userAccountId);
		int id = Integer.parseInt(userAccountId);
		Response res;
		String mes;
		if(accounts.get(id) == null) {
			mes = "User Account of given ID doesn't exist.";
			res = Response.status(Response.Status.NOT_FOUND).entity(mes).build();
		} else {
			accounts.remove(id);
			mes = "User Account with this ID has been deleted.";
			res = Response.status(Response.Status.NO_CONTENT)
			         .entity(mes).build();
		}
		

		
		return res;
	}
	
}
