package com.controle;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.CorreioBean;
import com.excecao.ValidacaoExecao;
import com.servico.ConsultaCorreios;
import com.utils.CriarSaida;
/**
 * Contem os servidos providos de consulta 
 * @author Arthur Andrade
 */
@RestController
public class CorreioControle {

	@Autowired
	ConsultaCorreios consultaCorreios;

	@RequestMapping(value = "/buscaCep/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String buscaCep() throws Exception {

		return "{erro: informe o Cep}";

	}

	
	/**
	 * Retorna uma lista de endereços relacionados com cep passado. 
	 * @param item - cep 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/buscaCep/{cep}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String buscaCep(@PathVariable String cep) throws Exception {
		List<CorreioBean> listEndereco = null;
		try {

			listEndereco = consultaCorreios.buscarCep(cep);

		} catch (ValidacaoExecao cepE) {
			return "{erro:" + cepE.getMessage() + "}";

		}

		return CriarSaida.montaMensagemRetornoJSON(listEndereco);

	}

	/**
	 * Retorna uma lista de endereços relacionados com logradouro passado. 
	 * @param item - logradouro 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/buscarEnd/{logradouro}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String buscarEnd(@PathVariable String logradouro) throws Exception {

		List<CorreioBean> listEndereco;
		try {

			listEndereco = consultaCorreios.buscarEndereco(logradouro);

		} catch (ValidacaoExecao endE) {
			return "{erro:" + endE.getMessage() + "}";

		}

		return CriarSaida.montaMensagemRetornoJSON(listEndereco);

	}

	@RequestMapping(value = "/buscarEnd/", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String buscarEnd() throws Exception {

		return "{erro:informe o endereço}";

	}

}
