package com.excecao;

public class ValidacaoExecao extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ValidacaoExecao(String message,Throwable cause) {
	    super(message,cause);
	  }

}
