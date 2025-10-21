public abstract class Inimigo extends Entidade{
    protected String raca;
    protected double velocidade;
    protected int dropXp;
	protected boolean fuga = false;

    public Inimigo(String nome, String raca, double vida, double dano, double defesa, double velocidade, int dropXp) {
		super(
			nome,		//nome
			vida,		//vidamax
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
			return getDano() + getArma().getDano();
		}
			
		return getDano();
	}
	
	public double danoCritico(){
		if(hasArma()){
			return getArma().ataqueCritico();
		}
		
		return getDano();
	}
	
	public double danoCriticoTotal(){
		return getDano() + getArma().getCritico();
	}


    //---

    public abstract void enemyAi(Jogador jogador);
	
	public abstract void dropItem(Jogador jogador);

    public void atacar(Jogador jogador){
		jogador.tomarDano(danoCritico(), this, jogador);
	} 
	
	public void dropDinheiro(Jogador jogador, double qnt){
		jogador.ganharDinheiro(qnt);
		System.out.println(jogador.getNome() + " ganhou " + qnt + " moedas!");
	}
	
	//@Override
    //public void tomarDano(double danoRecebido, Jogador jogador) {
	//	double danoFinal = Math.max(0, danoRecebido - this.defesa);
	//	this.vida = Math.max(0, this.vida - danoFinal);
	//	System.out.println(this.nome + " tomou " + danoFinal + " de dano!");

	//	if (this.vida <= 0)
	//		aoMorrer(jogador);
	//}


    public boolean isAlive() {
        return vida > 0;
    }

    public String getStatus() {
		return "Nome: " + nome +
           "\nRaça: " + raca +
           "\nVida: " + getVida() + "/" + vidaMax +
           "\nDano: " + dano +
           (this.hasArma() 
               ? "\nDano de " + getArma().getNome() + ": " + getArma().getDano() 
               : "") +
           "\nDefesa: " + defesa +
           "\nVelocidade: " + velocidade;
    }
	
	public void fuga(){
		this.fuga  = true;
    }
	
	public boolean getFuga(){
		return fuga;
	}
	
}