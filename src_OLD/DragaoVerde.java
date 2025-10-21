public final class DragaoVerde extends Dragao {
	public DragaoVerde(String nome){
		super(
			nome,
			"Dragão Verde",  //raca
			170,     //vida
			20,		//dano
			10,		//defesa
			10,		//velocidade
			150,		// dropXP
			690       //dinheiro
		);
		
		
	}
	
	@Override
	public void dropItem(Jogador jogador){
		jogador.addItem(new ArmaduraDragao());
	}	
	
	
}