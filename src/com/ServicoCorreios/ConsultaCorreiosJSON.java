package com.ServicoCorreios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;


@Path("/ConsultaCorreiosJSON")
public class ConsultaCorreiosJSON {

	  @Path("{f}")
	  @GET
	  @Produces("application/json")
	  public Response convertFtoCfromInput(@PathParam("f") String f) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		
		Pattern pattern = Pattern.compile("\\d{8,8}");
        Matcher matcher = pattern.matcher(f);
        if( matcher.find() ){
        	jsonObject.put("cep", f);
        }else{
        	jsonObject.put("locadouro", f);
        }
		 
		
		
		return Response.status(200).entity(jsonObject.toString()).build();
	  }

}
