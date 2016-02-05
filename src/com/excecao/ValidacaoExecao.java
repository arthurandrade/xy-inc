package com.excecao;

public class ValidacaoExecao extends RuntimeException {

	/**
	 *
	 * Refere-se a execão realianada a validações
	 * @author Arthur Andrade
	 */
	private static final long serialVersionUID = 1L;
	
	public ValidacaoExecao(String message,Throwable cause) {
	    super(message,cause);
	  }

}
