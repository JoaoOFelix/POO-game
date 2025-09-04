public abstract class Entidade {
	protected String nome;
    protected double vidaMax;
    protected double vida;
    protected double dano;
	protected double defesa;
	
	public Entidade(String nome, double vidaMax, double vida, double dano, double defesa){
		this.nome = nome;
		this.vidaMax = vidaMax;
		this.vida = vida;
		this.dano = dano;
		this.defesa = defesa;
	}
	
	public abstract void usar();
	
	protected abstract void aoMorrer(Jogador jogador);
	
	private void morrer(Jogador jogador) {
		System.out.println(this.nome + " morreu!");
		aoMorrer(jogador); // cada filho implementa
	}
	
	
	public void tomarDano(double dano, Jogador jogador){
		double danoFinal = Math.max(0, dano - this.defesa);
		this.vida = Math.max(0, this.vida - danoFinal);

		System.out.println(this.nome + " tomou " + danoFinal + " de dano!");
		
		if(this.vida <= 0)
			aoMorrer(jogador);
	}
	
	public void curar(double cura){
		this.vida += cura;
	}
	
	public void atacar(Entidade entidade, Jogador jogador){
		double danoTotal = getDano();
			
		System.out.println(this.getNome() + " atacou " + entidade.getNome() + " e causou " + danoTotal + " de dano\n");
		
        entidade.tomarDano(danoTotal, jogador);
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getVidaMax() {
		return vidaMax;
	}

	public void setVidaMax(double vidaMax) {
		this.vidaMax = vidaMax;
	}

	public double getVida() {
		return vida;
	}

	public void setVida(double vida) {
		this.vida = vida;
	}

	public double getDano() {
		return dano;
	}

	public void setDano(double dano) {
		this.dano = dano;
	}

	public double getDefesa() {
		return defesa;
	}

	public void setDefesa(double defesa) {
		this.defesa = defesa;
	}
}