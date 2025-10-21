public final class DragaoVermelho extends Dragao {
	public DragaoVermelho(String nome){
		super(
			nome,
			"Dragão Vermelho",  //raca
			240,     //vida
			25,		//dano
			20,		//defesa
			4,		//velocidade
			275,		// dropXP
			1200       //dinheiro
		);
	}
	
	
	@Override
	public void dropItem(Jogador jogador){
		jogador.addItem(new EspadaDeOssos());
	}	
}