import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Inventario<T extends Item>{
	private List<T> itens;
	private int slotsInventario;
	
	public Inventario() {
        this.itens = new ArrayList<>();
		slotsInventario = 8;
    }

	
	public void adicionarItem(T item){
		if(itens.size() < slotsInventario){
			itens.add(item);
		} else {
			System.out.println("Inventário está cheio!");
		}	
	}
	
	public void aumentarInventario(int quantidade){
		slotsInventario += quantidade;
	}
	
	public void remover(T item){
		itens.remove(item);
	}
	
	public boolean isEmpty(){
		return itens.isEmpty();
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
	
	public List<T> getInventario(){
		return itens; // retorna a lista real de itens
	}
		
		
	public String verInventario(Jogador jogador){
		System.out.println("Slots " +  itens.size() + "/" + slotsInventario);
		if (itens.isEmpty()) {
			return "Nenhum item no inventário.";
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < itens.size(); i++) {
			
			Item item = itens.get(i);
    
			
			sb.append(i).append(" - ");
			if(item.isEmpilhavel())
				sb.append(item.getQuantidade() + "x ");
			sb.append(item.getNome() + " - " + item.getRaridade());
			if(item == jogador.getArma() || item == jogador.getArmadura()){
				sb.append(" (Equipado)");
			}
			sb.append("\n| Descrição: ").append(item.getDescricao());
    
			// Se for arma, mostrar dano também
			if (item instanceof Arma) {
				Arma arma = (Arma) item;
				sb.append("\n| Dano: ").append(arma.getDano())
				.append("\n| Velocidade: ").append(arma.getVelocidade())
				.append("\n| Chance de critico: ").append(Formata.formatar(arma.getChanceCritico())).append("%")
				.append("\n| Multiplicador de dano critico: ").append(Formata.formatar(arma.getMultiplicadorCritico()));
			} else if(item instanceof Armadura){
				Armadura armadura = (Armadura) item;
				sb.append("\n| Defesa: ").append(armadura.getDefesa())
				.append("\n| Durabilidade: ").append(armadura.getDurabilidade());
			}
    
			sb.append("\n");
		}
			
		return sb.toString();
		
	}
	
	
	public String verArmas() {
		if (itens.isEmpty()) {
			return "Nenhum item no inventário.";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < itens.size(); i++) {
			Item item = itens.get(i);

			// Só processa se for Arma
			if (item instanceof Arma) {
				Arma arma = (Arma) item; // cast tradicional

				sb.append(i).append(" - ")
				.append(arma.getNome())
				.append(" | Raridade: ").append(arma.getRaridade())
				.append(" | Descrição: ").append(arma.getDescricao())
				.append(" | Dano: ").append(arma.getDano())
				.append(" | Velocidade: ").append(arma.getVelocidade())
				.append(" | Chance de critico: ").append(Formata.formatar(arma.getChanceCritico()))
				.append(" | Multiplicador de dano critico: ").append(Formata.formatar(arma.getMultiplicadorCritico()))
				.append("\n");
			}	
		}

		return sb.toString();
	}

	public String verArmaduras(){
		if (itens.isEmpty()) {
			return "Nenhum item no inventário.";
		}

		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < itens.size(); i++) {
			Item item = itens.get(i);
			
			if(item instanceof Armadura){
				Armadura armadura = (Armadura) item;
    
				sb.append(i).append(" - ")
				.append(item.getNome())
				.append(" | Raridade: ").append(armadura.getRaridade())
				.append(" | Descrição: ").append(armadura.getDescricao())
				.append(" | Durabilidade: ").append(armadura.getDurabilidade())
				.append(" | Defesa: ").append(armadura.getDefesa());
			}

    
			sb.append("\n");
		}
			
		return sb.toString();
		
	}
	
	public String verPocoes() {
		// Filtra apenas as poções
		List<PocaoCura> pocoes = getTipo(PocaoCura.class);

		if (pocoes.isEmpty()) {
			return "Nenhuma poção no inventário.";
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pocoes.size(); i++) {
			PocaoCura p = pocoes.get(i);
			sb.append(i).append(" - ")
			  .append(p.getQuantidade()).append("x ")
			  .append(p.getNome())
			  .append(" | Raridade: ").append(p.getRaridade())
			  .append(" | Descrição: ").append(p.getDescricao())
			  .append(" | Cura: ").append(p.getCura())
			  .append("\n");
		}

		return sb.toString();
	}

	
	
	public <U extends Item> List<U> getTipo(Class<U> clazz) {
		List<U> lista = new ArrayList<>();
		for (T item : itens) {
			if (clazz.isInstance(item)) {
				lista.add(clazz.cast(item));
			}
		}
		return lista;
	}
	
	public List<Item> getEquipaveis(){
		List<Item> itens = new ArrayList<>();
		itens.addAll(getTipo(Arma.class));
		itens.addAll(getTipo(Armadura.class));
		
		/*
		for(Item item : itens){
			System.out.println(item.getNome());
		} */
		
		return itens;
	}
	
	public boolean hasItem(Item item){
		for(Item i : itens){			
			if(item.getClass() == i.getClass()){
				return true;
			}
		}
		return false;
	}
	
	public void addQuantidade(Item item, int qtd){
		if(item.isEmpilhavel() && this.hasItem(item)){
			for(Item i : itens){			
				if(item.getClass() == i.getClass()){
					i.addQuantidade(qtd);
				}
			}
		}
	}
	
	public void removeQuantidade(Item item, int qtd){
		if(item.isEmpilhavel() && this.hasItem(item)){
			T itemRemovido = null;
			
			for(T i : itens){			
				if(item.getClass() == i.getClass()){
					if(i.getQuantidade() <= 1){
						//remoção apos o foreach para evitar erro
						//this.remover(i);
						itemRemovido = i;
					} else {
						i.removeQuantidade(qtd);
					}
					break;
				}
			}
			
			if(item != null)
				this.remover(itemRemovido);
		}
	}
	
	
}