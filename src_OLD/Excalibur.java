public final class Excalibur extends Arma{
	
	public Excalibur() {
        super(
            "Excalibur", 
            "A lendária espada dos reis", 
            Raridade.EPICO, 
            750.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            12.0,   // dano
            3.0,    // velocidade
            1.4,    // multiplicador crítico
			20     // chanche de critico
        );
	}
	
	@Override
	public void usar(Entidade entidade, Jogador jogador){
		
		
	}
	

}