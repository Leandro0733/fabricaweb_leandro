package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
    public UsuarioController() {
        super();        
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String nome = req.getParameter("nome");
//		String login = req.getParameter("login");
//		String senha = req.getParameter("senha");
//		
//		Usuario usuario = new Usuario();
//		usuario.setNome(nome);
//		usuario.setLogin(login);
//		usuario.setSenha(senha);
//		
//		UsuarioDAO usuarioDAO = new UsuarioDAO();
//		usuarioDAO.salvar(usuario);
		System.out.println("Chamou doGet!");
		
	}//Fim do doGet
	
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		resp.getWriter().print("<b>Salvo com sucesso!</b>");		
		
   }//Fim do doPost
	
	

}
