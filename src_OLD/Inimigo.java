public abstract class Inimigo {
    protected String nome;
    protected String raca;
    protected double vida;
    protected double vidaMax;
    protected double dano;
    protected double defesa;
    protected double velocidade;
    protected int dropXp;
	protected Arma arma = null;


    public Inimigo(String nome, String raca, double vida, double dano, double defesa, double velocidade, int dropXp) {
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

    public double getDanoBruto() {return this.dano;}

    public double getVida() {return this.vida;}

    public double getVidaMax() {return this.vidaMax;}

    public int getDropXp() {return this.dropXp;}

    public double getDefesa() {return this.defesa;}

    public double getVelocidade() {return this.velocidade;}
	
	public double danoTotal(){
		return getDanoBruto() + arma.ataqueCritico();
	}
	
	public double getDano(){
	
		return hasArma() ? danoTotal() : getDanoBruto();
	
		//if(hasArma()){
		//	return danoTotal();
		//}
		//return getDanoBruto();
	}


    //---

    public abstract void enemyAi(Jogador jogador);
	
	public abstract Item dropItem();

    public void morrer(Jogador jogador){
        jogador.ganharXp(getDropXp());
    }

    public void atacar(Jogador jogador){
		jogador.dmgPlayer(getDano());
	} 

    public void tomarDano(double danoRecebido, Jogador jogador) {
		double danoFinal = Math.max(0, danoRecebido - this.defesa);
		this.vida = Math.max(0, this.vida - danoFinal);

		System.out.println(this.nome + " tomou " + danoFinal + " de dano!");

		if (this.vida <= 0)
			morrer(jogador);
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
	
}