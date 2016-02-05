package com.servico;

import java.util.List;

import com.bean.CorreioBean;
/**
 * Interface dos serviços
 * @author Arthur Andrade
 *
 */
public interface ConsultaCorreios {
	/**
	 * Retorna uma lista de endereços relacionados com o CEP 
	 * @param cep -  item fornecido
	 * @return
	 */
	public List<CorreioBean> buscarCep(String cep) throws Exception;
	
	/**
	 * Retorna uma lista de endereços relacionados com o logradouro 
	 * @param cep -  item logradouro
	 * @return
	 */
	public List<CorreioBean> buscarEndereco(String logradouro) throws Exception;

}
