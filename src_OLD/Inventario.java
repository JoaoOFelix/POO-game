import java.util.List;
import java.util.ArrayList;

public class Inventario{
	private List<Item> itens;
	
	
	public Inventario() {
        this.itens = new ArrayList<>();
    }

	
	public void adicionarItem(Item item){
		itens.add(item);
	}
	
	public void remover(Item item){
		itens.remove(item);
	}
	
	public void listar(){
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio.");
        } else {
            System.out.println("Itens no inventário:");
            for (Item i : itens) {
                System.out.println("- " + i);
            }
        }
	}
	
	
	public List<Item> getInventario(){
		return itens; // retorna a lista real de itens
	}
		
		
	public String verInventario(){
		if (itens.isEmpty()) {
			return "Nenhum item no inventário.";
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < itens.size(); i++) {
			Item item = itens.get(i);
    
			sb.append(i).append(" - ")
			.append(item.getNome())
			.append(" | Raridade: ").append(item.getRaridade())
			.append(" | Descrição: ").append(item.getDescricao());
    
			// Se for arma, mostrar dano também
			if (item instanceof Arma) {
				Arma arma = (Arma) item;
				sb.append(" | Dano: ").append(arma.getDano())
				.append(" | Velocidade: ").append(arma.getVelocidade())
				.append(" | Chance de critico: ").append(arma.getChanceCritico())
				.append(" | Multiplicador de dano critico: ").append(arma.getMultiplicadorCritico());
			}
    
			sb.append("\n");
		}
			
		return sb.toString();
		
	}
}