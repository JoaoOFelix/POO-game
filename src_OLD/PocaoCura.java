public abstract class PocaoCura extends ItemUsavel {
	protected double valorCura;
	
	public PocaoCura(String nome, String descricao, Raridade raridade, double precoVenda, boolean empilhavel, int quantidade, double valorCura){
		super(nome, descricao, raridade, precoVenda, empilhavel, quantidade);
		this.valorCura = valorCura;
	}
	
	@Override
	public void usar(Entidade entidade){
		entidade.curar(valorCura);
	}
	
	
	public double getCura(){
		return valorCura;
	}
}