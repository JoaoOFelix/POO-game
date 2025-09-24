public abstract class Inimigo extends Entidade{
    protected String raca;
    protected double velocidade;
    protected int dropXp;
	protected Arma arma = null;
	protected boolean fuga = false;

    public Inimigo(String nome, String raca, double vida, double dano, double defesa, double velocidade, int dropXp) {
		super(
			nome,		//nome
			vida,	//vidamax
			vida,		//vida
			dano,		//dano
			defesa		//defesa
		);
		
		
        this.nome = nome;
        this.raca = raca;
        this.vida = this.vidaMax = vida;
        this.dano = dano;
        this.defesa = defesa;
        this.velocidade = velocidade;
        this.dropXp = dropXp;
    }
	
	
	public void setArma(Arma arma){
		this.arma = arma;
	}

    public String getDescricao() {
        return this.nome + " (" + this.raca + ") - Vida: " + this.vida + "/" + this.vidaMax;
    }

    public String getNome() {return this.nome;}

    public String getRaca() {return this.raca;}

    public double getVida() {return this.vida;}

    public double getVidaMax() {return this.vidaMax;}

    public int getDropXp() {return this.dropXp;}

    public double getDefesa() {return this.defesa;}

    public double getVelocidade() {return this.velocidade;}
	
	public double getDano(){return this.dano;}
	
	
	// ==
	
	
	public double danoSemCritico(){
		
		if(hasArma()){
			System.out.println("DANO SEM CRITICO COM ARMA APLICADO");
			return getDano() + arma.getDano();
		}
		
		
		return getDano();
	}
	
	public double danoCritico(){
		if(hasArma()){
			return arma.ataqueCritico();
		}
		
		return getDano();
	}
	
	public double danoCriticoTotal(){
		return getDano() + arma.getCritico();
	}


    //---

    public abstract void enemyAi(Jogador jogador);
	
	public abstract void dropItem(Jogador jogador);

    public void atacar(Jogador jogador){
		jogador.dmgPlayer(danoCritico());
	} 
	
	

    public void tomarDano(double danoRecebido, Jogador jogador) {
		double danoFinal = Math.max(0, danoRecebido - this.defesa);
		this.vida = Math.max(0, this.vida - danoFinal);

		System.out.println(this.nome + " tomou " + danoFinal + " de dano!");

		if (this.vida <= 0)
			aoMorrer(jogador);
	}


    public boolean isAlive() {
        return vida > 0;
    }
	
	public boolean hasArma(){
		return arma != null;
	}

    public String getStatus() {
		return "Nome: " + nome +
           "\nRaça: " + raca +
           "\nVida: " + vida + "/" + vidaMax +
           "\nDano: " + dano +
           (this.hasArma() 
               ? "\nDano de " + arma.getNome() + ": " + arma.getDano() 
               : "") +
           "\nDefesa: " + defesa +
           "\nVelocidade: " + velocidade;
    }
	
	public void fuga(){
        System.out.println("TESTE DE FUGA---");
		this.fuga  = true;
    }
	
	public boolean getFuga(){
		return fuga;
	}
	
}