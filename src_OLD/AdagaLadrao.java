public final class AdagaLadrao extends Arma {
	public AdagaLadrao() {
        super(
            "Adaga ladrão", 
            "Uma adaga que rouba dinheiro dos inimigos", 
            Raridade.RARO, 
            260.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            5.0,   // dano
            8.0,    // velocidade
            1.8,    // multiplicador crítico
			12      // chanche de critico
        );
	}
	
	@Override
	public void usar(Entidade entidade, Jogador jogador){
		
	}
	
}