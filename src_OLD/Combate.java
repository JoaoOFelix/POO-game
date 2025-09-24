import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Combate {
	private int turno;
    private Jogador jogador;
    private Inimigo inimigo;
	private List<String> goblins;
    private List<String> orcs;
    private Random random = new Random();
	
    public Combate(Jogador jogador){
		this.jogador = jogador;
		this.turno = 0;
		
		sorteadorNomes();
		criarInimigos();
        enemyTurn();
        acao();
    }
	
	private void criarInimigos(){
		int spawnChance = 5;
		int goblinsDerrotados = Goblin.getGoblinsDerrotados();
	
		
		//calcura a chanhce de de encontrar orcs com base na quantidade de golbins derrotados
		if(goblinsDerrotados > 0){
			spawnChance *= goblinsDerrotados;
		}
		
		//TESTE
		System.out.println("TESTE SPAWN RATE__ %" + spawnChance);
		
		if(Sorteador.chance(spawnChance)){
			this.inimigo = new Orc(sortearOrc());
		} else {
			this.inimigo = new Goblin(sortearGoblin());
		}
		
		System.out.print("\nVocê encontou um " + inimigo.getRaca() + " chamado " + inimigo.getNome());
        System.out.println("\nVocê entrou em combate com " + inimigo.getNome() + "!");
	}
	

    public void playerTurn() {
        jogador.atacar(inimigo, jogador);

        System.out.println("Vida do inimigo: "  + inimigo.getVida() + "/" + inimigo.getVidaMax() + "\n");

        if(inimigo.isAlive())
            enemyTurn();
		
		
		if(turno == 0 && jogador.getArmaEquipada() != null){
			jogador.getArmaEquipada().usarUnico(inimigo, jogador);
		}
		
		turno++;
    }
	
	private void sorteadorNomes() {
		try {
			goblins = Files.readAllLines(Paths.get("nomesGoblin.txt"));
			orcs = Files.readAllLines(Paths.get("nomesOrc.txt"));
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivos de nomes: " + e.getMessage());
			goblins = new java.util.ArrayList<String>();
			orcs = new java.util.ArrayList<String>();
		}
	}

	
	public String sortearGoblin() {
        if (goblins.isEmpty()) return "Goblin sem nome";
        return goblins.get(random.nextInt(goblins.size()));
    }

    public String sortearOrc() {
        if (orcs.isEmpty()) return "Orc sem nome";
        return orcs.get(random.nextInt(orcs.size()));
    }

    public void enemyTurn() {
        inimigo.enemyAi(jogador);
    }
    
    public void acao(){
        Scanner scanner = new Scanner(System.in);

        int opcao = -1;
        do {

            if(opcao == 0){
				System.out.println("Saindo da batalha!");
                break;
            }
            
            System.out.println("\n");
            System.out.println("\n===== BATALHA =====");
            System.out.println("1. Ver status");
            System.out.println("2. Ver inventario");
            System.out.println("3. Atacar " + inimigo.raca);
			System.out.println("4. Usar poção de cura");
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
                    System.out.println(jogador.inventario.getInventario());
                    break;

                case 3:
                    playerTurn();
                    break;
					
				case 4:
					jogador.usarCura();
					break;

                case 0:
                    System.out.println("Saindo da batalha...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0 && jogador.isAlive() && inimigo.isAlive() && !inimigo.getFuga());

        if (!jogador.isAlive()) {
            System.out.println("Fim de jogo: o jogador morreu.");
        }

        if(!inimigo.isAlive()){
            System.out.println(jogador.getNome() + " derrotou " + inimigo.getNome() + " o " + inimigo.getRaca());
            //inimigo.morrer(jogador);
        }
		
		if(inimigo.getFuga()){
			System.out.println("Inimigo fugiu...");
		}
    }
}
