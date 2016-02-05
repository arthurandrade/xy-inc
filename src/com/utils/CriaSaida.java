package com.utils;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.beans.CorreioBean;

public class CriaSaida {

	public static String montaMensagemRetornoJSON(List<CorreioBean> ends) {
		JSONObject logradouros = new JSONObject();
		JSONArray listLogr = new JSONArray();
		
		try {
			for (CorreioBean end : ends) {

				JSONObject lograd = new JSONObject();

				lograd.put("CEP", end.getCep());
				lograd.put("Logradouro", end.getLogradouro());
				lograd.put("Bairro", end.getBairro());
				lograd.put("Localidade", end.getLocalidade());
				listLogr.put(lograd);

			}
			logradouros.put("logradouros", listLogr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logradouros.toString();

	}

	/**
	 * Prepara a mensagem de retorno para um ou mais CEP
	 * 
	 * @param ceps
	 * @return
	 */
	public static String montaMensagemRetornoXML(List<CorreioBean> ends) {

		StringBuilder sd = new StringBuilder();
		sd.append("<Logradouros>");
		for (CorreioBean end : ends) {
			sd.append("<Lograd>");
			sd.append("<CEP>");
			sd.append(end.getCep());
			sd.append("</CEP>");
			sd.append("<Logradouro>");
			sd.append(end.getLogradouro());
			sd.append("</Logradouro>");
			sd.append("<Bairro>");
			sd.append(end.getBairro());
			sd.append("</Bairro>");
			sd.append("<Localidade>");
			sd.append(end.getLocalidade());
			sd.append("</Localidade>");
			sd.append("</Lograd>");
		}
		sd.append("</Logradouros>");
		return sd.toString();

	}

}
