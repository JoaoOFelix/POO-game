import java.util.Scanner;

public class Combate {
    Jogador jogador;
    Inimigo inimigo;

    public Combate(Jogador jogador, Inimigo inimigo){
        this.jogador = jogador;
        this.inimigo = inimigo;

        System.out.print("\nVocê encontou um " + inimigo.getRaca() + " chamado " + inimigo.getNome());
        System.out.println("\nVocê entrou em combate com " + inimigo.getNome() + "!");


        enemyTurn();
        acao();
    }

    public void playerTurn() {

        System.out.println(jogador.getNome() + " atacou " + inimigo.getNome() + " e causou " + jogador.getDano() + " de dano\n");
        jogador.atacar(inimigo);

        System.out.println("Vida do inimigo: "  + inimigo.getVida() + "/" + inimigo.getVidaMax() + "\n");

        if(inimigo.isAlive())
            enemyTurn();
    }

    public void enemyTurn() {
        inimigo.enemyAi(jogador);

        //acao();
    }
    
    public void acao(){
        Scanner scanner = new Scanner(System.in);

        int opcao = -1;
        do {

            if(opcao == 0){
                break;
            }
            
            System.out.println("\n");
            System.out.println("\n===== BATALHA =====");
            System.out.println("1. Ver status");
            System.out.println("2. Ver inventario");
            System.out.println("3. Atacar " + inimigo.raca);
            System.out.println("0. Sair");
            System.out.print("\n>");

            while (!scanner.hasNextInt()) {
                System.out.print("Digite um número valido: ");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.println("\nStats " + jogador.getNome());
                    jogador.getStatus();

                    System.out.println("\nStats " + inimigo.getRaca());
                    System.out.println(inimigo.getStatus());


                    break;

                case 2:
                    System.out.println("Inventario:");
                    System.out.println(jogador.getItens());


                    break;

                case 3:
                    playerTurn();


                    break;

                case 0:
                    System.out.println("Saindo da batalha...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0 || jogador.isAlive() || !inimigo.isAlive());

        if (!jogador.isAlive()) {
            System.out.println("Fim de jogo: o jogador morreu.");
        }

        if(!inimigo.isAlive()){
            System.out.println(jogador.getNome() + " derrotou " + inimigo.getNome() + " o " + inimigo.getRaca());
            inimigo.morrer(jogador);
        }
    }
}
