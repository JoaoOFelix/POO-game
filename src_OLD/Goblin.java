public final class Goblin extends Inimigo{
	//status
	private static final double VIDA_GOBLIN = 25.0;
	private static final double DANO_GOBLIN = 3.0;
	private static final double DEFESA_GOBLIN = 0.0;
	private static final double VELOCIDADE_GOBLIN = 3.0;
	private static final int DROP_XP_GOBLIN = 25;
	
	//Chances
	private static final double CHANCE_FUGA_TENTAR = 25.0;
	private static final double CHANCE_FUGA_SUCESSO = 30.0;
	private static final double CHANCE_ATAQUE_DESESPERADO = 23.0;
	private static final double CHANCE_ATAQUE_ESPECIAL = 27.0;
	private static final double CHANCE_ATAQUE_NORMAL = 92.0;
	private static final double CHANCE_DROP_EXCALIBUR = 2.0;
	private static final double CHANCE_DROP_ADAGA_GOBLIN = 80.0;
	private static final double CHANCE_DROP_ADAGA_LADRAO = 40.0;
	
	
	
	private int tentativasFuga = 0;
	protected static int goblinsDerrotados = 0;
	private boolean fuga = false;

	public Goblin(String nome){
		
		super(
			nome,      //nome
			"Goblin",  //raca
			VIDA_GOBLIN,        //vida
			DANO_GOBLIN,        //dano
			DEFESA_GOBLIN,		   //defesa
			VELOCIDADE_GOBLIN,		   //velocidade
			DROP_XP_GOBLIN          //drop de experiencia
			);
		
		this.setArma(new AdagaGoblin());
		this.dinheiro = 34;
	}

	@Override
	public void dropItem(Jogador jogador){
		//2% de chance de dropar a excalibur ou 80% de dropar adaga goblin ou não dropa nada
		if(Sorteador.chance(CHANCE_DROP_EXCALIBUR)){
			System.out.print(getNome() + " dropou uma EXCALIBUR!");
			jogador.addItem(new Excalibur());
			
		} else if(Sorteador.chance(CHANCE_DROP_ADAGA_GOBLIN)) {
			System.out.print(getNome() + " dropou uma Adaga goblin");
			jogador.addItem(new AdagaGoblin());
			
		} else if(Sorteador.chance(CHANCE_DROP_ADAGA_LADRAO)){
			System.out.print(getNome() + " dropou uma Adaga ladrão");
			jogador.addItem(new AdagaLadrao());
		}
		
		
		if(Sorteador.chance(15)){
			System.out.print(getNome() + " dropou uma Armadura Goblin");
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
    protected void aoMorrer(Jogador jogador) {
        System.out.println(getNome() + " grita: 'Aaargh!' e cai morto. Você ganha " + this.getDropXp() + " XP.");
        jogador.ganharXp(this.getDropXp());
		
		//Drop de itens do goblin
		dropItem(jogador);
		dropDinheiro(jogador, this.dinheiro);
		
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
			System.out.println(getNome() + " ataca com " + getArma().getNome() + "! Causando " + danoTmp + " de dano.");
			habilidadeArma(jogador);
		} else {
			System.out.println(getNome() + " ataca com os punhos! Causando " + danoTmp + " de dano");
		}
        jogador.tomarDano(danoTmp, this, jogador);
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
            jogador.tomarDano(danoDuplo, this, jogador);
			
			habilidadeArma(jogador);
			habilidadeArma(jogador);
        } else {
            System.out.println("Tentou atacar rápido, mas atingiu apenas 1 vez");
            System.out.println("Goblin causou " + danoUnico);
            jogador.tomarDano(danoUnico, this, jogador);
			
			habilidadeArma(jogador);
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
        jogador.tomarDano(danoEspecial, this, jogador);
		
		habilidadeArma(jogador);
    }

    public void ataqueDesesperado(Jogador jogador){
		double danoTmp = danoSemCritico();
		
        System.out.println("Goblin causou " + danoTmp * 1.5 + " mas no desespero se acertou tomando 4 de dano");
        jogador.tomarDano((danoTmp * 1.5), this, jogador);
        this.tomarDano(4, this, jogador);
		
		habilidadeArma(jogador);
    }

	@Override
    public void fuga(){
        this.fuga  = true;
    }
	
	@Override
	public boolean getFuga(){
		return fuga;
	}
	
	public void habilidadeArma(Jogador jogador){
		if(hasArma()){
			this.getArma().usar(jogador, this);
			//System.out.println("Usado a habilidade da arma");
		}
	}
}