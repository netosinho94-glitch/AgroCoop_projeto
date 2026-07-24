package main;

import exception.QuantidadeInvalidaException;
import model.ProdutoAgricola;
import model.Produtor;
import service.CooperativaAgricolaService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CooperativaAgricolaService service = new CooperativaAgricolaService();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== Sistema AgroCoop - Cooperativa Agrícola Familiar ===");
            System.out.println("1. Cadastrar Produtor");
            System.out.println("2. Cadastrar Produto Agrícola");
            System.out.println("3. Registrar Entrega");
            System.out.println("4. Listar Produtores (e remover)");
            System.out.println("5. Relatório: Receita por Produtor");
            System.out.println("6. Relatório: Ranking por Volume");
            System.out.println("7. Relatório: Consolidação Institucional (PAA/PNAE)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida!");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Produtor: ");
                    String nomeProd = scanner.nextLine();
                    System.out.print("Comunidade: ");
                    String comunidade = scanner.nextLine();
                    System.out.print("Propriedade: ");
                    String prop = scanner.nextLine();
                    service.cadastrarProdutor(new Produtor(nomeProd, comunidade, prop));
                    System.out.println("Produtor cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.print("Nome do Produto (ex: Farinha, Mandioca): ");
                    String nomeProdAgr = scanner.nextLine();
                    System.out.print("Unidade de Medida (kg, saca, litro): ");
                    String und = scanner.nextLine();
                    System.out.print("Preço de Referência: R$ ");
                    double preco = Double.parseDouble(scanner.nextLine());
                    service.cadastrarProduto(new ProdutoAgricola(nomeProdAgr, und, preco));
                    System.out.println("Produto cadastrado!");
                    break;
                case 3:
                    System.out.print("Nome do Produtor que realizou a entrega: ");
                    Produtor p = service.buscarProdutorPorNome(scanner.nextLine());
                    System.out.print("Nome do Produto entregue: ");
                    ProdutoAgricola prod = service.buscarProdutoPorNome(scanner.nextLine());
                    
                    if (p == null || prod == null) {
                        System.out.println("Erro: Produtor ou Produto não encontrado.");
                        break;
                    }
                    
                    System.out.print("Quantidade entregue: ");
                    double qtd = Double.parseDouble(scanner.nextLine());
                    System.out.print("Venda Institucional (PAA/PNAE - 8% de incentivo)? (S/N): ");
                    boolean institucional = scanner.nextLine().equalsIgnoreCase("S");
                    
                    try {
                        service.registrarEntrega(p, prod, qtd, institucional);
                        System.out.println("Entrega registrada com sucesso!");
                    } catch (QuantidadeInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("--- Lista de Produtores ---");
                    for (Produtor produtor : service.getProdutores()) {
                        System.out.println("- " + produtor.getNome() + " (Comunidade: " + produtor.getComunidade() + ")");
                    }
                    System.out.print("Deseja remover algum produtor? (Digite o nome ou ENTER para voltar): ");
                    String remover = scanner.nextLine();
                    if (!remover.isEmpty()) {
                        Produtor pRemover = service.buscarProdutorPorNome(remover);
                        if (pRemover != null) {
                            service.removerProdutor(pRemover);
                            System.out.println("Produtor removido.");
                        }
                    }
                    break;
                case 5:
                    System.out.print("Nome do Produtor para calcular receita: ");
                    Produtor pReceita = service.buscarProdutorPorNome(scanner.nextLine());
                    if (pReceita != null) {
                        System.out.printf("Receita total de %s: R$ %.2f\n", pReceita.getNome(), service.calcularReceitaPorProdutor(pReceita));
                    }
                    break;
                case 6:
                    System.out.println("--- Ranking de Produtores por Volume ---");
                    List<Map.Entry<Produtor, Double>> ranking = service.rankingPorVolume();
                    for (int i = 0; i < ranking.size(); i++) {
                        System.out.println((i+1) + "º Lugar: " + ranking.get(i).getKey().getNome() + " - Volume Total: " + ranking.get(i).getValue());
                    }
                    break;
                case 7:
                    System.out.println("--- Consolidação de Entregas por Produto ---");
                    Map<String, Double> consolidador = service.consolidarEntregasPorProduto();
                    for (Map.Entry<String, Double> entry : consolidador.entrySet()) {
                        System.out.println("Produto: " + entry.getKey() + " | Quantidade Total: " + entry.getValue());
                    }
                    break;
            }
        }
        scanner.close();
    }
}
