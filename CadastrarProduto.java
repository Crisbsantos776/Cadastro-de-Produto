import java.util.*;
import java.io.*;

public class CadastrarProduto {

    static final String ARQUIVO = "produtos.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int opcao;
        do {
            System.out.println("\nCADASTRO DE PRODUTOS");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Listar produtos");
            System.out.println("3. Remover produto");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> remover();
            }
        } while (opcao != 0);

        System.out.println("Até logo!");
    }

    static void cadastrar() throws Exception {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Preço: ");
        String preco = sc.nextLine();
        System.out.print("Quantidade: ");
        String qtd = sc.nextLine();

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO, true))) {
            pw.println(nome + ";" + preco + ";" + qtd);
        }
        System.out.println("Produto cadastrado!");
    }

    static void listar() throws Exception {
        File f = new File(ARQUIVO);
        if (!f.exists() || f.length() == 0) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\nProdutos:");
        int i = 1;
        try (Scanner leitor = new Scanner(f)) {
            while (leitor.hasNextLine()) {
                String[] p = leitor.nextLine().split(";");
                System.out.printf("%d. %s | R$ %s | Qtd: %s%n", i++, p[0], p[1], p[2]);
            }
        }
    }

    static void remover() throws Exception {
        listar();
        System.out.print("Número do produto para remover: ");
        int num = Integer.parseInt(sc.nextLine());

        File f = new File(ARQUIVO);
        List<String> linhas = new ArrayList<>();
        try (Scanner leitor = new Scanner(f)) {
            while (leitor.hasNextLine()) linhas.add(leitor.nextLine());
        }

        if (num < 1 || num > linhas.size()) {
            System.out.println("Número inválido.");
            return;
        }

        linhas.remove(num - 1);
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO, false))) {
            for (String l : linhas) pw.println(l);
        }
        System.out.println("Produto removido!");
    }
}
