package com.controle;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.CorreioBean;
import com.excecao.ValidacaoExecao;
import com.servico.ConsultaCorreios;
import com.utils.CriarSaida;

@RestController
public class CorreioControle {

	@Autowired
	ConsultaCorreios consultaCorreios;
	
	
	@RequestMapping(value = "/buscaCep/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String buscaCep() throws Exception {
		
			return "{erro: informe o Cep}";

		

	}
	
	
	

	@RequestMapping(value = "/buscaCep/{cep}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String buscaCep(@PathVariable String cep) throws Exception {
		List<CorreioBean> listEndereco = null;
		try {
			validaCep(cep);
			listEndereco = consultaCorreios.buscarCep(cep);

			if (listEndereco == null || listEndereco.isEmpty())
				throw new ValidacaoExecao(
						"Não foi encontrado resultado para o CEP " + cep + "",
						null);
		} catch (ValidacaoExecao cepE) {
			return "{erro:" + cepE.getMessage() + "}";

		}

		return CriarSaida.montaMensagemRetornoJSON(listEndereco);

	}

	@RequestMapping(value = "/buscarEnd/{endereco}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String buscarEnd(@PathVariable String endereco) throws Exception {

		List<CorreioBean> listEndereco;
		try {
			validaEnd(endereco);
			listEndereco = consultaCorreios.buscarEndereco(endereco);

			if (listEndereco == null || listEndereco.isEmpty())
				throw new ValidacaoExecao(
						"Não foi encontrado resultado para os dados de "
								+ endereco + "", null);
		} catch (ValidacaoExecao endE) {
			return "{erro:" + endE.getMessage() + "}";

		}

		return CriarSaida.montaMensagemRetornoJSON(listEndereco);

	}
	@RequestMapping(value = "/buscarEnd/", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String buscarEnd() throws Exception {

		
			return "{erro:infoeme o endereço}";


		

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
}
