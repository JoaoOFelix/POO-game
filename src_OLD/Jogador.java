import java.util.List;
import java.util.ArrayList;

public class Jogador extends Entidade{
		private int xp;
        private int xpLevel;
		private Arma armaEquipada = null;
        //protected List<Item> inventario = new ArrayList<>();
		
		//TORNAR PIRVATE E ADICIONAR OS GETTERS E OS SETTERS
		public Inventario inventario = new Inventario();

        
        public Jogador(String nome){
			super(
				nome,	//nome
				100,	//vidaMax
				100,	//vida
				5,		//dano
				0		//defesa
			);
			
			xp = 0;
			xpLevel = 0;
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
			xpLevel = 0;
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
        
		//-------------------------------------ACOES-------------------------------------------------------
        
		
		//MUDAR PARA tomarDano() e colocar override
        public void dmgPlayer(double dmg){
            vida -= dmg;
            System.out.println("Jogador perdeu " + dmg + " pontos de vida!");
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
			
			addItem(new ItemDecorativo("Balão de festa", "Um balão de comemoração de um novo nível!", Raridade.COMUM));
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


        public void getStatus(){
            System.out.println("===============================================");
            System.out.println("Nome: " + getNome() + " Nível " + getXpLevel());
            System.out.println("Vida " + getVida() + "/" + getVidaMax());
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