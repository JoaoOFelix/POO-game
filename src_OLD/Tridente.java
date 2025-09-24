public final class Tridente extends Arma {
	public Tridente() {
        super(
            "Tridente", 
            "Um Tridente originado do fundo dos oceanos, que reduz a defesa dos inimigos em 50%", 
            Raridade.EPICO, 
            750.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            5.0,   // dano
            2.0,    // velocidade
            3.0,    // multiplicador crítico
			5      // chanche de critico
        );
	}
	
	@Override
	public void usar(Entidade entidade, Jogador jogador){	
	}
	
	@Override
	public void usarUnico(Entidade entidade, Jogador jogador){
		double novaDefesa;
		double novoDano;
		double defesa = entidade.getDefesa();
		double dano = entidade.getDano();
		
		
		//reduz defesa
		if(defesa >= 2){
			
			
			novaDefesa = defesa * 0.5;
			System.out.println("Nova defesa do inimigo: "+novaDefesa);
			entidade.setDefesa(novaDefesa);
		}
		
		
		System.out.println("coisas do dia a dia");		
		
		//reduz dano
		if(dano >= 2){	
			novoDano = dano * 0.5;
			System.out.println("Novo dano do inimigo: "+novoDano);
			entidade.setDano(novoDano);
		}
	}
	
}