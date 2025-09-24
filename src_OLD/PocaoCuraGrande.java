public final class PocaoCuraGrande extends PocaoCura{
	public PocaoCuraGrande(){
		super(
			"Poção de cura grande",
			"Uma poção que cura 75 de vida.",
			Raridade.EPICO,
			175, //preco de venda
			true, //empilhavel?
			1, //quantidade
			75 //quantidade de cura
		);
	}
	
}