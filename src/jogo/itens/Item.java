package jogo.itens;

public abstract class Item {
	protected String nome;
	protected String descricao;
	protected Raridade raridade;
	protected double precoVenda;
	protected boolean empilhavel;
	protected int quantidade;
	
	
	public Item(String nome, String descricao, Raridade raridade, double precoVenda, boolean empilhavel, int quantidade){
		this.nome = nome;
		this.descricao = descricao;
		this.raridade = raridade;
		this.precoVenda = precoVenda;
		this.empilhavel = empilhavel;
		this.quantidade = quantidade;
	}
	
	public Item(String nome, String descricao, Raridade raridade){
		this.nome = nome;
		this.descricao = descricao;
		this.raridade = raridade;
		this.precoVenda = 0.0;
		this.empilhavel = false;
		this.quantidade = 1;
	}
	
	
	// void usar abstrato
	//public abstract void usar(Jogador jogador);

	public void usar(Jogador jogador){}
	
	 
	public String getNome(){
		return this.nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public Raridade getRaridade() {
		return raridade;
	}
	
	@Override
	public String toString() {
		return nome + " (" + raridade + ")";
	}
	
	
	
}