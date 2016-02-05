package com.servico;

import java.util.List;

import com.bean.CorreioBean;

public interface ConsultaCorreios {
	
	public List<CorreioBean> buscarCep(String cep) throws Exception;
	
	
	public List<CorreioBean> buscarEndereco(String logradouro) throws Exception;

}
