private static class Jogador{
        
        private String nome;
        private double vidaMax = 100;
        private double vida;
        private double dano;
		private double experiencia
        List<String> itens = new ArrayList<>();
        
        
        
        public Jogador(String nome){
            this.nome = nome;
            this.vida = vidaMax;
			this.dano = 5;
			this.experiencia = 0;
        }
        
        
        public String getNome(){
            return nome;
        }
        
        public double getVida(){
            return vida;
        }
		
		public double getVidaMax(){
            return vidaMax;
        }
		
		public double getXp(){
			return experiencia;
		}
        
        public String getItens(){
            
            if (itens.isEmpty()) {
                return "Nenhum item no inventário.";
            }
            
            StringBuilder sb = new StringBuilder();
            for (String item : itens) {
                sb.append("- ").append(item).append("\n");
            }
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
        
        //Adicionar item
        public void addItem(String item){
            this.itens.add(item);
        }
        
        public void playerDeath(){
            System.out.println("Jogador morreu!");
        }
        
        public boolean isAlive(){
            if(this.vida > 0){
                return true;
            }
            
            return false;
        }
        
        public void getStatus(){
            System.out.println("Nome: " + getNome());
            System.out.println("Vida " + getVida() + "/" + getVidaMax());
            System.out.println("Forca: " + dano);
            System.out.println("Inventario: ");
			
            System.out.println(getItens());
        }
        
    
    }