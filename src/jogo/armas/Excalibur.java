package jogo.armas;

public class Excalibur extends Arma{
	private double velocidade;
	private Raridade raridade;
	
	public Excalibur() {
        super(
            "Excalibur", 
            "A lendária espada dos reis", 
            Raridade.EPICO, 
            1000.0, // preço venda
            false,  // empilhável
            1,      // quantidade
            11.0,   // dano
            3.0,    // velocidade
            1.5,    // multiplicador crítico
			25      // chanche de critico
        );
	}
	

}