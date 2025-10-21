public final class ArmaduraDragao extends Armadura {
	
	public ArmaduraDragao(){
		super(
			"Armadura de escamas de Dragão", 
            "Armadura feita de escamas de dragão. Reduz o dano de dragões em 20%", 
            Raridade.EPICO, 
            370.0,   // preço venda
            false,  // empilhável
            1,      // quantidade
            25,     //duarabilidade
			25        //defesa
		);
	}
	
	
	@Override
	public double defesaArmadura(Entidade origem, double dano){
		
		if(origem instanceof Dragao){
			double novoDano = dano * 0.8;
			System.out.println("Defesa contra dragões aplicado!");
			
			return novoDano;	
		}
		return dano;
		
		
	}
}