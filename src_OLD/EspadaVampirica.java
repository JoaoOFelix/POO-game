public final class EspadaVampirica extends Arma{
	
	public EspadaVampirica() {
        super(
            "Espada Vampirica", 
            "Uma espada que cura 3 ou 5 de vida por ataque!", 
            Raridade.LENDARIO, 
            850.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            10.0,   // dano
            8.0,    // velocidade
            2.0,    // multiplicador crítico
			15      // chanche de critico
        );
	}
	
	
	@Override
	public void usar(Entidade entidade, Jogador jogador){
		if(Sorteador.chance(70)){
			jogador.curar(3);
		} else {
			jogador.curar(5);
		}
	}
	
}