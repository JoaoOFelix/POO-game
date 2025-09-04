import java.util.Random;

public class Orc extends Inimigo{
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
		jogador.addItem(dropItem());
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
	public Item dropItem(){
		System.out.println(getNome() + " dropou uma Excalibur!");
		Item item = this.arma;
		return item;
	}
	
}
