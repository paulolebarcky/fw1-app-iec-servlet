package br.com.iec.framework1.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.iec.framework1.util.ServletUtilities;

public class ProdutoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String title = ServletUtilities.filter("Pedido Enviado com sucesso!");

        int codProduto = Integer.parseInt(request.getParameter("codProduto"));
        Produto produto = TabelaPreco.getDadosProduto(codProduto);

        if (produto != null) {
            int qtde = Integer.parseInt(request.getParameter("qtde"));
            double total = qtde * produto.getPreco();

            out.println(ServletUtilities.headWithTitle(title)
                    + "<body>\n"
                    + " <h1 align=center>" + title + "</h1>\n" + "<ul>\n"
                    + " <li>Cod. Produto: " + request.getParameter("codProduto") + "</li>"
                    + " <li>Produto: " + produto.getNome() + "</li>"
                    + " <li>Quantidade solicitada: " + qtde + "</li>"
                    + " <li>Nome: " + request.getParameter("nomeProduto") + "</li>"
                    + " <li>Endereco de entrega: " + request.getParameter("endereco") + "</li>"
                    + " <li>Cartao de credito: " + request.getParameter("tipoCartao") + "</li>"
                    + " <li>Numero cartao credito: " + request.getParameter("numCartao") + "</li>"
                    + " <li>Valor unidade: R$ " + String.valueOf(produto.getPreco()) + "</li>"
                    + "<br/><br/>"
                    + " <li><h2>Valor Total do pedido: R$" + String.valueOf(total) + "</h2></li>"
                    + "</ul>\n"
                    + "<a href=\"/webapp-framework1-a\">Voltar para compra de produtos</a>"
                    + "</body></html>");
        } else {
            out.println(ServletUtilities.headWithTitle(title)
                    + "<body>\n"
                    + " <h1 align=center>Erro ao realizar solicitação de produto</h1>\n"
                    + "<a href=\"/webapp-framework1-a\">Voltar para compra de produtos</a>"
                    + "</body></html>");

        }

    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
