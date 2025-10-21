public abstract class Dragao extends Inimigo {	
    public Dragao(String nome) {
        super(
			nome,
			"Dragão",  //raca
			200,     //vida
			20,		//dano
			20,		//defesa
			4,		//velocidade
			200		// dropXP
		);
		
		this.dinheiro = 430;
    }
	
	public Dragao(String nome, String raca, double vida, double dano, double defesa, double velocidade, int dropXp, double dinheiro) {
        super(
			nome,
			raca,  //raca
			vida,     //vida
			dano,		//dano
			defesa,		//defesa
			velocidade,		//velocidade
			dropXp		// dropXP
		);
		
		this.dinheiro = dinheiro;
    }

    @Override
    public void aoMorrer(Jogador jogador){
        System.out.println(getNome() + " grita: 'WRAAAAAAAAAAATHHH!' e cai morto. Você ganha " + getDropXp() + " XP.");
		jogador.ganharXp(this.getDropXp());
		
		dropItem(jogador);
		dropDinheiro(jogador, this.dinheiro);
    }
	
	@Override
	public void usar(){
		//---
		
	}


    @Override
    public void enemyAi(Jogador jogador) {
		System.out.println("WIP");
		atacar(jogador);
    }
	
	@Override
	public void dropItem(Jogador jogador){
		jogador.addItem(new BigBertha());
	}	
	
	@Override
	public void atacar(Jogador jogador){
		double dano = danoCritico();
		
		
		System.out.println(this.raca + " atacou o jogador causando " + dano + " de dano");
		jogador.tomarDano(dano, this, jogador);
	}
}