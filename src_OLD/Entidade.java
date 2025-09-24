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
		//double danoFinal = Math.max(0, dano - this.defesa);
		double danoFinal = calculaDefesa(dano);
		this.vida = Math.max(0, this.vida - danoFinal);

		System.out.println(this.nome + " tomou " + danoFinal + " de dano!");
		
		if(this.vida <= 0)
			aoMorrer(jogador);
	}
	
	public void curar(double cura){
		double vidaAntiga = this.vida;
		
		if(vidaAntiga < vidaMax){
			this.vida += cura;
			if(this.vida > this.vidaMax){
				this.vida = this.vidaMax;
			}	

			System.out.println(this.nome + " curou " + (this.vida - vidaAntiga) + " de vida.");
		} else {
			System.out.println(this.nome + " já está com vida máxima");
		}
	}
	
	public void sobreCura() {
		
	}
	
	public void atacar(Entidade entidade, Jogador jogador){
		
		double danoTotal = getDano();
			
		System.out.println(this.getNome() + " atacou " + entidade.getNome() + " e causou " + danoTotal + " de dano\n");
		
        entidade.tomarDano(danoTotal, jogador);
	}
	
	
	public double calculaDefesa(double dano){
		//log
		System.out.println(dano - (dano * (defesa / 100)));
		return dano - (dano * (defesa / 100));
	}
	
	
	
	
	//getters e setters
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
		if(defesa < 0)
			defesa = 0;
		this.defesa = defesa;
	}
	
	
	
	
	
	public void diminuirDefesa(double qnt){
		this.defesa -= qnt;
	}
	
	public void diminuirDano(double qnt){
		this.dano -= qnt;
	}
}