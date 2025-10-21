public final class Excalibur extends Arma{
	
	public Excalibur() {
        super(
            "Excalibur", 
            "A lendária espada dos reis", 
            Raridade.EPICO, 
            900.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            11.0,   // dano
            3.5,    // velocidade
            1.4,    // multiplicador crítico
			20,     // chanche de critico
			false   //Dano verdadeiro
        );
	}
}