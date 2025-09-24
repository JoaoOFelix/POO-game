import java.util.Random;

public final class Orc extends Inimigo{
    private final Random random = new Random();

    public Orc(String nome) {		
        super(
			nome,
			"Orc",  //raca
			50,     //vida
			5,		//dano
			2,		//defesa
			1,		//velocidade
			50		// dropXP
		);
		
		
		this.setArma(new Excalibur());
		
    }

    @Override
    public void aoMorrer(Jogador jogador){
        System.out.println(getNome() + " grita: 'UUURAAAGHHH!' e cai morto. Você ganha " + getDropXp() + " XP.");
		jogador.ganharXp(this.getDropXp());
		
		dropItem(jogador);
		
    }
	
	@Override
	public void usar(){
		//---
	}


    @Override
    public void enemyAi(Jogador jogador) {
		System.out.println("WIP");
		
		
		if(Sorteador.chance(30)){
			int buff = 2;
			System.out.println("Orc usou aumentou seu dano em " + buff + " pontos de dano!");
			this.buffDano(buff);
			
		} else if(Sorteador.chance(20)){
			int buff = 1;
			System.out.println("Orc usou aumentou sua defesa em " + buff + " pontos!");
			this.buffDefesa(buff);
		}
		
		if(this.vida < (this.vida - 20) && Sorteador.chance(10)){
			System.out.println("Orc curou 15 de vida");
			this.curar(15);
		}
		
		
		if(Sorteador.chance(15)){
			ataqueForte(jogador);
			
		} else if(Sorteador.chance(20)){
			ataqueDuplo(jogador);
			
		} else if(Sorteador.chance(90)){
			atacar(jogador);
			
		} else {
			System.out.println("Orc errou o ataque");
		}
    }
	
	@Override
	public void dropItem(Jogador jogador){
		if(Sorteador.chance(65)){
			System.out.println(getNome() + " dropou uma Excalibur!");
			jogador.addItem(new Excalibur());
			
		} else if(Sorteador.chance(80)){
			System.out.println(getNome() + " dropou uma Big Bertha!");
			jogador.addItem(new BigBertha());
			
		} else if(Sorteador.chance(60)){
			System.out.println(getNome() + " dropou uma epsada vampirica!");
			jogador.addItem(new EspadaVampirica());
		}
			
		
		if(Sorteador.chance(60)){
			System.out.println(getNome() + " dropu uma poção de cura Grande");
			jogador.addItem(new PocaoCuraGrande());
		} else {
			System.out.println(getNome() + " dropu uma poção de cura Média");
			jogador.addItem(new PocaoCuraMedia());
		}
		
	}	
	
	@Override
	public void atacar(Jogador jogador){
		double dano = danoCritico();
		
		
		System.out.println("Orc atacou o jogador causando " + dano + "de dano");
		jogador.dmgPlayer(dano);
	}
	
	
	private void buffDano(int dano){
		this.dano += dano;
	}
	
	private void buffDefesa(int defesa){
		this.defesa += defesa;
	}
	
	private void ataqueForte(Jogador jogador){
		double dano = danoSemCritico() * 1.5;
		
		
		jogador.dmgPlayer(dano);
	}
	
	private void ataqueDuplo(Jogador jogador){
		double danoSemCritico = danoSemCritico();
		double danoFinal;
		
		System.out.println("Orc tentou um ataque duplo...");
		
		
		if(Sorteador.chance(82)){
					
			if(Sorteador.chance(60)){
				System.out.println("Orc acertou dois ataques!");
				danoFinal = (danoSemCritico * 0.9) + (danoSemCritico * 0.75);
				
			} else {
				System.out.println("Orc acertou apenas um ataque...");
				danoFinal = (danoSemCritico * 0.9);
			}
			
			jogador.dmgPlayer(danoFinal);
			
		} else {
			System.out.println("Orc não conseguiu atacar...");
		}
	}
	
	
}
