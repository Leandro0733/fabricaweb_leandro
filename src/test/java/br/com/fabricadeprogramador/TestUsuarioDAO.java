package br.com.fabricadeprogramador;

import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		
		//testeCadastrar();
		//testeAlterar();
		//testeExcluir();
		//testeSalvar();
		//testeBuscaPorId();
		//testeBuscarTodos();
		//testeAutenticar();
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
	
	public static void testeSalvar() {
		Usuario usu = new Usuario();

		usu.setId(3);
		usu.setNome("Jão");
		usu.setLogin("jajao");
		usu.setSenha("123");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		
		System.out.println("Salvo com sucesso!");		
		
	}
	
	public static void testeBuscaPorId() {
		UsuarioDAO usuDAO = new UsuarioDAO();
		//System.out.println(usuDAO.buscaPorId(1));
		Usuario usuRetorno = usuDAO.buscaPorId(3);
		if(usuRetorno!=null) {
		System.out.println("Nome: "+usuRetorno.getNome() + "\n" +"Login: "+usuRetorno.getLogin());		
		}
		else {
			System.out.println("Usuário não existe.");
		}
	}
	
	public static void testeBuscarTodos() {
		UsuarioDAO usuDAO = new UsuarioDAO();
		List<Usuario> listaResultado = usuDAO.buscarTodos();
		
		for(Usuario u: listaResultado) {
			System.out.println("Id:"+u.getId() + " - " + "Nome:"+u.getNome() + " - " + "Login:"+u.getLogin() + " - " + "Senha:"+u.getSenha() );
		}
	}
	
	public static void testeAutenticar() {
		Usuario usu = new Usuario();	
		usu.setLogin("leandro");
		usu.setSenha("123");	
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		if(usuDAO.autenticar(usu)!=null) {
			System.out.println("Usuário "+usuDAO.autenticar(usu).getNome()+" logado!");
		}
		else {
			System.out.println("Login ou Senha inválido.");
		}
	}
	

}
