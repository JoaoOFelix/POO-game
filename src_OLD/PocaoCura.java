public abstract class PocaoCura extends ItemUsavel {
	protected double valorCura;
	
	public PocaoCura(String nome, String descricao, Raridade raridade, double precoVenda, boolean empilhavel, int quantidade, double valorCura){
		super(nome, descricao, raridade, precoVenda, empilhavel, quantidade);
		this.valorCura = valorCura;
	}
	
	@Override
	public void usar(Entidade entidade, Jogador jogador){
		this.removeQuantidade(1);
		
		
		
		jogador.curar(valorCura);
	}
	
	@Override
	public void usarUnico(Entidade entidade, Jogador jogador){
		
	}
	
	
	public double getCura(){
		return valorCura;
	}
}