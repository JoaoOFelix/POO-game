public abstract class Item {
	private final String nome;
	private final String descricao;
	private Raridade raridade;
	private double precoVenda;
	private boolean empilhavel;
	private int quantidade;	
	
	public Item(String nome, String descricao, Raridade raridade, double precoVenda, boolean empilhavel, int quantidade){
		this.nome = nome;
		this.descricao = descricao;
		this.raridade = raridade;
		this.precoVenda = precoVenda;
		this.empilhavel = empilhavel;
		this.quantidade = quantidade;
	}
	
	public Item(String nome, String descricao, Raridade raridade){
		this.nome = nome;
		this.descricao = descricao;
		this.raridade = raridade;
		this.precoVenda = 0.0;
		this.empilhavel = true;
		this.quantidade = 1;
	}	
	 
	public String getNome(){
		return this.nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public Raridade getRaridade() {
		return raridade;
	}
	
	public double getPrecoVenda() {
		return precoVenda;
	}
	
	public int getQuantidade(){
		return quantidade;
	}
	
	public void addQuantidade(int qtd){
		if(this.isEmpilhavel()){
			quantidade += qtd;
		}
	}
	
	public void removeQuantidade(int qtd){
		if(this.isEmpilhavel()){
			quantidade -= qtd;
		}
	}
	
	public boolean isEmpilhavel(){
		return empilhavel;
	}
	
	@Override
	public String toString() {
		return nome + " (" + raridade + ")";
	}
	
	public void mostrarItem(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" - ");
			if(isEmpilhavel())
				sb.append(quantidade + "x ");
			sb.append(this.getNome() + " - " + this.getRaridade())
			.append("\n | Descrição: ").append(this.getDescricao());
    
			// Se for arma, mostrar dano também
			if (this instanceof Arma) {
				Arma arma = (Arma) this;
				sb.append("\n | Dano: ").append(arma.getDano())
				.append("\n | Velocidade: ").append(arma.getVelocidade())
				.append("\n | Chance de critico: ").append(arma.getChanceCritico())
				.append("\n | Multiplicador de dano critico: ").append(Formata.formatar(arma.getMultiplicadorCritico()));
			} else if (this instanceof Armadura){
				Armadura armadura = (Armadura) this;
				sb.append("\n | Defesa: ").append(armadura.getDefesa())
				.append("\n | Durabilidade: ").append(armadura.getDurabilidade());
			}
			
    
			sb.append("\n");
			
			
			
			System.out.println(sb.toString());
	}
		
}