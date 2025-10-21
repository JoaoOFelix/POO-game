import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class program {
	
	private Scanner scanner = new Scanner(System.in);
    private Jogador jogador;
    private Inimigo goblin;
	
    public static void main(String args[]) {
		new program().iniciarJogo();
	}
  
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
		System.out.println("\nDinheiro: " + jogador.getDinheiro());		
		
		if (jogador.hasArma()){
			System.out.println("Arma equipada: " + jogador.getArma().getNome());
		} else {
			System.out.println("Arma equipada: Nenhuma");
		}

		if (jogador.hasArmadura()){
			System.out.println("Armadura equipada: " + jogador.getArmadura().getNome());
		} else {
			System.out.println("Armadura equipada: Nenhuma");
		}

		
		System.out.println("===== MENU =====");
        System.out.println("1. Ver status");
        System.out.println("2. Ver inventário");
        System.out.println("3. Tomar dano");
        System.out.println("4. Adicionar item");
        System.out.println("5. Iniciar combate");
		System.out.println("6. Equipar arma");
		System.out.println("7. Equipar armadura");
		System.out.println("8. Usar cura");
		System.out.println("9. Vender Item");
		System.out.println("10. Loja");
		if(jogador.getXpLevel() >= 3)
			System.out.println("11. Forja");
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
			this.equiparArmadura();
			break;
		case 8:
			this.usarCura();
			break;	
		case 9:
			jogador.venderItem();
			break;
		case 10:
			abrirLoja();	
			break;
		case 11:
			if(jogador.getXpLevel() >= 3)
				abrirForja();
			break;
		case 12:			
			//jogador.addItem(new BigBertha());
			jogador.addItem(new AdagaLadrao());
			jogador.addItem(new PorreteOrc());
			//jogador.addItem(new PocaoCuraPequena());
			//jogador.addItem(new PocaoCuraMedia());
			//jogador.addItem(new PocaoCuraGrande());
			//jogador.addItem(new Tridente());
			//jogador.addItem(new ArmaduraDragao());
			jogador.setXpLevel(3);
			//jogador.addItem(new AdagaLadrao());
			//jogador.addItem(new ArmaduraGuerreiro());
			break;
		case 155:
			jogador.ganharDinheiro(500);
			break;
		case 0:
			System.out.println("Saindo do jogo...");
			break;
		default:
			System.out.println("Opção inválida!");
			break;
		}
    }
	
	private void equiparArmadura(){
		jogador.equiparArmadura();
	}
	
	private void verInventario() {
        System.out.println("Inventário:");
        System.out.println(jogador.getInventario().verInventario(jogador));
    }

    private void tomarDano() {
        System.out.print("Quanto de dano o jogador vai tomar? ");
        double dano = scanner.nextDouble();
        scanner.nextLine();
        jogador.tomarDano(dano, jogador, jogador);
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
		jogador.addItem(new BigBertha());
		jogador.addItem(new ArmaduraGuerreiro());
		
		
		//APAGAR
		equiparArma();
		
    }

    private void iniciarCombate() {
        new Combate(jogador);
    }
	
	//colocar no jogador =====================================================================================
	private void usarCura(){
		jogador.usarCura();
	}
	
	//JOGAR PARA O CODIGO DO JOGADOR
	private void equiparArma() {
		// Pega apenas as armas do inventário
		List<Arma> armas = jogador.getInventario().getTipo(Arma.class);

		if (armas.isEmpty()) {
			System.out.println("Nenhuma arma disponível no inventário.");
			return;
		}

		// Mostra as armas disponíveis
		for (int i = 0; i < armas.size(); i++) {
			Arma a = armas.get(i);
			System.out.println(i + " - " + a.getNome());
		}

		// Escolha do jogador
		Scanner sc = new Scanner(System.in);
		int escolha = sc.nextInt();

		// Valida índice
		if (escolha < 0 || escolha >= armas.size()) {
			System.out.println("Opção inválida!");
			return;
		}

		// Pega a arma escolhida
		Arma escolhida = armas.get(escolha); // ✅ correto

		// Equipa a arma
		jogador.equiparArma(escolhida);
		System.out.println("Você equipou: " + escolhida.getNome());

	}
		
	private void abrirLoja(){
		new LojaNoturna(jogador);
	}
	
	private void abrirForja(){
		new Forja(jogador);
	}

}