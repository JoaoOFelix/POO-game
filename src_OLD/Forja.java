import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public final class Forja {
	Jogador jogador;
	Inventario<Item> inventario;
	
	public Forja(Jogador jogador){
		this.jogador = jogador;
		this.inventario = jogador.getInventario();
		
		abrirForja();
	}
	
	private void abrirForja(){
		System.out.println("Bem vindo à Forja");
		System.out.println("Dinheiro: " + jogador.getDinheiro());
		System.out.println("======================");
			
		if(!inventario.isEmpty()){
			System.out.println("Escolha um item para forjar: ");
			//inventario.getEquipaveis();
			
			forjar();
			
		} else {
			System.out.println("Você não tem itens para forjar.");
		}
	}
	
	private void forjar(){
		List<Item> itens = inventario.getEquipaveis();

			System.out.println("Itens disponíveis para reforja:");
			for (int i = 0; i < itens.size(); i++) {
				Item a = itens.get(i);
				
				if(a == jogador.getArma() || a == jogador.getArmadura()){
					System.out.println(i + " - " + a.getNome() + " (Equipado)" + " - ");
				} else if(a.isEmpilhavel()){
					System.out.println(i + " - " +  a.getQuantidade() + "x " + a.getNome() + " - ");
				} else {
					System.out.println(i + " - " + a.getNome() + " - ");
				}	
			}
			
			System.out.print("Escolha um item para Forjar (número): ");
			Scanner sc = new Scanner(System.in);
			int escolha = sc.nextInt();

			if (escolha < 0 || escolha >= itens.size()) {
				System.out.println("Opção inválida!");
				return;
			}

			Item item = itens.get(escolha);

			if(item instanceof Arma){
				Arma arma = (Arma) item;				
				forjaArma(arma);
			}
			
			if(item instanceof Armadura){
				Armadura armadura = (Armadura) item;
				double defesaArmadura = armadura.getDefesa();
				
				defesaArmadura = defesaArmadura + 2;
				
				armadura.setDefesa(defesaArmadura);
			}
	}
	
	private void forjaArma(Arma arma){
		int precoReforja = 500;
		
		System.out.println("Escolha um atributo da arma para reforjar: ");
		System.out.println(precoReforja + " moedas");
		System.out.println("1. Dano: +2 ("+ precoReforja +" moedas)");
		System.out.println("2. Chance de critico: +5% ("+ precoReforja +" moedas)");
		System.out.println("3. Multiplicador de critico: +0.10 ("+ precoReforja +" moedas)");
		System.out.println("4. Velocidade: +2 ("+ precoReforja +" moedas)");
		System.out.print(">");
		Scanner sc = new Scanner(System.in);
		int escolha = sc.nextInt();
		
		
		if(jogador.getDinheiro() >= precoReforja){
			switch(escolha){
			case 1:
			
				double danoArma = arma.getDano();
				System.out.println(danoArma + " -> " + (danoArma + 2));
				arma.setDano(danoArma + 2);
				jogador.perderDinheiro(precoReforja);
				arma.mostrarItem();
			break;
			
			case 2:
				double chance = arma.getChanceCritico();
				System.out.println(chance + " -> " + (chance + 5));
				arma.setChanceCritico(chance + 5);
				jogador.perderDinheiro(precoReforja);
				arma.mostrarItem();
			break;
			
			case 3:
				double multi = arma.getMultiplicadorCritico();
				System.out.println(Formata.formatar(multi) + " -> " + Formata.formatar(multi + 0.1));
				arma.setMultiplicadorCritico(multi + 0.1);
				jogador.perderDinheiro(precoReforja);
				arma.mostrarItem();
			break;
			
			case 4:
				double velocidade = arma.getVelocidade();
				System.out.println(velocidade + " -> " + (velocidade + 2));
				arma.setVelocidade(velocidade + 2);
				jogador.perderDinheiro(precoReforja);
				arma.mostrarItem();
			break;
			
			default:
				System.out.println("Saiu");
			break;
			
			}	
		} else {
			System.out.println("Dinheiro Insuficiente");
		}
	}
	
	private void forjaArmadura(Armadura armadura){
		System.out.println("Escolha um atributo da arma para reforjar: ");
		System.out.println("1. Defesa");
		System.out.println("2. Durabilidade");
		
		armadura.mostrarItem();
	}
}