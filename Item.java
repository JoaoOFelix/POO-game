public abstract class Item {
	protected String nome;
	protected String descricao;
	protected Raridade raridade;
	protected double precoVenda;
	protected boolean empilhavel;
	protected int quantidade;
	
	
	public Item (String nome, String descricao, Raridade raridade, double precoVenda, boolean empilhavel, int quantidade){
		this.nome = nome;
		this.descricao = descricao;
		this.raridade = raridade;
		this.precoVenda = precoVenda;
		this.empilhavel = empilhavel;
		this.quantidade = quantidade;
	}
	
	
	public abstract void usar(Jogador jogador);
	
	 
	public String getNome(){
		return this.nome;
	}
	
	
	
}