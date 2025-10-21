public final class AdagaLadrao extends Arma {
	public AdagaLadrao() {
        super(
            "Adaga ladrão", 
            "Uma adaga que rouba dinheiro dos inimigos", 
            Raridade.RARO, 
            180.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            4.0,   // dano
            8.0,    // velocidade
            1.5,    // multiplicador crítico
			20,      // chanche de critico
			true    //Dano verdadeiro
        );
	}
	
	@Override
	public void usar(Entidade alvo, Entidade usuario){
		double dinheiroAlvo = alvo.getDinheiro();
		
		if(Sorteador.chance(40)){
			if(alvo instanceof Jogador){
				if(dinheiroAlvo > 50){
					alvo.perderDinheiro(50);
					usuario.ganharDinheiro(50);
					System.out.println(usuario.getNome() + " roubou 50 moedas de " + alvo.getNome());
				} else if (dinheiroAlvo > 0){
					alvo.perderDinheiro(dinheiroAlvo);
					usuario.ganharDinheiro(dinheiroAlvo);
					System.out.println(usuario.getNome() + " roubou " + dinheiroAlvo + " moedas de " + alvo.getNome());
				} else {
					System.out.println(alvo.getNome() + " não tinha dinheiro para roubar!");
				}
			} else {
				usuario.ganharDinheiro(50);
				System.out.println(usuario.getNome() + " roubou 50 moedas de " + alvo.getNome());
			}
		}
	}
	
}