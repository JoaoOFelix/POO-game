import java.util.List;
import java.util.ArrayList;

public class Jogador{
        
        private String nome;
        private double vidaMax = 100;
        private double vida;
        private double dano;
		private int xp;
        private int xpLevel;
        List<String> itens = new ArrayList<>();

        
        public Jogador(String nome){
            this.nome = nome;
            this.vida = vidaMax;
			this.dano = 5;
			this.xp = 0;
        }
        
        
        public String getNome(){
            return nome;
        }
        
        public double getVida(){
            return vida;
        }

        public double getDano(){ return dano; }
		
		public double getVidaMax(){
            return vidaMax;
        }
		
		public double getXp(){
			return xp;
		}

        public double getXpLevel(){ return xpLevel; }
        
        public String getItens(){


            if (itens.isEmpty()) {
                return "Nenhum item no inventário.";
            }
            
            StringBuilder sb = new StringBuilder();

            for (String item : itens) {
                sb.append("- ").append(item).append("\n");
            }

            System.out.println("===============================================");
            return sb.toString();
        }
        
        
        public void dmgPlayer(double dmg){
            vida -= dmg;
            System.out.println("Jogador perdeu " + dmg + " pontos de vida!");
			System.out.println("Vida: " + getVida() + "/" + getVidaMax());

            if(!isAlive()){
                playerDeath();
            }
        }

        public void atacar(Inimigo inimigo){
            inimigo.tomarDano(getDano(), this);
        }

        public void ganharXp(int xp){
            this.xp += xp;

            System.out.println("Experiencia: " + getXp() + "/100");

            if (this.xp >= 100) {
                xpLevel++;
                this.xp = 0;
                System.out.println(getNome() + " Subiu para o level " + getXpLevel() + "!");
            }

        }
        
        //Adicionar item
        public void addItem(String item){
            this.itens.add(item);
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
            System.out.println("Forca: " + getDano());
            System.out.println("Experiência: " + getXp() + "/100");
            System.out.println("\n--Inventario--");
            System.out.println(getItens());
            System.out.println("===============================================");
        }
    
}