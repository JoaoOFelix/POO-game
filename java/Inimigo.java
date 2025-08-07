public abstract class Inimigo(){
		protected String nome;
		protected String raca;
		protected double vida;
		protected double dano;
		protected double defesa;
		protected double velocidade;
		protected double dropXp;
		
		public Inimigo(String nome, String raca, double vida, double dano, double defesa, double velocidade, double dropXp){
			this.nome = nome;
			this.raca = raca;
			this.vida = vida;
			this.dano = dano;
			this.defesa = defesa;
			this.velocidade = velocidade;
			this.dropXp = dropXp;
		}


		
		public abstract void atacar(){}
		
		
		public abstract void morrer(double danoRecebido){}
		
		public void tomarDano(){
			double danoFinal = Math.max(0, danoRecebido - this.defesa);
			this.vida -= danoFinal;
			System.out.println(nome + " tomou " + danoFinal + " de dano!");

			if (this.vida <= 0){
				morrer();
			}
		}
		
		public String getDescricao(){
			return nome + " (" + raca + ") - Vida: " + vida;
		}
		
		public String getNome(){
			return nome;
		}
		
		public String getRaca(){
			return raca;
		}
		
		
		
	}