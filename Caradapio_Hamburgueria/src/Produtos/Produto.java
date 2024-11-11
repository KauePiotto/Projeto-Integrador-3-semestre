package Produtos;

import java.util.Arrays;

public class Produto {
	private int id;
	private String nome;
	private String descricao;
	private double preco;
	private String tipo;
	private byte[] logo;

	// Construtor vazio
	public Produto() {
	}

	// Construtor com parâmetros
	public Produto(String nome, String descricao, double preco, String tipo, byte[] logo) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.tipo = tipo;
		this.logo = logo;
	}

	// Getters e Setters
	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	// Método toString para facilitar a visualização do objeto
	@Override
	public String toString() {
		return "Produto{" + "nome='" + nome + '\'' + ", descricao='" + descricao + '\'' + ", preco=" + preco
				+ ", tipo='" + tipo + '\'' + ", logo=" + Arrays.toString(logo) + '}';
	}
}