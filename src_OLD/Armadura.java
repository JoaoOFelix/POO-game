public class Armadura extends Item {
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
	
	
	
	public double getDefesa(){
			return this.defesa;
		}
		
	public int getDurabilidade(){
		return this.durabilidade;
	}
	

}