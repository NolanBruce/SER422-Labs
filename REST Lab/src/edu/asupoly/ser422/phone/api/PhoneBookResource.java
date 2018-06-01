package edu.asupoly.ser422.phone.api;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.asupoly.ser422.phone.exceptions.EntryNotFoundException;
import edu.asupoly.ser422.phone.model.PhoneBook;
import edu.asupoly.ser422.phone.model.PhoneEntry;
import edu.asupoly.ser422.phone.model.PhoneBookList;
import edu.asupoly.ser422.phone.model.PhoneBookListFactory;

@Path("/PhoneBooks")
@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
public class PhoneBookResource {
	private static PhoneBookList _pList = PhoneBookListFactory.getInstance();
	
	// Technique for location header taken from
	// http://usna86-techbits.blogspot.com/2013/02/how-to-return-location-header-from.html
	@Context
	private UriInfo _uriInfo;
	
	 /**
     * @apiDefine BadRequestError
     * @apiError (Error 4xx) {400} BadRequest Bad Request Encountered
     * */
	/**
     * @apiDefine ForbiddenError
     * @apiError (Error 4xx) {403} ForbiddenError Request is not allowed
     * */
    /** @apiDefine ActivityNotFoundError
     * @apiError (Error 4xx) {404} NotFound Activity cannot be found
     * */
    /**
     * @apiDefine InternalServerError
     * @apiError (Error 5xx) {500} InternalServerError Something went wrong at server, Please contact the administrator!
     * */

    /**
     * @api {get} /PhoneBooks Get list of all PhoneEntries
     * @apiName getPhoneBookList
     * @apiGroup PhoneBook
     * @apiDescription	Returns all the PhoneEntries in the persistent store
     *
     * @apiUse InternalServerError
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 200 OK
     * 	[
     *   {"firstName":"Ariel","lastName":"Denham", "phone":"1234567"},
     *   {"firstName":"John","lastName":"Worsley", "phone":"2345678"}
     *  ]
     * 
     * */
	@GET
	public Response getPhoneBookList() {
		List<PhoneEntry> results = new ArrayList<PhoneEntry>();
		for(int i=0; i<_pList.getSize();i++) {
			List<PhoneEntry> pBook = _pList.get(i).getEntries();
			for(int j=0; j<pBook.size(); j++) {
				results.add(pBook.get(j));
			}
		}
		try {
			String aString = new ObjectMapper().writeValueAsString(results);
			return Response.status(Response.Status.OK).entity(aString).build();
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}
	
	 /**
     * @api {get} /PhoneBooks/PhoneBook Get list of PhoneEntries in PhoneBook with passed id
     * @apiName getPhoneBook
     * @apiGroup PhoneBook
     * @apiDescription	Returns contents of specific PhoneBook with given id
     *
     * @apiUse InternalServerError
     * @apiUse ActivityNotFoundError
     * @apiUse BadRequestError
     * 
     * @apiParam {id}	id of PhoneBook to be fetched
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 200 OK
     * 	[
     *   {"firstName":"Ariel","lastName":"Denham", "phone":"1234567"},
     *   {"firstName":"John","lastName":"Worsley", "phone":"2345678"}
     *  ]
     * 
     * */
	@GET
	@Path("/PhoneBook")
	public Response getPhoneBook(@QueryParam("id") String id) {
		if(id==null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		List<PhoneEntry> results = new ArrayList<PhoneEntry>();
		for(int i=0; i<_pList.getSize();i++) {
			PhoneBook pBook = _pList.get(i);
			if(pBook.getID().equals(id)) {
				results = pBook.getEntries();
				try {
					String aString = new ObjectMapper().writeValueAsString(results);
					return Response.status(Response.Status.OK).entity(aString).build();
				} catch (Exception exc) {
					exc.printStackTrace();
					return null;
				}
			}
		}
		//if not found, return 404
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	/**
     * @api {get} /PhoneBooks/Subset Get list of PhoneEntries in PhoneBook with passed id and which contains firstName and/or lastName
     * @apiName getSubset
     * @apiGroup PhoneBook
     * @apiDescription	Retrieves subset of PhoneBook with passed id and that contains passed firstName and/or lastName
     * 					If either {first} or {last} param are not passed, results will not be limited by that param
     * 					If both {first} and {last} are not passed (or no {id}), BadRequestError will be turned
     * 					
     *
     * @apiUse InternalServerError
     * @apiUse ActivityNotFoundError
     * @apiUse BadRequestError
     * 
     * @apiParam {id}	id of PhoneBook to search through
     * @apiParam {first}	first name (or subset of name) to search for
     * @apiParam {last}	last name (or subset of name) to search for
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 200 OK
     * 	[
     *   {"firstName":"Ariel","lastName":"Denham", "phone":"1234567"},
     *   {"firstName":"Ariel","lastName":"Worsley", "phone":"2345678"}
     *  ]
     * 
     * */
	@GET
	@Path("/Subset")
	public Response getSubset(@QueryParam("id") String id, @QueryParam("first") String fname, @QueryParam("last") String lname) {
		String firstName = "";
		String lastName = "";
		if(id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		PhoneBook pBook = _pList.get(id);
		if(fname == null && lname == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if(fname != null) {
			firstName = fname;
		}
		if(lname != null) {
			lastName = lname;
		}
		List<PhoneEntry> results =  pBook.getSubset(firstName, lastName);
		//if no entries found that match results, return 404
		if(results.size() == 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		//otherwise, return results
		try {
			String aString = new ObjectMapper().writeValueAsString(results);
			return Response.status(Response.Status.OK).entity(aString).build();
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
     * @api {get} /PhoneBooks/Unlisted Get list of Unlisted PhoneEntries
     * @apiName getUnlisted
     * @apiGroup PhoneBook
     * @apiDescription	Returns all Unlisted entries
     *
     * @apiUse InternalServerError
     * @apiUse ActivityNotFoundError
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 200 OK
     * 	[
     *   {"firstName":"Ariel","lastName":"Denham", "phone":"1234567"},
     *   {"firstName":"John","lastName":"Worsley", "phone":"2345678"}
     *  ]
     * 
     * */
	@GET
	@Path("/Unlisted")
	public Response getUnlisted() {
		List<PhoneEntry> results = _pList.get("Unlisted").getEntries();
		//if no unlisted numbers, return 404
		if(results.size() == 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		try {
			String aString = new ObjectMapper().writeValueAsString(results);
			return Response.status(Response.Status.OK).entity(aString).build();
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		
	}
	
	/**
     * @api {put} /PhoneBooks/AddToBook Add a PhoneEntry to specified book
     * @apiName addToBook
     * @apiGroup PhoneBook
     * @apiDescription	Adds entry with passed number to passed PhoneBook
     * 					Request should be formatted as follows to avoid BadRequestError:
     * 					[phone number] [id]
     * 
     * @apiExample Example usage:
     * 	body:
     * 	{
     * 		1057295 2
     * 	}
     *
     * @apiUse InternalServerError
     * @apiUse ActivityNotFoundError
     * @apiUse BadRequestError
     * @apiUse ForbiddenError
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 204 NO_CONTENT
     * 
     * */
	@PUT
	@Consumes("text/plain")
	@Path("/AddToBook")
	public Response addToBook(String str) {
		PhoneEntry ent;
		String[] info = str.split(" ");
		if(info.length != 2) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			ent = _pList.findEntry(info[0]);
			//if error not throw (meaning entry found in PhoneBook), return 403
			return Response.status(Response.Status.FORBIDDEN).build();
		} catch(EntryNotFoundException exc) {
			try {
				PhoneBook pBook = _pList.get("Unlisted");
				ent = pBook.getEntry(info[0]);
				if(_pList.addEntry(ent, info[1])) {
					return Response.status(Response.Status.NO_CONTENT).build();
				}
			} catch (EntryNotFoundException ex) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
