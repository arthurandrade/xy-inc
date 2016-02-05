package com.servicoCorreios;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.beans.CorreioBean;
import com.consultaCorreios.ConsultaCorreios;
import com.consultaCorreios.ConsultaCorreiosImpl;
import com.utils.CriaSaida;

@Path("ConsultaCorreiosXML")
public class ConsultaCorreiosServ {

	private ConsultaCorreios consultaCorreios;

	@Path("{f}")
	@GET
	@Produces("application/xml")
	public String realizarConsulta(@PathParam("f") String f) {

		Pattern pattern = Pattern.compile("\\d{8,8}");
		Matcher matcher = pattern.matcher(f);
		String texto = "";
		consultaCorreios = new ConsultaCorreiosImpl();
		List<CorreioBean> lisrEnd = null;
		try {
			if (matcher.find()) {
				
				lisrEnd = consultaCorreios.buscarCep(f);	
				
			} else {
				lisrEnd = consultaCorreios.buscarEndereco(f);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		texto = CriaSaida.montaMensagemRetornoXML(lisrEnd);
		return texto;
	}

}
