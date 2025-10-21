import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public final class Jogador extends Entidade{
		private int xp;
        private int xpLevel;
		private Inventario<Item> inventario = new Inventario<>();
		

        
        public Jogador(String nome){
			super(
				nome,	//nome
				100,	//vidaMax
				100,	//vida
				5,		//dano
				0		//defesa
			);
			
			dinheiro = 0.0;
			xp = 0;
			xpLevel = 1;
        }
		
		public Jogador(String nome, double vida, double dano){
			super(
				nome,	//nome
				vida,	//vidaMax
				vida,	//vida
				dano,	//dano
				0		//defesa
			);
			
			dinheiro = 0.0;
			xp = 0;
			xpLevel = 1;
		}
		
	
		public double getDanoBruto(){
			return this.dano;
		}
		
		@Override
		public void atacar(Entidade entidade, Jogador jogador){
			
			if(hasArma()){
				this.getArma().usar(entidade, jogador);
			}
			
			double danoTotal = getDano();
			
			System.out.println(this.getNome() + " atacou " + entidade.getNome() + " e causou " + danoTotal + " de dano\n");
		
			entidade.tomarDano(danoTotal, this, jogador);
		}
		
		
		//REFAZER DEPOIS!!!!!!!!!!!!!!!!!!!!!!!!!!
		//classe arma deverá calcular o critico! não o player
		@Override
        public double getDano(){ 
			if(getArma() != null){
				if(Sorteador.chance(getArma().chanceCritico)){
					System.out.println("Dano crítico da arma!!");
					return getDanoBruto() + getArma().getCritico();
					
				} else {
					System.out.println("Dano com arma aplicado!");
					return getDanoBruto() + getArma().getDano();
				}
			}
			return getDanoBruto();
		}
		
		public double getXp(){
			return xp;
		}

        public double getXpLevel(){ 
			return xpLevel; 
		}
		
		
		public Inventario<Item> getInventario(){
			return this.inventario;
		}
		
        
		//-------------------------------------ACOES-------------------------------------------------------
		
		public void equiparArmadura() {
			// Pega apenas as armaduras do inventário
			List<Armadura> armaduras = inventario.getTipo(Armadura.class);

			if (armaduras.isEmpty()) {
				System.out.println("Nenhuma armadura disponível no inventário.");
				return;
			}

			// Mostra as armaduras disponíveis
			System.out.println("Armaduras disponíveis:");
			for (int i = 0; i < armaduras.size(); i++) {
				Armadura a = armaduras.get(i);
				System.out.println(i + " - " + a.getNome() 
					+ " | Raridade: " + a.getRaridade()
					+ " | Defesa: " + a.getDefesa()
					+ " | Descrição: " + a.getDescricao());
			}

			// Escolha do jogador
			System.out.print("Escolha uma armadura para equipar (número): ");
			Scanner sc = new Scanner(System.in);
			int escolha = sc.nextInt();

			if (escolha < 0 || escolha >= armaduras.size()) {
				System.out.println("Opção inválida!");
				return;
			}

			Armadura escolhida = armaduras.get(escolha);

			// Equipar
			if (this.getArmadura() != null) {
				// Remove defesa da armadura antiga
				this.defesa -= this.getArmadura().getDefesa();
			}

			this.defesa += escolhida.getDefesa();
			this.setArmadura(escolhida);
			System.out.println("Armadura " + escolhida.getNome() + " equipada!");
		}

		public void desequiparArmadura(){
			this.defesa -= this.getArmadura().getDefesa();
			this.setArmadura(null);
			System.out.println(nome + " deseequipou sua armadura.");
		}

        public void ganharXp(int xp){
            this.xp += xp;

            System.out.println("Experiencia: " + getXp() + "/100");

            if (this.xp >= 100) {
                subirNivel();
            }
        }
		
		public void subirNivel(){
			int buffVida = 5;
			int buffDano = 3;
			int buffDefesa = 4;
			
			this.xpLevel++;
            this.xp -= 100;
			
            System.out.println(getNome() + " Subiu para o level " + getXpLevel() + "!");
			
			System.out.println("Jogador ganhou +1 espaços no inventário");
			this.inventario.aumentarInventario(1);
			
			//escolha de atributo a ser melhorado
			System.out.println("\n Escolha um atributo para ser melhorado:");
			System.out.println("1 - +" + buffVida + " de vida");
			System.out.println("2 - +" + buffDano + " de dano");
			System.out.println("3 - +" + buffDefesa + " de defesa");
			
			Scanner sc = new Scanner(System.in);
			int escolha = sc.nextInt();
			
			switch(escolha){
				case 1:
					this.vidaMax += buffVida;
					break;
				
				case 2:
					this.dano += buffDano;
					break;
					
				case 3:
					this.defesa += buffDefesa;
					break;
			}
			
			addItem(new ItemDecorativo("Balão de festa", "Um balão de comemoração de um novo nível!", Raridade.COMUM));
			
			//Recupera a vida inteira e caso tenha sobrevida não recupera nada
			if(this.vida < this.vidaMax){
				//this.vida += this.vidaMax * 0.2;
				this.vida += 20;
				
				if(this.vida > this.vidaMax)
					this.vida = this.vidaMax;
			}
			
			if(this.getXp() >= 100)
				subirNivel();
		}
		
		public void usar(){
			//---
		}
		
		
		//COLOCAR NA ENTIDADE
		//===============================================================================================
		public void usarItem(){
				
		}
		
		
        //Adicionar item
        public void addItem(Item item) {			
			if(item.isEmpilhavel() && inventario.hasItem(item)){
				this.inventario.addQuantidade(item, 1);
			} else {
				inventario.adicionarItem(item);
			}
			
			System.out.println(item.getNome() + " foi adicionado ao inventário.");
		}
		
		public void venderItem(){
			List<Item> itens = inventario.getInventario();


			System.out.println("Itens disponíveis para venda:");
			for (int i = 0; i < itens.size(); i++) {
				Item a = itens.get(i);
				
				if(a == this.getArma() || a == this.getArmadura()){
					System.out.println(i + " - " + a.getNome() + " (Equipado)" + " - " + a.getPrecoVenda());
				} else if(a.isEmpilhavel()){
					System.out.println(i + " - " +  a.getQuantidade() + "x " + a.getNome() + " - " + a.getPrecoVenda());
				} else {
					System.out.println(i + " - " + a.getNome() + " - " + a.getPrecoVenda());
				}
				
			}
			System.out.print("Escolha um item para vender (número): ");
			Scanner sc = new Scanner(System.in);
			int escolha = sc.nextInt();

			if (escolha < 0 || escolha >= itens.size()) {
				System.out.println("Opção inválida!");
				return;
			}

			Item item = itens.get(escolha);


			this.ganharDinheiro(item.getPrecoVenda());
			
			if(item instanceof Arma){
				if(item == this.getArma()){
					desequiparArma();
				}
			}
			
			if(item instanceof Armadura){
				if(item == this.getArmadura()){
					desequiparArmadura();
				}
			}
			
			if(item.isEmpilhavel()){
				inventario.removeQuantidade(item, 1);
			} else {
				inventario.remover(item);
			}
			System.out.println("Item vendido");
		}
        
		@Override
        public void aoMorrer(Jogador jogador){
            System.out.println("Jogador morreu!");
        }
        
        public boolean isAlive(){
            return this.vida > 0;
        }
	
		//============================================================================ FAZER ANALISE DE CODIGO
		
		public void usarCura(){
			List<PocaoCura> pocoes = inventario.getTipo(PocaoCura.class);
			System.out.println(inventario.verPocoes());
			if(pocoes.isEmpty()){
				System.out.println("Você não tem poções");
				return;
			}
			System.out.print("Escolha uma item para usar (número): ");
			Scanner sc = new Scanner(System.in);
			int escolha = sc.nextInt();

			if (escolha < 0 || escolha >= pocoes.size()) {
				System.out.println("Opção inválida!");
				return;
			}

			PocaoCura escolhida = pocoes.get(escolha);
			
			if (escolhida instanceof PocaoCura) {
				
				(escolhida).usar(this, this);
				
				if((escolhida).getQuantidade() == 0)
					this.inventario.remover((escolhida));
	
				System.out.println(this.getVida() + "/"+ this.getVidaMax());
				
			} else {
				System.out.println("O item selecionado não é usável!");
			}	
		}
		
        public void getStatus(){
            System.out.println("===============================================");
			System.out.println("Dinheiro: " + getDinheiro());
            System.out.println("Nome: " + getNome() + " Nível " + getXpLevel());
            System.out.println("Vida " + getVida() + "/" + getVidaMax());
			System.out.println("Defesa atual: " + getDefesa());
            System.out.println("Dano bruto: " + getDanoBruto());
			if(this.getArma() != null)
				System.out.println("Dano total: " + Formata.formatar(this.getArma().getDano() + getDanoBruto()));
			if(this.getArma() != null)
				System.out.println("Dano crítico: " + Formata.formatar(getDanoBruto() + this.getArma().getCritico()));
            System.out.println("Experiência: " + getXp() + "/100");
			if(this.getArma() != null)
				System.out.println("\nArma equipada :" + this.getArma().getNome());
            System.out.println("===============================================");
		}
    
	//apagar
	public void setXpLevel(int lvl){
		this.xpLevel = lvl;
	}
}