package teste;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bean.CorreioBean;
import com.excecao.ValidacaoExecao;
import com.servico.ConsultaCorreios;
import com.servico.ConsultaCorreiosImpl;

public class TesteJunit {
	
	
	
	private static final String VALID_CEP = "13564030";
	private static final CorreioBean Rua_Jose_Duarte_Souza = new CorreioBean("13564030",
			"Rua José Duarte de Souza", "Jardim Santa Paula", "São Carlos","SP");
	
	
	ConsultaCorreios consultaCorreios;
	
	@Before
	public void setUp() throws Exception {
		consultaCorreios = new ConsultaCorreiosImpl();
	}

	@After
	public void tearDown() throws Exception {
		consultaCorreios = null;
	}
	@Test
	public void busca_por_cep_valido() throws Exception {
		List<CorreioBean> ep = consultaCorreios.buscarCep(VALID_CEP);
		
		System.out.println(ep.get(0).toString()); 
		assertThat(ep.get(0).toString(), equalTo(Rua_Jose_Duarte_Souza.toString()));
	}
	
	@Test(expected=ValidacaoExecao.class)
	public void cep_invalido_lancar_excecao() throws Exception {
		consultaCorreios.buscarCep("1308444000");
	}
	
	@Test(expected=ValidacaoExecao.class)
	public void logradouro_invalido_lancar_excecao() throws Exception {
		consultaCorreios.buscarEndereco("Rua ffgghtrhtrhhy");
	}
	
	@Test
	public void buscaLogradouro() throws Exception {
		List<CorreioBean> ep = consultaCorreios.buscarEndereco("Rua José Duarte de Souza");
		assertThat(ep.get(1).toString(), equalTo(Rua_Jose_Duarte_Souza.toString()));
	}

}
