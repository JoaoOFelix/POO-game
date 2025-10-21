public final class EspadaVampirica extends Arma{
	
	public EspadaVampirica() {
        super(
            "Espada Vampirica", 
            "Uma espada que cura 8 ou 15 de vida por ataque!", 
            Raridade.LENDARIO, 
            1600.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            8.0,   // dano
            5.0,    // velocidade
            2.0,    // multiplicador crítico
			15,      // chanche de critico
			false   //Dano verdadeiro
        );
	}
	
	
	@Override
	public void usar(Entidade alvo, Entidade usuario){
		if(Sorteador.chance(90)){
			usuario.curar(8);
		} else {
			usuario.curar(15);
		}
	}
	
}