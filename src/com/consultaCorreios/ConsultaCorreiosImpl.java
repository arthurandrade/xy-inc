package com.consultaCorreios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;







import com.beans.CorreioBean;

public class ConsultaCorreiosImpl implements ConsultaCorreios{

	private static final String BASE_URL = "http://www.buscacep.correios.com.br/servicos/dnec/consultaEnderecoAction.do?relaxation=%s&TipoCep=ALL&semelhante=S&cfm=1&Metodo=listaLogradouro&TipoConsulta=relaxation";

	
	@Override
	public List<CorreioBean> buscarCep(String cep) throws Exception {
		List<CorreioBean> listaEnd = busca(cep);
		return listaEnd;
	}

	@Override
	public List<CorreioBean> buscarEndereco(String logradouro) throws Exception {
		List<CorreioBean> listaEnd = busca(logradouro);
		return listaEnd;
	}
	
	private List<CorreioBean> busca(String item) throws Exception {
		try {
			Document doc = Jsoup.connect(String.format(BASE_URL, item)).get();
			Elements rows = doc.getElementsByAttributeValueMatching("onclick",
					"javascript:detalharCep.*");
			
			List<CorreioBean> ret = new ArrayList<CorreioBean>();
			for (Element tr : rows) {
				Elements tds = tr.select("td");
				
				CorreioBean endereco = new CorreioBean();
				
				endereco.setLogradouro(tds.get(0).text());
				endereco.setBairro(tds.get(1).text());
				endereco.setLocalidade(tds.get(2).text());
				endereco.setCep(tds.get(3).text());
				
				ret.add(endereco);
			}
			
			return ret;
		} catch (IOException e) {
			throw new Exception(e);
		}		
	}
	
	public static void main(String[] args) throws Exception {
		
		ConsultaCorreiosImpl con = new ConsultaCorreiosImpl();
		List<CorreioBean> ret = new ArrayList<CorreioBean>();
		ret = con.busca("Rua Duarte de Souza");
		for(CorreioBean item : ret){
			System.out.println(item.toString());
		}
		
	}

	

}
