public enum Raridade {
	COMUM("Cinza", 1.0),
    RARO("Verde", 1.5),
    EPICO("Roxo", 2.0),
    LENDARIO("Dourado", 3.0);
	
	private final String cor;
	private final double multiValor;
	
	Raridade(String cor, double multiValor){
		this.cor = cor;
		this.multiValor = multiValor;
	}
	
	public String getCor(){
		return this.cor;
	}
	
	public double getMultiValor(){
		return this.multiValor;
	}
}