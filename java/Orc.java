import java.util.Random;

public class Orc extends Inimigo{
    private final Random random = new Random();

    public Orc(String nome) {
        super(nome, "Orc", 50, 8, 2, 1, 18);
    }

    @Override
    public void morrer(Jogador jogador){
        System.out.println(getNome() + " grita: 'UUURAAAGHHH!' e cai morto. Você ganha " + getDropXp() + " XP.");
    }


    @Override
    public void enemyAi(Jogador jogador) {
        //WIP
    }
}
