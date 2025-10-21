public final class GolemDePedra extends Inimigo {
	
    public GolemDePedra(String nome) {		
        super(
			nome,
			"Golem de pedra",  //raca
			100,     //vida
			8,		//dano
			30,		//defesa
			1,		//velocidade
			100		// dropXP
		);
		
		this.dinheiro = 430;
    }

    @Override
    public void aoMorrer(Jogador jogador){
        System.out.println(getNome() + " grita: 'AAARGHHHH!' e cai morto. Você ganha " + getDropXp() + " XP.");
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
		
		
		System.out.println("Golem de pedra atacou o jogador causando " + dano + "de dano");
		jogador.tomarDano(dano, this, jogador);
	}
}