public final class PorreteOrc extends Arma {
	public PorreteOrc() {
        super(
            "Porrete Orc", 
            "Um forte porrete usado por Orcs", 
            Raridade.RARO, 
            500.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            8.0,   // dano
            1.5,    // velocidade
            2.0,    // multiplicador crítico
			20,      // chanche de critico
			false   //Dano verdadeiro
        );
	}	
}