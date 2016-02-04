package com.consultaCorreios;

import java.util.List;

import com.beans.CorreioBean;

public interface ConsultaCorreios {
	
	public List<CorreioBean> buscarCep(String cep) throws Exception;
	
	
	public List<CorreioBean> buscarEndereco(String logradouro) throws Exception;

}
