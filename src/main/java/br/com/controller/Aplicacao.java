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
		opcaoMenu = entrada.nextInt();
		
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
			break;
		case 2:
			DAO<Produto> pdao = new DAO<>(Produto.class);
			List<Produto> listaProdutos = pdao.obterTodos();
			
			for(Produto prod: listaProdutos) {
				System.out.print("\n Codigo:" + prod.getId_produto());
				System.out.print("\n Produto:"+ prod.getNomeProduto());
				System.out.print("\n Valor: R$ "+ prod.getValorProduto()+ "\n");
			}
			break;
			
		case 3:
			DAO<Produto> pdao2 = new DAO<>(Produto.class);
			List<Produto> listaProdutos2 = pdao2.obterTodos();
			
			for(Produto prod: listaProdutos2) {
				System.out.print("\n Codigo:" + prod.getId_produto());
				System.out.print("\n Produto:"+ prod.getNomeProduto());
				System.out.print("\n Valor: R$ "+ prod.getValorProduto()+ "\n");
			}
			
			break;
		case 4:
			System.out.println("Op3");
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
			break;
		default:
			System.out.println("Opção inválida");
		}
			
	}
}
