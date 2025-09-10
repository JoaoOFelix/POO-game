import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public final class Jogador extends Entidade{
		private int xp;
        private int xpLevel;
		private Arma armaEquipada = null;
		private Armadura armaduraEquipada = null;		
		//TORNAR PIRVATE E ADICIONAR OS GETTERS E OS SETTERS
		public Inventario inventario = new Inventario();
//		Scanner sacnner = new Scanner(System.in);

        
        public Jogador(String nome){
			super(
				nome,	//nome
				100,	//vidaMax
				100,	//vida
				5,		//dano
				0		//defesa
			);
			
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
			
			xp = 0;
			xpLevel = 1;
		}
		
	
		public double getDanoBruto(){
			return this.dano;
		}
		
		
		//REFAZER DEPOIS!!!!!!!!!!!!!!!!!!!!!!!!!!
		//classe arma deverá calcular o critico! não o player
		@Override
        public double getDano(){ 
			if(armaEquipada != null){
				if(Sorteador.chance(armaEquipada.chanceCritico)){
					System.out.println("Dano crítico da arma!!");
					return getDanoBruto() + armaEquipada.getCritico();
					
				} else {
					System.out.println("Dano com arma aplicado!");
					return getDanoBruto() + armaEquipada.getDano();
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
		
		public Arma getArmaEquipada(){
			return armaEquipada;
		}
		
		public Armadura getArmaduraEquipada(){
			return armaduraEquipada;
		}
        
		//-------------------------------------ACOES-------------------------------------------------------
        
		
		//MUDAR PARA tomarDano() e colocar override
        public void dmgPlayer(double dmg){
			double dmgTotal = dmg;
			
			
			dmgTotal = (dmg - this.defesa);
			
			if(dmgTotal < 0)
				dmgTotal = 0;

			vida -= dmgTotal;
            
            System.out.println("Jogador perdeu " + dmgTotal + " pontos de vida!");
			System.out.println("Vida: " + getVida() + "/" + getVidaMax());

            if(!isAlive()){
                aoMorrer(this);
            }
        }
		
		
		
		public void equiparArma(Arma arma){
			this.armaEquipada = arma;
			System.out.println(nome + " equipou " + arma.getNome() + "!");
		}
		
		public void desequiparArma(){
			this.armaEquipada = null;
			System.out.println(nome + " deseequipou sua arma.");
		}
		
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
			if (armaduraEquipada != null) {
				// Remove defesa da armadura antiga
				this.defesa -= armaduraEquipada.getDefesa();
			}

			this.defesa += escolhida.getDefesa();
			this.armaduraEquipada = escolhida;
			System.out.println("Armadura " + escolhida.getNome() + " equipada!");
		}

		
		
		public void desequiparArmadura(){
			this.defesa -= armaduraEquipada.getDefesa();
			this.armaduraEquipada = null;
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
			this.xpLevel++;
            this.xp -= 100;
            System.out.println(getNome() + " Subiu para o level " + getXpLevel() + "!");
			System.out.println("Jogador ganhou +1 de defesa");
			this.defesa += 1;
			System.out.println("Jogador ganhou +1 de ataque");
			this.dano += 1;
			System.out.println("Jogador ganhou +3 de vida");
			this.vida += 3;
			this.vidaMax += 3;
			
			
			
			//escolha de atributo a ser melhorado
			System.out.println("\n Escolha um atributo para ser melhorado:");
			System.out.println("1 - +3 de vida");
			System.out.println("2 - +2 de dano");
			System.out.println("3 - +1 de defesa");
			
			Scanner sc = new Scanner(System.in);
			int escolha = sc.nextInt();
			
			switch(escolha){
				case 1:
					this.vida += 3;
					this.vidaMax += 3;
					break;
				
				case 2:
					this.dano += 2;
					break;
					
				case 3:
					this.defesa += 1;
					break;
			}
			
			addItem(new ItemDecorativo("Balão de festa", "Um balão de comemoração de um novo nível!", Raridade.COMUM));
			
			//Recupera a vida inteira e caso tenha sobrevida não recupera nada
			if(this.vida < this.vidaMax){
				this.vida = this.vidaMax;
			}
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
			inventario.adicionarItem(item);
			System.out.println(item.getNome() + " foi adicionado ao inventário.");
		}
        
		@Override
        public void aoMorrer(Jogador jogador){
            System.out.println("Jogador morreu!");
        }
        
        public boolean isAlive(){
            return this.vida > 0;
        }
		
		public boolean hasArmaEquipada(){
			return this.armaEquipada != null;
		}
		
		public boolean hasArmaduraEquipada(){
			return this.armaduraEquipada != null;
		}
		
		
		//============================================================================ FAZER ANALISE DE CDIGO
		
		
		
		// ==========================
		
		
		
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
				
				System.out.println("VIDA ATUAL TESTE: " + this.getVida());
				
				if(this.getVida() < 100)
					this.inventario.remover(escolhida);
				
					(escolhida).usar(this);
					
			} else {
				System.out.println("O item selecionado não é usável!");
			}	
	}
	


        public void getStatus(){
            System.out.println("===============================================");
            System.out.println("Nome: " + getNome() + " Nível " + getXpLevel());
            System.out.println("Vida " + getVida() + "/" + getVidaMax());
			System.out.println("Defesa atual: " + getDefesa());
            System.out.println("Dano bruto: " + getDanoBruto());
			if(armaEquipada != null)
				System.out.println("Dano total: " + (armaEquipada.getDano() + getDanoBruto()));
			if(armaEquipada != null)
				System.out.println("Dano crítico: " + (getDanoBruto() + armaEquipada.getCritico()));
            System.out.println("Experiência: " + getXp() + "/100");
            System.out.println("\n--Inventario--");
            System.out.println(inventario.verInventario());
            System.out.println("===============================================");
        }
    
}