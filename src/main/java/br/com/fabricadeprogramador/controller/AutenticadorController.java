package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet(name = "AutenticadorController", urlPatterns = { "/autenticador.do" })
public class AutenticadorController extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession sessao = req.getSession(false);
		
		if(sessao!=null) {
			sessao.invalidate();
		}		
		resp.sendRedirect("login.html");
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuAutenticado = usuarioDAO.autenticar(usuario);	
		
		if(usuAutenticado!=null) {
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			sessao.setMaxInactiveInterval(3000);
			
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);;
		}
		else {
			resp.getWriter().print("<script> window.alert('Usuário não encontrado.'); location.href='login.html';</script>");
			
		}
		
	}//Fim do doPost
	
	

}
