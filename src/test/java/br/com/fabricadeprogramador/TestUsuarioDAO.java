package br.com.fabricadeprogramador;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		
		//testeCadastrar();
		//testeAlterar();
		testeExcluir();
	}
	
	public static void testeCadastrar() {
		Usuario usu = new Usuario();

		usu.setNome("Jão");
		usu.setLogin("jao");
		usu.setSenha("123");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);
		
		System.out.println("Cadastrado com sucesso!");		
		
	}
	
	public static void testeAlterar() {
		Usuario usu = new Usuario();

		usu.setId(2);
		usu.setNome("Jão");
		usu.setLogin("jj");
		usu.setSenha("123");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);
		
		System.out.println("Alterado com sucesso!");		
		
	}
	
	public static void testeExcluir() {
		Usuario usu = new Usuario();

		usu.setId(2);
				
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluido com sucesso!");		
		
	}
	

}
