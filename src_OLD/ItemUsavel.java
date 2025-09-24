public abstract class ItemUsavel extends Item {

	public ItemUsavel(String nome, String descricao, Raridade raridade, double precoVenda, boolean empilhavel, int quantidade){
		super(
			nome,        //nome do item
			descricao,   //descricao do item
			raridade,  //raridade do item
			precoVenda,  //preco de venda unitario
			empilhavel, //se é empilhavel ou nao
			quantidade      //Quantidade de itens
		);
	}
	
	
	public abstract void usar(Entidade entidade, Jogador jogador);
	
	public abstract void usarUnico(Entidade entidade, Jogador jogador);

}