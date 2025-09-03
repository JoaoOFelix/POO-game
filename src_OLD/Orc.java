import java.util.Random;

public class Orc extends Inimigo{
    private final Random random = new Random();

    public Orc(String nome) {
        super(nome, "Orc", 50, 8, 2, 1, 18);
		this.setArma(new Excalibur());
    }

    @Override
    public void morrer(Jogador jogador){
        System.out.println(getNome() + " grita: 'UUURAAAGHHH!' e cai morto. Você ganha " + getDropXp() + " XP.");
		jogador.addItem(dropItem());
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
