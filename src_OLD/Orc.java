import java.util.Random;

public final class Orc extends Inimigo{
    private final Random random = new Random();

    public Orc(String nome) {
        super(
			nome,
			"Orc",  //raca
			50,     //vida
			8,		//dano
			2,		//defese
			1,		//velocidade
			38		// dropXP
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
		System.out.println("WIP | WORK IN PROGRESS");
		this.atacar(jogador);
    }
	
	@Override
	public void dropItem(Jogador jogador){
		System.out.println(getNome() + " dropou uma Excalibur!");
		jogador.addItem(this.arma);
		
		if(Sorteador.chance(60)){
			System.out.println(getNome() + " dropu uma poção de cura Grande");
			jogador.addItem(new PocaoCuraGrande());
		} else {
			System.out.println(getNome() + " dropu uma poção de cura Média");
			jogador.addItem(new PocaoCuraMedia());
		}
		
	}
	
}
