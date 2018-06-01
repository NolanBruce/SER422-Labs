package edu.asupoly.ser422.phone.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

@Path("/PhoneEntry")
@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
public class PhoneEntryResource {
	private static PhoneBookList _pList = PhoneBookListFactory.getInstance();
	
	// Technique for location header taken from
	// http://usna86-techbits.blogspot.com/2013/02/how-to-return-location-header-from.html
	@Context
	private UriInfo _uriInfo;
	
	 /**
     * @apiDefine BadRequestError
     * @apiError (Error 4xx) {400} BadRequest Bad Request Encountered
     * */
    /** @apiDefine ActivityNotFoundError
     * @apiError (Error 4xx) {404} NotFound Activity cannot be found
     * */
    /**
     * @apiDefine InternalServerError
     * @apiError (Error 5xx) {500} InternalServerError Something went wrong at server, Please contact the administrator!
     * */

	/* Jersey doesn't seem to know how to serialize a PhoneEntry by default
	@Path("/{phone}")
	public PhoneEntry getEntry(@PathParam("phone") String phone) {
		return _pBook.getEntry(phone);
	}
	 */
	
	 /**
     * @api {get} /PhoneEntry/{phone} PhoneEntry with passed PhoneNumber
     * @apiName getPhone
     * @apiGroup PhoneEntry
     * @apiDescription searches for PhoneEntry based on phone number
     *
     * @apiUse BadRequestError
     * @apiUse InternalServerError
     * @apiUse ActivityNotFoundError
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 200 OK
     * 	[
     *   {"firstName":"Ariel","lastName":"Denham", "phone":"1234567"}
     *  ]
     * 
     * */
	/*
	 * Uses Jackson's ObjectMapper to serialize PhoneEntry into a JSON Object
	 */
	@GET
	@Path("/{phone}")
	public Response getPhone(@PathParam("phone") String p) {
		//check to make sure phone only retains digits
		if(!p.chars().allMatch(Character::isDigit)) {
			try {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}
		//search for entry
		PhoneEntry ent = null;
		try {
			ent = _pList.findEntry(p);
		} catch (EntryNotFoundException exc) {
			//if entry not found in previous list, check unlisted numbers
			PhoneBook pBook = _pList.get("Unlisted");
			try {
				ent = pBook.getEntry(p);
			} catch(EntryNotFoundException ex) {
				ex.getMessage();
				try {
					//if entry not found in any PhoneBook or Unlisted, return 404 error
					return Response.status(Response.Status.NOT_FOUND).build();
				} catch(Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		
		//return PhoneEntry
		try {
			String aString = new ObjectMapper().writeValueAsString(ent);
			return Response.status(Response.Status.OK).entity(aString).build();
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}
	
	/**
     * @api {post} /PhoneEntry PhoneEntry with passed firstName, lastName, and phone
     * @apiName createEntry
     * @apiGroup PhoneEntry
     * @apiDescription	creates entry from a "text/plain" body request.
     * 					Request should be formatted as follows to avoid BadRequestError:
     * 					[firstName] [lastName] [phone number]
     *
     * @apiUse BadRequestError
     * @apiUse InternalServerError
     * @apiUse ActivityNotFoundError
     * 
     * @apiExample Example usage:
     * 	body:
     * 	{
     * 		Nolan Bruce 1057295
     * 	}
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 201 CREATED
     * */
	@POST
	@Consumes("text/plain")
	public Response createEntry(String str) {
		boolean badReq = false;
		String[] entry = str.split(" ");
		//checks if there are too many or two few Strings in array
		if(entry.length != 3) {
			badReq = true;
		}
		//checks if first and last name only contain characters
		if(!entry[0].chars().allMatch(Character::isLetter) || !entry[1].chars().allMatch(Character::isLetter)) {
			badReq = true;
		}
		//checks if number only contains digits
		if(!entry[2].chars().allMatch(Character::isDigit)) {
			badReq = true;
		}
		//create PhoneEntry from str
		if(!badReq) {
			PhoneEntry ent = new PhoneEntry(entry[0], entry[1], entry[2]);
			PhoneBook pBook = _pList.get("Unlisted");
			try {
				pBook.addEntry(ent.getPhone(), ent);
				try {
					return Response.status(Response.Status.CREATED).build();
				} catch (Exception exc) {
					exc.printStackTrace();
					return null;
				}
			} catch (Exception ex) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	/**
     * @api {put} /PhoneEntry/Update Updates PhoneEntry firstName and/or lastName
     * @apiName updateEntry
     * @apiGroup PhoneEntry
     * @apiDescription 	Updates entry with new firstName and/or lastName
     * 					Request should be formatted as follows to avoid BadRequestError:
     * 					[firstName] [lastName] [phone number]
     *
     * @apiUse BadRequestError
     * @apiUse InternalServerError
     * @apiUse ActivityNotFoundError
     * 
     * @apiExample Example usage:
     * 	body:
     * 	{
     * 		Michael Bruce 1057295
     * 	}
     *
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 204 NO_CONTENT
     * */
	@PUT
	@Path("/Update")
	@Consumes("text/plain")
    public Response updateEntry(String str) {
		String[] entry = str.split(" ");
		if(entry.length != 3) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if(_pList.contains(entry[2])) {
			try {
				//find id of PhoneBook containing phone
				String id = _pList.locateEntry(entry[2]);
				PhoneBook pBook = _pList.get(id);
				//find PhoneEntry based on phone
				PhoneEntry ent = _pList.findEntry(entry[2]);
				//change name based on passed text
				ent.changeName(entry[0], entry[1]);	
				//remove entry and replace with updated info
				pBook.removeEntry(ent.getPhone());
				pBook.addEntry(ent.getPhone(), ent);
				return Response.status(Response.Status.NO_CONTENT).build();
			} catch(EntryNotFoundException exc) {
				//if entry not found, return 404
				exc.getMessage();
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		}
		//if entry not contained in list, return 404
		return Response.status(Response.Status.NOT_FOUND).build();
    }
	
	/**
     * @api {delete} /PhoneEntry removes PhoneEntry with passed phone
     * @apiName deleteEntry
     * @apiGroup PhoneEntry
     * @apiDescription	Deletes PhoneEntry by phone number
     *
     * @apiUse BadRequestError
     * @apiUse InternalServerError
     * @apiUse ActivityNotFoundError
     * 
     * @apiParam {phone}		phone number of PhoneEntry to be deleted
     * 
     * @apiSuccessExample Success-Response:
     * 	HTTP/1.1 204 NO_CONTENT
     * */
	@DELETE
    public Response deleteEntry(@QueryParam("phone") String phone) {
		if(_pList.contains(phone)) {
			try {
				String id = _pList.locateEntry(phone);
				PhoneBook pBook = _pList.get(id);
				if(pBook.removeEntry(phone)) {
					return Response.status(Response.Status.NO_CONTENT).build();
				}
			} catch (EntryNotFoundException exc) {
				//if entry not found, return 404
				exc.getMessage();
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		}
		//if entry not contained in list, return 404
		return Response.status(Response.Status.NOT_FOUND).build();
    }
}
