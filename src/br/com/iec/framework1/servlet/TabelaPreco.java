package br.com.iec.framework1.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class TabelaPreco {

    public static List<Produto> produtos;

    public static Produto getDadosProduto(int codProduto) {

        if (produtos == null || produtos.isEmpty()) {
            montarTabela();
        }

        for (Produto produto : produtos) {
            if (produto.getCod() == codProduto) {
                return produto;
            }
        }

        return null;

    }

    private static void montarTabela() {

        produtos = new ArrayList();
        
        URL url = TabelaPreco.class.getResource("resources/tabelaprodutos.txt");
        File file = new File(url.getPath());

        if (!file.exists()) {
            System.err.println("Arquivo \"tabelaprodutos\" não foi encontrado");
            return;
        }
        
        System.out.printf("Lendo arquivo de produtos: %s", url.getPath());
 
        try {
            FileReader arq = new FileReader(file);
            BufferedReader lerArq = new BufferedReader(arq);

            while (lerArq.ready()) {

                String linha = lerArq.readLine(); // lê da segunda até a última linha
                System.out.printf("%s", linha);

                String[] values = linha.split(";");

                if (values.length == 3) {
                    try {
                        Produto produto = new Produto();
                        produto.setCod(Integer.parseInt(values[0]));
                        produto.setNome(values[1]);
                        produto.setPreco(Double.parseDouble(values[2]));

                        produtos.add(produto);

                    } catch (Exception e) {
                    }

                }
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        
        System.out.printf("Fim leitura arquivo");
    }
}
