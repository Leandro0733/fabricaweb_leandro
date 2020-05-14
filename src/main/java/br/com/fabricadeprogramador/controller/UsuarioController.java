package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		resp.setContentType("text/html");
		String acao = req.getParameter("acao");
		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		RequestDispatcher dispacher;
		
		if(acao.equals("excluir")) {
			String id = req.getParameter("id");				
			
			if(id!=null && id!="0" && !id.isEmpty()) {
				usuario.setId(Integer.parseInt(id));
			}			
			usuarioDAO.excluir(usuario);
						
			//Redirecionando pelo cliente (Browser)
			resp.sendRedirect("usucontroller.do?acao=listar");
		}
		else if(acao.equals("listar")) {
			List<Usuario> listaUsuario = usuarioDAO.buscarTodos();
			req.setAttribute("listausuario", listaUsuario);		
			dispacher = req.getRequestDispatcher("WEB-INF/listausuario.jsp");
			dispacher.forward(req, resp);
		}		
		else if(acao.equals("alterar")) {
			String id = req.getParameter("id");
			usuario = usuarioDAO.buscaPorId(Integer.parseInt(id));
			req.setAttribute("usuario", usuario);
			dispacher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispacher.forward(req, resp);			
		}
		else if (acao.equals("cadastrar")) {
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			req.setAttribute("usuario", usuario);
			dispacher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispacher.forward(req, resp);
		}
		
		
	}//Fim do doGet
	
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String id = req.getParameter("id");
    	String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		int idConfere = (Integer.parseInt(id));
		String mensagem = null;
		
		Usuario usuario = new Usuario();
		
		if(id!="0" || id!=null) {
			usuario.setId(Integer.parseInt(id));			
		}
		if(idConfere==0 || id==null) {
			mensagem ="<script>"+ 
							"alert('Cadastrado com sucesso!');"+
							"location.href='usucontroller.do?acao=listar'; "+
					  "</script>";
		}
		else {
			mensagem ="<script>"+ 
							"alert('Alterado com sucesso!');"+
							"location.href='usucontroller.do?acao=listar'; "+
					 "</script>";
		}
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		resp.getWriter().print(mensagem);		
		
   }//Fim do doPost
	
	

}
