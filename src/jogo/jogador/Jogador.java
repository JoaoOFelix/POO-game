package jogo.jogador;

import java.util.List;
import java.util.ArrayList;
import jogo.itens.Item;
import jogo.itens.Arma;

public class Jogador{        
        private String nome;
        private double vidaMax = 100;
        private double vida;
        private double dano;
		private int xp;
        private int xpLevel;
		private Arma armaEquipada = null;
        protected List<Item> inventario = new ArrayList<>();
		

        
        public Jogador(String nome){
            this.nome = nome;
            this.vida = vidaMax;
			this.dano = 5;
			this.xp = 0;
        }
		
		public Jogador(String nome, double vida, double dano){
			this.nome = nome;;
			this.vida = this.vidaMax = vida;
			this.dano = dano;
		}
        
        public String getNome(){
            return nome;
        }
        
        public double getVida(){
            return vida;
        }
		
	
		public double getDanoBruto(){
			return this.dano;
		}
		
		
		//REFAZER DEPOIS!!!!!!!!!!!!!!!!!!!!!!!!!!
		//classe arma deverá calcular o critico! não o player
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
		
		
		
		public double getVidaMax(){
            return vidaMax;
        }
		
		public double getXp(){
			return xp;
		}

        public double getXpLevel(){ return xpLevel; }
        
        public List<Item> getInventario(){
			return inventario; // retorna a lista real de itens
		}
		
		
		public String verInventario(){
			if (inventario.isEmpty()) {
				return "Nenhum item no inventário.";
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < inventario.size(); i++) {
				Item item = inventario.get(i);
    
				sb.append(i).append(" - ")
				.append(item.getNome())
				.append(" | Raridade: ").append(item.getRaridade())
				.append(" | Descrição: ").append(item.getDescricao());
    
				// Se for arma, mostrar dano também
				if (item instanceof Arma) {
					Arma arma = (Arma) item;
					sb.append(" | Dano: ").append(arma.getDano())
					.append(" | Velocidade: ").append(arma.getVelocidade())
					.append(" | Chance de critico: ").append(arma.getChanceCritico())
					.append(" | Multiplicador de dano critico: ").append(arma.getMultiplicadorCritico());
				}
    
				sb.append("\n");
			}
			
			return sb.toString();
		}
        
		//-------------------------------------ACOES-------------------------------------------------------
        
        public void dmgPlayer(double dmg){
            vida -= dmg;
            System.out.println("Jogador perdeu " + dmg + " pontos de vida!");
			System.out.println("Vida: " + getVida() + "/" + getVidaMax());

            if(!isAlive()){
                playerDeath();
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

        public void atacar(Inimigo inimigo){
			double danoTotal = getDano();
			
			System.out.println(this.getNome() + " atacou " + inimigo.getNome() + " e causou " + danoTotal + " de dano\n");
			
			
			
            inimigo.tomarDano(danoTotal, this);
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
		
        
        //Adicionar item
        public void addItem(Item item) {
			inventario.add(item);
			System.out.println(item.getNome() + " foi adicionado ao inventário.");
		}
        
        public void playerDeath(){
            System.out.println("Jogador morreu!");
        }
        
        public boolean isAlive(){
            return this.vida > 0;
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
            System.out.println(verInventario());
            System.out.println("===============================================");
        }
    
}