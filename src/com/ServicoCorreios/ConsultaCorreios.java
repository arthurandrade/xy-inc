package com.ServicoCorreios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("ConsultaCorreiosXML")
public class ConsultaCorreios {
	
 
	@Path("{f}")
	@GET
	@Produces("application/xml")
	public String convertCtoFfromInput(@PathParam("f") String f) {
		
		
		Pattern pattern = Pattern.compile("\\d{8,8}");
        Matcher matcher = pattern.matcher(f);
        String texto = "";
        if( matcher.find() ){
        	texto = texto + "<cep>" + f + "</cep>";
        }else{
        	texto = texto + "<locadouro>" + f + "</locadouro>";
        }
 
		
		return "<ConsultaCorreios>" + texto + "</ConsultaCorreios>";
	}

}
