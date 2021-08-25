package br.com.controller;

import java.util.List;
import java.util.Scanner;

import br.com.DAO.DAO;
import br.com.model.Cliente;
import br.com.model.Produto;

public class Aplicacao {
	
	
	public static void main(String[] args) {
		int opcaoMenu;
		Scanner entrada =  new Scanner(System.in);
		mostrarMenu();
		opcaoMenu = entrada.nextInt();
		
		while(opcaoMenu != 10) {
			//mostrarMenu();
			//opcaoMenu = entrada.nextInt();
			
			switch(opcaoMenu) {
			case 1:
				System.out.println("Informe o nome do produto");
				entrada.nextLine();
				
				String nomeProduto = entrada.nextLine();
				System.out.println("Informe o valor do produto");
				
				double valorProduto = entrada.nextDouble();
				
				Produto produto = new Produto(nomeProduto, valorProduto);
				
				try {
					DAO<Produto> pdao = new DAO<>(Produto.class);
					pdao.abrirT().inserir(produto).fecharT().fechar();	
					System.out.println("Produto Inserido com sucesso");
				} catch(Exception e) {
					System.out.println("Erro " + e.getMessage());
				}
				mostrarMenu();
				opcaoMenu = entrada.nextInt();
				break;
			case 2:
				
				DAO<Produto> pdao = new DAO<>(Produto.class);
				List<Produto> listaProdutos = pdao.obterTodos();
				
				for(Produto prod: listaProdutos) {
					System.out.print("\n Codigo:" + prod.getId_produto());
					System.out.print("\n Produto:"+ prod.getNomeProduto());
					System.out.print("\n Valor: R$ "+ prod.getValorProduto()+ "\n");
				}
				mostrarMenu();
				opcaoMenu = entrada.nextInt();
				break;
				
			case 3:
				DAO<Produto> pdao2 = new DAO<>(Produto.class);
				List<Produto> listaProdutos2 = pdao2.obterTodos();
				
				System.out.println("Estes são os produtos cadastrados até agora: ");
				for(Produto prod: listaProdutos2) {
					System.out.print("\n Codigo:" + prod.getId_produto());
					System.out.print("\n Produto:"+ prod.getNomeProduto());
					System.out.print("\n Valor: R$ "+ prod.getValorProduto()+ "\n");
				}
				
				System.out.println("Qual o código do produto que deseja excluir ?");
				int codigoDeletar = entrada.nextInt();
				
				Produto prod = pdao2.obterProduto(codigoDeletar);
				pdao2.abrirT().deletarProduto(prod).fecharT().fechar();
				
				System.out.println("Produto deletado com sucesso");
				mostrarMenu();
				opcaoMenu = entrada.nextInt();
				break;
			case 4:
				DAO<Produto> pdao3 = new DAO<>(Produto.class);
				List<Produto> listaProdutos3 = pdao3.obterTodos();
				
				System.out.println("Estes são os produtos cadastrados até agora: ");
				for(Produto prod2: listaProdutos3) {
					System.out.print("\n Codigo:" + prod2.getId_produto());
					System.out.print("\n Produto:"+ prod2.getNomeProduto());
					System.out.print("\n Valor: R$ "+ prod2.getValorProduto()+ "\n");
				}
				
				System.out.println("Qual o código do produto que deseja alterar ?");
				int codigoAlterar = entrada.nextInt();
				
				pdao3.abrirT();
				
				Produto prod4 = pdao3.obterProduto(codigoAlterar);
				entrada.nextLine();
				System.out.println("Nome do produto atual é " + prod4.getNomeProduto()+ " Deseja alterar ? (s/n)");
				String alterarNome = entrada.nextLine();
				if(alterarNome.equals("s")) {
					System.out.println("Qual será o novo nome do produto ?");
					//entrada.nextLine();
					String novoNome = entrada.nextLine();
					prod4.setNomeProduto(novoNome);
					System.out.println("Nome alterado com sucesso");
				} else {
					System.out.println("Nome permanecerá igual.");
				}
				
				System.out.println("Valor do produto atual é " + prod4.getValorProduto()+ " Deseja alterar ? (s/n)");
				//entrada.nextLine();
				String alterarValor = entrada.nextLine();
				if(alterarValor.equals("s")) {
					System.out.println("Qual será o novo valor ?");
					double novoValor = entrada.nextDouble();
					prod4.setValorProduto(novoValor);
					System.out.println("Valor alterado com sucesso");
				} else {
					System.out.println("Valor permanecerá igual");
				}
				
				pdao3.fecharT().fechar();
				mostrarMenu();
				opcaoMenu = entrada.nextInt();
				break;
			case 5:
				entrada.nextLine();
				System.out.println("Informe o nome do cliente");
				String nomeCliente = entrada.nextLine();
				
				System.out.println("Informe a idade do cliente");
				int idadeCliente = entrada.nextInt();
				
				Cliente cliente = new Cliente(nomeCliente, idadeCliente);
				DAO<Cliente> cdao = new DAO<>(Cliente.class);
				cdao.abrirT().inserir(cliente).fecharT().fechar();
				mostrarMenu();
				opcaoMenu = entrada.nextInt();
				break;
			case 6:
				DAO<Cliente> cdao2 = new DAO<>(Cliente.class);
				List<Cliente> listaClientes = cdao2.obterTodos();
				
				for(Cliente cli : listaClientes) {
					System.out.print("\n Codigo:" + cli.getId_cliente());
					System.out.print("\n Nome:"+ cli.getNomeCliente());
					System.out.print("\n Idade:"+ cli.getIdadeCliente()+ "\n");
				}
				mostrarMenu();
				opcaoMenu = entrada.nextInt();
				break;
			case 7:
				DAO<Cliente> cdao3 = new DAO<>(Cliente.class);
				List<Cliente> listaClientes2 = cdao3.obterTodos();
				
				System.out.println("Estes são os produtos cadastrados até agora: ");
				for(Cliente cli2: listaClientes2) {
					System.out.print("\n Codigo:" + cli2.getId_cliente());
					System.out.print("\n Nome:"+ cli2.getNomeCliente());
					System.out.print("\n Idade:"+ cli2.getIdadeCliente()+ "\n");
				}
				
				System.out.println("Qual o código do cliente que deseja excluir ?");
				int codigoDeletarCliente = entrada.nextInt();
				
				Cliente cli3 = cdao3.obterCliente(codigoDeletarCliente);
				cdao3.abrirT().deletarCliente(cli3).fecharT().fechar();
				
				System.out.println("Cliente deletado com sucesso");
				mostrarMenu();
				opcaoMenu = entrada.nextInt();
				break;
				
			case 8:
				DAO<Cliente> cdao4 = new DAO<>(Cliente.class);
				List<Cliente> listaClientes4 = cdao4.obterTodos();
				
				System.out.println("Estes são os produtos cadastrados até agora: ");
				for(Cliente cli4: listaClientes4) {
					System.out.print("\n Codigo:" + cli4.getId_cliente());
					System.out.print("\n Nome:"+ cli4.getNomeCliente());
					System.out.print("\n Idade:"+ cli4.getIdadeCliente()+ "\n");
				}
				
				System.out.println("Qual o código do cliente que deseja alterar ?");
				int codigoAlterarCliente = entrada.nextInt();
				
				cdao4.abrirT();
				
				Cliente cli4 = cdao4.obterCliente(codigoAlterarCliente);
				entrada.nextLine();
				System.out.println("Nome atual do cliente é " + cli4.getNomeCliente()+ " Deseja alterar ? (s/n)");
				String alterarNomeCliente = entrada.nextLine();
				if(alterarNomeCliente.equals("s")) {
					System.out.println("Qual será o novo nome do cliente ?");
					//entrada.nextLine();
					String novoNomeCliente = entrada.nextLine();
					cli4.setNomeCliente(novoNomeCliente);
					System.out.println("Nome alterado com sucesso");
				} else {
					System.out.println("Nome permanecerá igual.");
				}
				
				System.out.println("Idade atual do cliente é " + cli4.getIdadeCliente()+ " Deseja alterar ? (s/n)");
				//entrada.nextLine();
				String alterarIdade = entrada.nextLine();
				if(alterarIdade.equals("s")) {
					System.out.println("Qual será a nova idade ?");
					int novaIdade = entrada.nextInt();
					cli4.setIdadeCliente(novaIdade);
					System.out.println("Idade alterada com sucesso");
				} else {
					System.out.println("Valor permanecerá igual");
				}
				
				cdao4.fecharT().fechar();
				mostrarMenu();
				opcaoMenu = entrada.nextInt();
				break;
			default:
				System.out.println("Opção inválida");
			}
		}
			
	}
	
	public static void mostrarMenu() {
		System.out.println("Bem vindo! O que deseja fazer ?");
		System.out.println("1 - Cadastrar Novo Produto");
		System.out.println("2 - Ver produtos Cadastrados");
		System.out.println("3 - Deletar produto");
		System.out.println("4 - Alterar cadastro do produto");
		System.out.println("5 - Cadastrar Novo Cliente");
		System.out.println("6 - Ver clientes cadastrados");
		System.out.println("7 - Deletar clientes");
		System.out.println("8 - Alterar cadastro de cliente");
		System.out.println("9 - Realizar pedido");
		System.out.println("10 - Sair");
	}
}
