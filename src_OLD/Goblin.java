public class Goblin extends Inimigo{
	private int tentativasFuga = 0;

	public Goblin(String nome){
		
		super(
			nome,      //nome
			"Goblin",  //raca
			30,        //vida
			10,        //dano
			0,		   //defesa
			3,		   //velocidade
			5          //drop de experiencia
			);
		
		this.setArma(new AdagaGoblin());
	}


	@Override
	public Item dropItem(){
		if(Sorteador.chance(15)){
			System.out.println(getNome() + " dropou uma Excalibur!");
			return new Excalibur();
		} else if(Sorteador.chance(80)) {
			System.out.println(getNome() + " dropou uma Adaga goblin");
			return new AdagaGoblin();
		} else {
			return null;			
		}
	}


    @Override
    public void morrer(Jogador jogador) {
        System.out.println(getNome() + " grita: 'Aaargh!' e cai morto. Você ganha " + this.getDropXp() + " XP.");
        jogador.ganharXp(this.getDropXp());
		
		Item itemDropado = dropItem();
		if(itemDropado != null){
			jogador.addItem(itemDropado);
		} else {
			System.out.println(this.getRaca() + " não dropu nenhum item.");
		}
		
    }


	//INTELIGÊNCIA ARTIFICIAL DO INIMIGO
    @Override
    public void enemyAi(Jogador jogador) {

        //tem 35% de chanche de tentar fugir caso tenha menos de 40% de vidae caso não tenha tentado fugir
        if(getVida() <= (vidaMax * 0.35) && Sorteador.chance(25) && tentativasFuga == 0){

                System.out.println(getNome() + " Tentou fugir...");

                //30% de chance de fuga
                if(Sorteador.chance(30)) {
                    System.out.println(getNome() + " conseguiu fugir da batalha");
                    fuga();
                } else {
                    System.out.println(getNome() + " falhou ao fugir da batalha");
                }

                tentativasFuga++;
        }

        //Ataque desesperado caso tenha menos que 50% da vida
        if(getVida() <= (getVidaMax() * 0.5) && Sorteador.chance(32)){

            System.out.println(getNome() + " Usou o ataque desesperado");
            ataqueDesesperado(jogador);

        } else if(getVida() <= (getVidaMax() * 0.5) && Sorteador.chance(15)) {

            ataqueEspecial(jogador);

        } else if (Sorteador.chance(85)){

            atacar(jogador);

        } else if(Sorteador.chance(8)){

            System.out.println("Goblin errou o ataque!");
        }

    }

    @Override
    public void atacar(Jogador jogador) {
		double danoTmp = this.getDano();
		
		if(hasArma()){
			System.out.println(getNome() + " ataca com " + arma.getNome() + "! Causando " + danoTmp + " de dano.");
		} else {
			System.out.println(getNome() + " ataca com os punhos! Causando " + danoTmp + " de dano");
		}
        jogador.dmgPlayer(danoTmp);
    }

    //Tem 35% de chance de atacar duas vezes causando o dobro de dano
    public void ataqueRapido(Jogador jogador){
		double danoTmp = getDano();
		
        if(Sorteador.chance(35)){
            System.out.println("Tentou atacar rápido, e atingiu 2 vezes");
            System.out.println("Goblin causou " + danoTmp * 2);
            jogador.dmgPlayer(danoTmp * 2);
        } else {
            System.out.println("Tentou atacar rápido, mas atingiu apenas 1 vez");
            System.out.println("Goblin causou " + danoTmp * 0.75);
            jogador.dmgPlayer(danoTmp * 0.75);
        }
    }

    public void contraAtaque(Jogador jogador){
        System.out.println("TESTE DE CONTRA-ATAQUE---");
        if(Sorteador.chance(50)){
            System.out.println("CONTRA-ATAQUE FALHOU---");
        } else {
            System.out.println("CONTRA-ATAQUE FEITO COM SUCESSO---");
        }
    }

    public void ataqueEspecial(Jogador jogador){
		double danoTmp = getDano();
		
        System.out.println(getRaca() + " usou seu ataque especial e causou " + (getDano() * 2) + " de dano");
        jogador.dmgPlayer(getDano() * 2);
    }

    public void ataqueDesesperado(Jogador jogador){
		double danoTmp = getDano();
		
        System.out.println("Goblin causou " + danoTmp * 1.5 + " mas no desespero se acertou tomando 4 de dano");
        jogador.dmgPlayer(danoTmp * 1.5);
        this.tomarDano(4, jogador);
    }

    public void fuga(){
        System.out.println("TESTE DE FUGA---");
    }
}