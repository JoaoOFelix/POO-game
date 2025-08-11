public class Arma extends Item{
	protected double dano;
	protected double velocidade;
	protected double multiCritico;
	
	
	public Arma(double dano, double velocidade, double multiCritico){
		this.dano = dano;
		this.velocidade = velocidade;
		this.multiCritico = multiCritico;
	}
	
	
	
	@Override
	public void usar(Jogador jogador){
		
	}
}