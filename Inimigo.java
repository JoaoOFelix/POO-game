public abstract class Inimigo {
    protected String nome;
    protected String raca;
    protected double vida;
    protected double vidaMax;
    protected double dano;
    protected double defesa;
    protected double velocidade;
    protected int dropXp;


    public Inimigo(String nome, String raca, double vida, double dano, double defesa, double velocidade, int dropXp) {
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

    public double getDano() {return this.dano;}

    public double getVida() {return this.vida;}

    public double getVidaMax() {return this.vidaMax;}

    public int getDropXp() {return this.dropXp;}

    public double getDefesa() {return this.defesa;}

    public double getVelocidade() {return this.velocidade;}


    //---

    public abstract void enemyAi(Jogador jogador);

    public void morrer(Jogador jogador){
        jogador.ganharXp(getDropXp());
    }

    public void atacar(Jogador jogador){
        jogador.dmgPlayer(getDano());
    }

    public void tomarDano(double danoRecebido, Jogador jogador) {
        double danoFinal = Math.max(0, danoRecebido - this.defesa);
        this.vida -= danoFinal;
        System.out.println(this.nome + " tomou " + danoFinal + " de dano!");

        if (this.vida <= 0) {
            morrer(jogador);
        }
    }

    public boolean isAlive() {
        return vida > 0;
    }

    public String getStatus() {
        return "Nome: " + nome +
                "\nRaça: " + raca +
                "\nVida: " + vida + "/" + vidaMax +
                "\nDano: " + dano +
                "\nDefesa: " + defesa +
                "\nVelocidade: " + velocidade;
    }
	
}