import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class program {
    public static void main(String args[]) {
		
    Scanner scanner = new Scanner(System.in);
	
	
	//Inimigos
	Inimigo goblin = new Goblin("Bobe");

    Inimigo[] inimigos = {goblin};

	
    System.out.println("Digite o nome do jogador");
    Jogador jogador = new Jogador(scanner.nextLine()); 
    
    System.out.println("Bem vindo " + jogador.getNome() + "!");
    
    int opcao;
        do {
			System.out.println("\n");
            System.out.println("\n===== MENU =====");
            System.out.println("1. Ver status");
            System.out.println("2. Ver inventario");
            System.out.println("3. Tomar dano");
            System.out.println("4. Adicionar item");
            System.out.println("5. Buscar...");
            System.out.println("0. Sair");
            System.out.print("\n> ");
            
            while (!scanner.hasNextInt()) {
                System.out.print("Digite um número valido: ");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1:
                    jogador.getStatus();
                    break;

                case 2:
                    System.out.println("Inventario:");
                    System.out.println(jogador.getItens());
                    break;

                case 3:
                    System.out.print("Quanto de dano o jogador vai tomar? ");
                    double dano = scanner.nextDouble();
                    scanner.nextLine(); // limpar o buffer
                    jogador.dmgPlayer(dano);
                    break;

                case 4:
                    System.out.print("Digite o nome do item: ");
                    String item = scanner.nextLine();
                    jogador.addItem(item);
                    break;

                case 5:
                    Combate combate = new Combate(jogador, goblin);
                    break;
				
				case 0:
					System.out.println("Saindo do jogo...");
					break;
				
                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0 && jogador.isAlive());

        if (!jogador.isAlive()) {
            System.out.println("Fim de jogo: o jogador morreu.");
        }

        scanner.close();
    
  }
}