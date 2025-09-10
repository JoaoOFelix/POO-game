public final class PocaoCuraPequena extends PocaoCura{
	public PocaoCuraPequena(){
		super(
			"Poção de cura pequena",
			"Uma poção que cura 15 de vida.",
			Raridade.COMUM,
			75, //preco de venda
			true, //empilhavel?
			1, //quantidade
			15 //quantidade de cura
		);
	}
}