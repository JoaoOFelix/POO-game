public abstract class Entidade {
	protected String nome;
    protected double vidaMax;
    protected double vida;
    protected double dano;
	protected double defesa;
	protected double dinheiro;
	private Arma arma = null;
	private Armadura armadura = null;
	
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
	
	public void atacar(Entidade entidade, Jogador jogador){
		
		double danoTotal = getDano();
			
		System.out.println(this.getNome() + " atacou " + entidade.getNome() + " e causou " + danoTotal + " de dano\n");
		
        entidade.tomarDano(danoTotal, this, jogador);
	}
	
	public void tomarDano(double dano, Entidade origemDano, Jogador jogador){
		double danoFinal = dano;
		
		danoFinal = Formata.formatarDouble(calculaDefesa(danoFinal, origemDano));
		this.vida = Formata.formatarDouble(Math.max(0, this.vida - danoFinal));

		System.out.println(this.nome + " tomou " + danoFinal + " de dano!");
		System.out.println(this.getNome() + " vida: " + this.getVida() + "/" + this.getVidaMax() + "\n");
		
		if(this.vida <= 0)
			aoMorrer(jogador);
	}	
	
	public double calculaDefesa(double dano, Entidade origemDano){
		boolean danoVerdadeiro = false;
		double danoFinal = dano;
		
		if(origemDano.hasArma()){
			danoVerdadeiro = origemDano.getArma().getDanoVerdadeiro();
		}
		
		if(danoVerdadeiro){
			System.out.println(this.nome + " tomou DANO VERDADEIRO!");
			return dano;
		}
		
		
		if(this.hasArmadura()){
			this.getArmadura().habilidadeArmadura(this, origemDano, danoFinal);
			
			danoFinal = this.getArmadura().defesaArmadura(origemDano, danoFinal);
			System.out.println("Defesa da armadura aplicado");
		}
		
		return danoFinal - (danoFinal * (defesa / 100));
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
	
	public void sobreCura(double sobreCura) {
		this.vida += sobreCura;
	}
	
	//acoes
	
	public void diminuirDefesa(double qnt){
		this.defesa -= qnt;
	}
	
	public void diminuirDano(double qnt){
		this.dano -= qnt;
	}
	
	public void ganharDinheiro(double dinheiro){
		this.dinheiro += dinheiro;
	}
		
	public void perderDinheiro(double dinheiro){
		this.dinheiro -= dinheiro;
	}
	
	public void equiparArma(Arma arma){
		this.arma = arma;
		System.out.println(nome + " equipou " + arma.getNome() + "!");
	}
		
	public void desequiparArma(){
		this.arma = null;
		System.out.println(nome + " deseequipou sua arma.");
	}
	
	//getters e setters
	
	public Arma getArma(){
		return this.arma;
	}
	
	public void setArma(Arma arma){
		this.arma = arma;
	}
	
	public Armadura getArmadura(){
		return this.armadura;
	}
	
	public void setArmadura(Armadura armadura){
		this.armadura = armadura;
	}
	
	public boolean hasArmadura(){
		return this.armadura != null;
	}
	
	public boolean hasArma(){
		return this.arma != null;
	}
	
	
	public double getDinheiro(){
		return dinheiro;
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
		return Formata.formatarDouble(vida);
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
}