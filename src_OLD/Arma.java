public class Arma extends ItemUsavel{
	protected double dano;
	protected double velocidade;
	protected double multiCritico;
	protected double chanceCritico;
	protected boolean danoVerdadeiro;
	
	
	public Arma(String nome, String descricao, Raridade raridade, double precoVenda, boolean empilhavel,
				int quantidade, double dano, double velocidade, double multiCritico, double chanceCritico, boolean danoVerdadeiro) {
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
		this.danoVerdadeiro = danoVerdadeiro;
		
		if(chanceCritico > 100){
			chanceCritico = 100;
		} else if (chanceCritico < 0){
			chanceCritico = 0;
		}
		this.chanceCritico = chanceCritico;
    }
	
	@Override
	public void usar(Entidade alvo, Entidade usuario){}
	
	@Override
	public void usarUnico(Entidade alvo, Entidade usuario){}
	
	
	public Arma(String nome, String descricao, Raridade raridade, double dano, double velocidade, double multiCritico, double chanceCritico) {
        super(nome, descricao, raridade, 0.0, false, 1);
        this.dano = dano;
        this.velocidade = velocidade;
        this.multiCritico = multiCritico;
		this.danoVerdadeiro = false;
		
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
	
	public void setDano(double dano){
		this.dano = dano;
	}
	
	public double getVelocidade(){
		return velocidade;
	}
	
	public void setVelocidade(double velocidade){
		this.velocidade = velocidade;
	}
	
	public double getMultiplicadorCritico(){
		return multiCritico;
	}
	
	public void setMultiplicadorCritico(double multiCritico){
		this.multiCritico = multiCritico;
	}
	
	public double getChanceCritico(){
		return chanceCritico;
	}
	
	public void setChanceCritico(double chanceCritico){
		this.chanceCritico = chanceCritico;
	}
	
	public double getCritico() {
		double danoFinal = Formata.formatarDouble(dano * multiCritico);
		return danoFinal;
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
	
	public boolean getDanoVerdadeiro() {
		return danoVerdadeiro;
	}
}