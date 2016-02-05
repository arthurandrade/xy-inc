package com.servico;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bean.CorreioBean;
import com.excecao.ValidacaoExecao;

public class ConsultaCorreiosImpl implements ConsultaCorreios {

	private static final String BASE_URL = "http://www.buscacep.correios.com.br/servicos/dnec/consultaEnderecoAction.do?relaxation=%s&TipoCep=ALL&semelhante=S&cfm=1&Metodo=listaLogradouro&TipoConsulta=relaxation";

	@Override
	public List<CorreioBean> buscarCep(String cep) throws Exception {

		List<CorreioBean> listEndereco = null;

		validaCep(cep);
		listEndereco = busca(cep);

		if (listEndereco == null || listEndereco.isEmpty())
			throw new ValidacaoExecao(
					"Não foi encontrado resultado para o CEP " + cep + "", null);

		return listEndereco;
	}

	@Override
	public List<CorreioBean> buscarEndereco(String logradouro) throws Exception {
		
		List<CorreioBean> listaEnd = null;
		validaEnd(logradouro);		
		listaEnd = busca(logradouro);
		
		
		if (listaEnd == null || listaEnd.isEmpty())
			throw new ValidacaoExecao(
					"Não foi encontrado resultado para os dados de "
							+ logradouro + "", null);
		return listaEnd;
	}

	private List<CorreioBean> busca(String item) throws Exception {
		try {

			String value = URLEncoder.encode(item, "ISO-8859-1");
			Document doc = Jsoup.connect(String.format(BASE_URL, value)).get();
			Elements rows = doc.getElementsByAttributeValueMatching("onclick",
					"javascript:detalharCep.*");

			List<CorreioBean> ret = new ArrayList<CorreioBean>();
			for (Element tr : rows) {
				Elements tds = tr.select("td");

				CorreioBean endereco = new CorreioBean();

				endereco.setLogradouro(tds.get(0).text());
				endereco.setBairro(tds.get(1).text());
				endereco.setLocalidade(tds.get(2).text());
				endereco.setUf(tds.get(3).text());
				endereco.setCep(tds.get(4).text());

				ret.add(endereco);
			}

			return ret;
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	private void validaCep(String cep) {
		if (cep == null || cep.equals(""))
			throw new ValidacaoExecao(
					"O CEP deve ser informado para realizar a busca", null);

		Pattern pattern = Pattern.compile("\\d{8,8}");
		Matcher matcher = pattern.matcher(cep.replace("-", ""));

		if (!matcher.find()) {
			throw new ValidacaoExecao("O Cep: " + cep + " é inválido!", null);
		}

	}
	
	private void validaEnd(String end) {
		if (end == null || end.equals(""))
			throw new ValidacaoExecao(
					"O Endereço deve ser informado para realizar a busca", null);
	}

	public static void main(String[] args) throws Exception {

		ConsultaCorreiosImpl con = new ConsultaCorreiosImpl();
		List<CorreioBean> ret = new ArrayList<CorreioBean>();
		ret = con.buscarEndereco("Rua José Duarte de Souza");
		for (CorreioBean item : ret) {
			System.out.println(item.toString());
		}

	}

}
