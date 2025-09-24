public final class Goblin extends Inimigo{
	private static final double CHANCE_FUGA_TENTAR = 25.0;
	private static final double CHANCE_FUGA_SUCESSO = 30.0;
	private static final double CHANCE_ATAQUE_DESESPERADO = 23.0;
	private static final double CHANCE_ATAQUE_ESPECIAL = 27.0;
	private static final double CHANCE_ATAQUE_NORMAL = 92.0;
	private static final double CHANCE_DROP_EXCALIBUR = 10.0;
	private static final double CHANCE_DROP_ADAGA = 60.0;

	private int tentativasFuga = 0;
	protected static int goblinsDerrotados = 0;
	
	//reanalizar
	private boolean fuga = false;

	public Goblin(String nome){
		
		super(
			nome,      //nome
			"Goblin",  //raca
			15,        //vida
			3,        //dano
			0,		   //defesa
			3,		   //velocidade
			25          //drop de experiencia
			);
		
		this.setArma(new AdagaGoblin());
	}


	@Override
	public void dropItem(Jogador jogador){
		//15% de chance de dropar a excalibur ou 80% de dropar adaga goblin ou não dropa nada
		if(Sorteador.chance(CHANCE_DROP_EXCALIBUR)){
			System.out.println(getNome() + " dropou uma Excalibur!");
			jogador.addItem(new Excalibur());
			
		} else if(Sorteador.chance(CHANCE_DROP_ADAGA)) {
			System.out.println(getNome() + " dropou uma Adaga goblin");
			jogador.addItem(new AdagaGoblin());
			
		} else if(Sorteador.chance(80)){
			System.out.println(getNome() + " dropou uma Armadura Goblin");
			jogador.addItem(new ArmaduraGoblin());
		}
		
		//100% de chance de drop de pocao de cura
		if(Sorteador.chance(90)){
			jogador.addItem(new PocaoCuraPequena());
		} else {
			jogador.addItem(new PocaoCuraMedia());
		}
		
	}

	@Override
	public void usar(){
		//--
	}


    @Override
    public void aoMorrer(Jogador jogador) {
        System.out.println(getNome() + " grita: 'Aaargh!' e cai morto. Você ganha " + this.getDropXp() + " XP.");
        jogador.ganharXp(this.getDropXp());
		
		//Drop de itens do goblin
		dropItem(jogador);
		goblinsDerrotados++;
    }


	//INTELIGÊNCIA ARTIFICIAL DO INIMIGO
    @Override
    public void enemyAi(Jogador jogador) {

        //tem 8% de chanche de tentar fugir caso tenha menos de 50% de vidae caso não tenha tentado fugir
        if(getVida() <= (vidaMax * 0.50) && Sorteador.chance(8) && tentativasFuga == 0 && this.vida > 4){
			
                System.out.println(getNome() + " Tentou fugir...");

                //40% de chance de fuga
                if(Sorteador.chance(40)) {
                    System.out.println(getNome() + " conseguiu fugir da batalha");
                    fuga();
                } else {
                    System.out.println(getNome() + " falhou ao fugir da batalha");
                }

                tentativasFuga++;
        } else {
			//Ataque desesperado caso tenha menos que 50% da vida
			if(getVida() <= (getVidaMax() * 0.5) && Sorteador.chance(23) && getVida() > 4){

				System.out.println(getNome() + " Usou o ataque desesperado");
				ataqueDesesperado(jogador);

			} else if(getVida() <= (getVidaMax() * 0.5) && Sorteador.chance(27)) {

				ataqueEspecial(jogador);

			} else if (Sorteador.chance(92)){

				if(Sorteador.chance(75)){
					atacar(jogador);
				} else {
					ataqueRapido(jogador);
				}
				
			} else {

				System.out.println("Goblin errou o ataque!");
			}
		}

    }

    @Override
    public void atacar(Jogador jogador) {
		double danoTmp = danoCritico();
		
		if(hasArma()){
			System.out.println(getNome() + " ataca com " + arma.getNome() + "! Causando " + danoTmp + " de dano.");
		} else {
			System.out.println(getNome() + " ataca com os punhos! Causando " + danoTmp + " de dano");
		}
        jogador.dmgPlayer(danoTmp);
    }
	
	
	public static int getGoblinsDerrotados(){
		return goblinsDerrotados;
	}
	

    //Tem 35% de chance de atacar duas vezes causando o dobro de dano
    public void ataqueRapido(Jogador jogador){
		double danoTmp = danoSemCritico();
		double danoDuplo = danoTmp * 1.75;
		double danoUnico = danoTmp * 0.75;
		
		
        if(Sorteador.chance(65)){
            System.out.println("Tentou atacar rápido, e atingiu 2 vezes");
            System.out.println("Goblin causou " + danoDuplo);
            jogador.dmgPlayer(danoDuplo);
        } else {
            System.out.println("Tentou atacar rápido, mas atingiu apenas 1 vez");
            System.out.println("Goblin causou " + danoUnico);
            jogador.dmgPlayer(danoUnico);
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
		double danoTmp = danoSemCritico();
		double danoEspecial = danoTmp * 2.25;
		
        System.out.println(getRaca() + " usou seu ataque especial e causou " + (danoEspecial) + " de dano");
        jogador.dmgPlayer(danoEspecial);
    }

    public void ataqueDesesperado(Jogador jogador){
		double danoTmp = danoSemCritico();
		
        System.out.println("Goblin causou " + danoTmp * 1.5 + " mas no desespero se acertou tomando 4 de dano");
        jogador.dmgPlayer(danoTmp * 1.5);
        this.tomarDano(4, jogador);
    }

	@Override
    public void fuga(){
        this.fuga  = true;
    }
	
	@Override
	public boolean getFuga(){
		return fuga;
	}
}