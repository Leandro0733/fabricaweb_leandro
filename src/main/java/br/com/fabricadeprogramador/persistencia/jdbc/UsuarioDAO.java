package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {

	private Connection con = ConexaoFactory.getConnection();	
	
	public void salvar(Usuario usuario) {
		if(usuario.getId()!=null && usuario.getId()!=0) {
			alterar(usuario);
		}
		else {
			cadastrar(usuario);
		}
	}//Fim do Salvar	
	
	public void cadastrar(Usuario usu) {
		String sql = "INSERT INTO usuario(nome, login, senha) VALUES(?, ?, MD5(?))";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			
			preparador.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}//Fim do Cadastrar

	public void alterar(Usuario usu) {
		String sql = "UPDATE usuario SET nome=?, login=?, senha=MD5(?) WHERE id=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			
			preparador.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}//Fim do Alterar

	public void excluir(Usuario usu) {
		String sql = "DELETE FROM usuario WHERE id=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, usu.getId());
			
			preparador.execute();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}//Fim do Excluir
	
	/**
	 * Busca de um registro no banco de dados pelo número do id do usuário	
	 * @param id É um inteiro que representa o número do id do usuário a ser buscado
	 * @return Um objeto usuário quando encontra ou Null quando nào encontra
	 */
	public Usuario buscaPorId(Integer id) {
		String sql = "SELECT * FROM usuario WHERE id=?";
		Usuario usuRetorno = null;
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, id);			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
			}
			
		} catch (SQLException e) {			
			System.out.println("Erro de SQL: "+ e.getMessage());		
		}
		return usuRetorno;
		
	}//Fim do buscaPorId
	
	/**
	 * Realiza a busca de todos os registros da tabela de usuários
	 * @return Uma lista de objetos Usuários contendo 0 elementos quando tiver registro
	 * ou N elementos quando tiver resultado
	 */
	public List<Usuario> buscarTodos() {
		
		String sql = "SELECT * FROM usuario ORDER BY id";
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			 ResultSet resultado = preparador.executeQuery();			
			
			 while(resultado.next()) {
				Usuario usu = new Usuario();
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));
				
				listaUsuario.add(usu);
			 }		
						
		} catch (SQLException e) {			
			System.out.println("Erro de SQL: "+ e.getMessage());		
		}
		return listaUsuario;
		
	}//Fim do buscarTodos
	
	/**
	 * Busca por login e senha de Usuário
	 * @param Usuário Objeto com login e senha a ser consultado no banco
	 * @return Null quando nao encontra no banco ou um Ponteiro a um objeto usuário completo quando encontra
	 */	
	public Usuario autenticar(Usuario usuConsulta) {
		String sql = "SELECT * FROM usuario WHERE login=? AND senha=MD5(?)";
		Usuario usuarioRetorno = null;
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
				Usuario usu = new Usuario();
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));	
				
				//System.out.println("Usuário Autenticado!");
				usuarioRetorno = usu;
			}
			
		} catch (SQLException e) {			
			System.out.println("Erro de SQL: "+ e.getMessage());		
		}
		return usuarioRetorno;
		
	}//Fim do Autenticar
	
	

}//Fim do UsuarioDAO
