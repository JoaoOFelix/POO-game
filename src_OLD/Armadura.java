public class Armadura extends ItemUsavel {
	protected int durabilidade;
	protected double defesa;
	
	public Armadura(String nome, String descricao, Raridade raridade, double precoVenda, boolean empilhavel, int quantidade, int durabilidade, double defesa){
		super(
			nome,
			descricao,
			raridade,
			precoVenda,
			empilhavel,
			quantidade);
			
		this.durabilidade = durabilidade;
		this.defesa = defesa;
	}
	
	@Override
	public void usar(Entidade alvo, Entidade usuario){
	}
	
	@Override
	public void usarUnico(Entidade alvo, Entidade usuario){
		
	}
	
	public void habilidadeArmadura(Entidade usuario, Entidade origem, double dano){
		System.out.println("Habilidade da Armadura");
	}
	
	public double defesaArmadura(Entidade origem, double dano){
		return dano;
	}
	
	
	public double getDefesa(){
		return this.defesa;
	}
	
	public void setDefesa(double defesa){
		this.defesa = defesa;
	}
		
	public int getDurabilidade(){
		return this.durabilidade;
	}
	

}