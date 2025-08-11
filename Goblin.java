import java.util.Random;

public class Goblin extends Inimigo{

    private final Random random = new Random();

    private int tentativasFuga = 0;

	public Goblin(String nome){
		super(nome, "Goblin", 30, 10, 0, 3, 5);
	}


    @Override
    public void morrer(Jogador jogador) {
        System.out.println(getNome() + " grita: 'Aaargh!' e cai morto. Você ganha " + this.getDropXp() + " XP.");
        jogador.ganharXp(this.getDropXp());
    }

    public boolean chance(double chance){
        return random.nextInt(100) < chance;
    }

    @Override
    public void enemyAi(Jogador jogador) {

        //tem 35% de chanche de tentar fugir caso tenha menos de 40% de vidae caso não tenha tentado fugir
        if(getVida() <= (vidaMax * 0.35) && chance(25) && tentativasFuga == 0){

                System.out.println(getNome() + " Tentou fugir...");

                //30% de chance de fuga
                if(chance(30)) {
                    System.out.println(getNome() + " conseguiu fugir da batalha");
                    fuga();
                } else {
                    System.out.println(getNome() + " falhou ao fugir da batalha");
                }

                tentativasFuga++;
        }

        //Ataque desesperado caso tenha menos que 50% da vida
        if(getVida() <= (getVidaMax() * 0.5) && chance(32)){

            System.out.println(getNome() + " Usou o ataque desesperado");
            ataqueDesesperado(jogador);

        } else if(getVida() <= (getVidaMax() * 0.5) && chance(15)) {

            ataqueEspecial(jogador);

        } else if (chance(75)){

            atacar(jogador);

        } else if(chance(5)){

            System.out.println("Goblin errou o ataque!");
        }

    }

    @Override
    public void atacar(Jogador jogador) {
        System.out.println(getNome() + " ataca com uma adaga! Causando " + getDano() + " de dano.");
        jogador.dmgPlayer(getDano());
    }

    //Tem 35% de chance de atacar duas vezes causando o dobro de dano
    public void ataqueRapido(Jogador jogador){
        if(chance(35)){
            System.out.println("Tentou atacar rápido, e atingiu 2 vezes");
            System.out.println("Goblin causou " + getDano() * 2);
            jogador.dmgPlayer(getDano() * 2);
        } else {
            System.out.println("Tentou atacar rápido, mas atingiu apenas 1 vez");
            System.out.println("Goblin causou " + getDano() * 0.75);
            jogador.dmgPlayer(getDano() * 0.75);
        }
    }

    public void contraAtaque(Jogador jogador){
        System.out.println("TESTE DE CONTRA-ATAQUE---");
        if(chance(50)){
            System.out.println("CONTRA-ATAQUE FALHOU---");
        } else {
            System.out.println("CONTRA-ATAQUE FEITO COM SUCESSO---");
        }
    }

    public void ataqueEspecial(Jogador jogador){
        System.out.println(getRaca() + " usou seu ataque especial e causou " + (getDano() * 2) + " de dano");
        jogador.dmgPlayer(getDano() * 2);
    }

    public void ataqueDesesperado(Jogador jogador){
        System.out.println("Goblin causou " + getDano() * 1.5 + " mas no desespero se acertou tomando 4 de dano");
        jogador.dmgPlayer(getDano() * 1.5);
        this.tomarDano(4, jogador);
    }

    public void fuga(){
        System.out.println("TESTE DE FUGA---");
    }
}