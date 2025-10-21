public final class EspadaDeOssos extends Arma {
	public EspadaDeOssos() {
        super(
            "Espada de ossos de dragão", 
            "A espada criada com ossos des dragoes, que ignora a defesa do alvo!", 
            Raridade.LENDARIO, 
            2550.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            17.0,   // dano
            1.1,    // velocidade
            1.2,    // multiplicador crítico
			10,      // chanche de critico
			true //Dano verdadeiro
        );
	}
	
	@Override
	public void usar(Entidade alvo, Entidade usuario){
		
	}
	
	@Override
	public void usarUnico(Entidade alvo, Entidade usuario){
		
	}
	
	

}