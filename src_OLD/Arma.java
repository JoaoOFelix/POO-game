public class Arma extends ItemUsavel{
	protected double dano;
	protected double velocidade;
	protected double multiCritico;
	protected double chanceCritico;
	
	
	public Arma(String nome, String descricao, Raridade raridade, double precoVenda, boolean empilhavel,
				int quantidade, double dano, double velocidade, double multiCritico, double chanceCritico) {
        super(
			nome,
			descricao,
			raridade,
			precoVenda,
			empilhavel,
			quantidade);
			
        this.dano = dano;
        this.velocidade = velocidade;
        this.multiCritico = multiCritico;
		
		if(chanceCritico > 100){
			chanceCritico = 100;
		} else if (chanceCritico < 0){
			chanceCritico = 0;
		}
		this.chanceCritico = chanceCritico;
    }
	
	@Override
	public void usar(Entidade entidade, Jogador jogador){}
	
	@Override
	public void usarUnico(Entidade entidade, Jogador jogador){}
	
	
	public Arma(String nome, String descricao, Raridade raridade, double dano, double velocidade, double multiCritico, double chanceCritico) {
        super(nome, descricao, raridade, 0.0, false, 1);
        this.dano = dano;
        this.velocidade = velocidade;
        this.multiCritico = multiCritico;
		
		if(chanceCritico > 100){
			chanceCritico = 100;
		} else if (chanceCritico < 0){
			chanceCritico = 0;
		}
		this.chanceCritico = chanceCritico;
    }
	
	public double getDano() {
		return dano;
	}
	
	public double getVelocidade(){
		return velocidade;
	}
	
	public double getMultiplicadorCritico(){
		return multiCritico;
	}
	
	public double getChanceCritico(){
		return chanceCritico;
	}
	
	public double getCritico() {
		return dano * multiCritico;
	}
	
	public double ataqueCritico(){
		if(Sorteador.chance(this.chanceCritico)){
			System.out.println("Dano crítico!");
			return getCritico();
		}
		
		return getDano();
	}
	
	public double ataqueSemCritico(){
		return getDano();
	}
}