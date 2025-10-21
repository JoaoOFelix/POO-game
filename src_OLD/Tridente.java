public final class Tridente extends Arma {
	
	public Tridente() {
        super(
            "Tridente", 
            "Um Tridente originado do fundo dos oceanos, que reduz a defesa e o dano dos inimigos em 50%", 
            Raridade.EPICO, 
            900.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            6.0,   // dano
            2.0,    // velocidade
            4.0,    // multiplicador crítico
			10,      // chanche de critico
			false   //Dano verdadeiro
        );
	}
	
	@Override
	public void usar(Entidade alvo, Entidade usuario){	
	}
	
	@Override
	public void usarUnico(Entidade alvo, Entidade usuario){
		double novaDefesa = 0;
		double novoDano = 0;
		double defesaInimigo = alvo.getDefesa();
		double danoInimigo = alvo.getDano();
		double danoTotalFinal = 0;
		
		
		
		if(alvo instanceof Inimigo){
			Inimigo inimigo = (Inimigo) alvo;
			
			if(inimigo.hasArma()){
			
				
				double danoArma = inimigo.getArma().getDano();
				
				inimigo.getArma().setDano(danoArma * 0.5);
				danoTotalFinal += inimigo.getArma().getDano();
			}
		}
		

		//reduz defesa
		if(defesaInimigo >= 2){
			novaDefesa = defesaInimigo * 0.5;
			System.out.println("Nova defesa do inimigo: " + novaDefesa);
			alvo.setDefesa(novaDefesa);
		}
		
		//reduz dano
		if(danoInimigo >= 2){	
			novoDano = danoInimigo * 0.5;
			
			alvo.setDano(novoDano);
			danoTotalFinal += novoDano;
		}
			
		System.out.println("O tridente diminuiu o dano total do inimigo. Dano total do Inimigo: " + danoTotalFinal);
	}
}