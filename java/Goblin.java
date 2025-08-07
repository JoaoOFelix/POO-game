public class Goblin extends Inimigo{
	public Goblin(String nome){
		super(String nome, "Goblin", 15, 10, 0, 3, 5)
	}
	
	
	
	@Override
    public void atacar() {
        System.out.println(nome + " ataca com uma adaga! Causa " + dano + " de dano.");
    }

    @Override
    public void morrer() {
        System.out.println(nome + " grita: 'Aaargh!' e cai morto. Você ganha " + dropXp + " XP.");
    }
}