package br.ifpe.transtech.transtech.model;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class FiltroUsuarioLogado implements Filter {

	private String[] pathsLiberados = { "/", "/entrarEmp", "/entrarUsu", "/cadastroEmpresa",
			"/cadastroUsuario", "/salvarUsuario", "/salvarCandidatura", "/efetuarLoginUsuario", "/efetuarLoginEmpresa", "/salvarEmpresa",
			"/index", "/quemSomos", "/listarVagas", "/alteracaoSenhaUsuario", "/alteracaoSenhaEmpresa", "/sending-email", "/redefinirSenha", "/acessoNegado", "/listarVagasSemUsuario", "/detalheVaga(.*)", "/pesquisaVaga(.*)", "/h2-console(.*)", "/css/(.*)", "/images/(.*)" };

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession sessao = req.getSession();

		String path = req.getRequestURI();
		System.out.println("WEBFILTER: " + path);

		// Verificar se o path chamado está na lista dos liberados
		for (String livre : pathsLiberados) {
			if (path.matches(livre)) {
				chain.doFilter(request, response);
				return;
			}
		}

		if (sessao != null && sessao.getAttribute("usuarioLogado") != null || sessao.getAttribute("empresaLogado") !=null) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect("/acessoNegado");
			return;
		}
	}
}
