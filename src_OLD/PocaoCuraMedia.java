public class PocaoCuraMedia extends PocaoCura{
	public PocaoCuraMedia(){
		super(
			"Poção de cura média",
			"Uma poção que cura 45 de vida.",
			Raridade.RARO,
			100, //preco de venda
			true, //empilhavel?
			1, //quantidade
			45 //quantidade de cura
		);
	}
}