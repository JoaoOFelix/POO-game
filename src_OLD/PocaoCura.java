public abstract class PocaoCura extends ItemUsavel {
	protected double valorCura;
	
	public PocaoCura(String nome, String descricao, Raridade raridade, double precoVenda, boolean empilhavel, int quantidade, double valorCura){
		super(nome, descricao, raridade, precoVenda, empilhavel, quantidade);
		this.valorCura = valorCura;
	}
	
	@Override
	public void usar(Entidade alvo, Entidade usuario){
		this.removeQuantidade(1);		
		
		usuario.curar(valorCura);
	}
	
	@Override
	public void usarUnico(Entidade alvo, Entidade usuario){
		
	}
	
	
	public double getCura(){
		return valorCura;
	}
}