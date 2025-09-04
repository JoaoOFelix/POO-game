import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class program {
	
    public static void main(String args[]) {
		new program().iniciarJogo();
	}
  
	private Scanner scanner = new Scanner(System.in);
    private Jogador jogador;
    private Inimigo goblin;
  
  
	private void iniciarJogo(){
		iniciarObjetos();
        exibirMensagemBoasVindas();
        loopMenu();
        scanner.close();
	}
  
	private void exibirMensagemBoasVindas() {
        System.out.println("\n \n Bem-vindo " + jogador.getNome() + "!");
    }
  
  
  private void iniciarObjetos(){
	  System.out.print("Escreva o nome do jogador: ");
	  jogador = new Jogador(scanner.nextLine());
  }
  
  
  private void loopMenu(){
	  int opcao;
        do {
            exibirMenu();
            opcao = lerOpcaoMenu();
            processarOpcao(opcao);
        } while (opcao != 0 && jogador.isAlive());
	  
  }
  
  
  private void exibirMenu(){
		System.out.println("\nVida: " + jogador.getVida() + "/" + jogador.getVidaMax());		
		
		if (jogador.hasArmaEquipada()){
			System.out.println("Arma equipada: " + jogador.getArmaEquipada().getNome());
		} else {
			System.out.println("Arma equipada: Nenhuma");
		}
		
		System.out.println("===== MENU =====");
        System.out.println("1. Ver status");
        System.out.println("2. Ver inventário");
        System.out.println("3. Tomar dano");
        System.out.println("4. Adicionar item");
        System.out.println("5. Iniciar combate");
		System.out.println("6. Equipar arma");
        System.out.println("0. Sair");
        System.out.print("> ");
  }
  
  
  private int lerOpcaoMenu() {
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            scanner.next();
        }
        int opcao = scanner.nextInt();
        scanner.nextLine(); // limpar buffer
        return opcao;
    }
	
	
	private void processarOpcao(int opcao) {
			switch (opcao) {
		case 1:
			jogador.getStatus();
			break;
		case 2:
			verInventario();
			break;
		case 3:
			tomarDano();
			break;
		case 4:
			adicionarItem();
			break;
		case 5:
			iniciarCombate();
			break;
		case 6:
			this.equiparArma();
			break;
		case 7:
			this.usarCura();
			break;
		case 8:
			this.usarItem();
			break;
		case 0:
			System.out.println("Saindo do jogo...");
			break;
		default:
			System.out.println("Opção inválida!");
			break;
}
    }
	
	
	//APAGAR 
	private void usarCura(){
		jogador.addItem(new PocaoCuraPequena());
		jogador.addItem(new PocaoCuraMedia());
		jogador.addItem(new PocaoCuraGrande());
		
		
	}
	
	
	
	
	
	private void verInventario() {
        System.out.println("Inventário:");
        System.out.println(jogador.inventario.verInventario());
    }

    private void tomarDano() {
        System.out.print("Quanto de dano o jogador vai tomar? ");
        double dano = scanner.nextDouble();
        scanner.nextLine();
        jogador.dmgPlayer(dano);
    }

    private void adicionarItem() {
        System.out.print("Digite o nome da arma: ");
        String nomeArma = scanner.nextLine();
        System.out.print("Digite o dano da arma: ");
        int danoArma = scanner.nextInt();
		System.out.print("Digite a chance de critico: ");
        int chanceCritico = scanner.nextInt();
        scanner.nextLine();
		
		//String nome, String descricao, Raridade raridade, double dano, double velocidade, double multiCritico, double chanceCritico
        Arma arma = new Arma(nomeArma, "", Raridade.EPICO, danoArma, 3.0, 1.2, chanceCritico);
        jogador.addItem(arma);
		
		
		
		//APAGAR
		equiparArma();
		
    }

    private void iniciarCombate() {
        new Combate(jogador);
    }
	
	
	//colocar no jogador =====================================================================================
	private void usarItem(){
		verInventario();

		System.out.print("Escolha uma item para usar (número): ");
		Scanner sc = new Scanner(System.in);
		int escolha = sc.nextInt();

		if (escolha < 0 || escolha >= jogador.inventario.getInventario().size()) {
			System.out.println("Opção inválida!");
			return;
		}

		Item itemEscolhido = jogador.inventario.getInventario().get(escolha);
		
		if (itemEscolhido instanceof ItemUsavel) {
			
			
			((ItemUsavel) itemEscolhido).usar(jogador);
			
			
			jogador.inventario.remover((ItemUsavel) itemEscolhido);
			
			
		} else {
			System.out.println("O item selecionado não é usável!");
		}
	}
	
	
	
	private void equiparArma() {
		verInventario();

		System.out.print("Escolha uma arma para equipar (número): ");
		Scanner sc = new Scanner(System.in);
		int escolha = sc.nextInt();

		if (escolha < 0 || escolha >= jogador.inventario.getInventario().size()) {
			System.out.println("Opção inválida!");
			return;
		}

		Item itemEscolhido = jogador.inventario.getInventario().get(escolha);

		if (itemEscolhido instanceof Arma) {
			jogador.equiparArma((Arma) itemEscolhido);
			System.out.println("Você equipou: " + itemEscolhido.getNome());
		} else {
			System.out.println("O item selecionado não é uma arma!");
		}
	}
}