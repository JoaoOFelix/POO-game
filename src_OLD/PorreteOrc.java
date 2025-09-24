public final class PorreteOrc extends Arma {
	public PorreteOrc() {
        super(
            "Porrete Orc", 
            "Um forte porrete usado por Orcs", 
            Raridade.RARO, 
            300.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            8.0,   // dano
            1.5,    // velocidade
            2,    // multiplicador crítico
			20      // chanche de critico
        );
	}
	
	@Override
	public void usar(Entidade entidade, Jogador jogador){
		
	}
	
}