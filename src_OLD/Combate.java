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
		
		
		if(jogador.hasArma() && inimigo.hasArma()){
			if(jogador.getArma().getVelocidade() > inimigo.getArma().getVelocidade()){
				System.out.println("Jogador é mais rápido e ataca primeiro");
				acao();
			} else {
				System.out.println("Inimigo é mais rápido e ataca primeiro");
				enemyTurn();
			}
		} else {
			System.out.println("Inimigo é mais rápido e ataca primeiro");
			enemyTurn();
		}
    }
	
	private void criarInimigos(){
		int spawnChance = 5;
		int goblinsDerrotados = Goblin.getGoblinsDerrotados();
	
		
		//calcura a chanhce de de encontrar orcs com base na quantidade de golbins derrotados
		if(goblinsDerrotados > 0){
			spawnChance *= goblinsDerrotados;
		}
		
		double nivelJogador = jogador.getXpLevel();
		
		if(nivelJogador > 7 && nivelJogador < 10){
			this.inimigo = new DragaoVerde("Norr");
		} else if(nivelJogador >= 10){
			this.inimigo = new DragaoVermelho("Manchur");
		} else if(nivelJogador > 5 && nivelJogador < 7){
			this.inimigo = new GolemDePedra("Golmun");
		} else {
			
			if(Sorteador.chance(spawnChance)){
				this.inimigo = new Orc(sortearOrc());
			} else {
				this.inimigo = new Goblin(sortearGoblin());
			}
			
		}
		
		System.out.print("\nVocê encontou um " + inimigo.getRaca() + " chamado " + inimigo.getNome());
	}
	
    public void playerTurn() {	
		if(turno == 0 && jogador.getArma() != null){
			jogador.getArma().usarUnico(inimigo, jogador);
		}
			
        jogador.atacar(inimigo, jogador);

        //System.out.println("Vida do inimigo: "  + inimigo.getVida() + "/" + inimigo.getVidaMax() + "\n");

        if(inimigo.isAlive() && jogador.isAlive())
            enemyTurn();
		
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
		if(turno == 0 && inimigo.getArma() != null){
			inimigo.getArma().usarUnico(jogador, inimigo);
		}
		
		if(inimigo.isAlive() && jogador.isAlive()){
			inimigo.enemyAi(jogador);
			acao();
		}
    }
    
    public void acao(){
		Scanner scanner = new Scanner(System.in);

		int opcao = -1;

		// loop central da batalha
		while (opcao != 0 && jogador.isAlive() && inimigo.isAlive() && !inimigo.getFuga()) {

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
					System.out.println(jogador.getInventario().getInventario());
					break;

				case 3:
					// turno do jogador
					if(turno == 0 && jogador.getArma() != null){
						jogador.getArma().usarUnico(inimigo, jogador);
					}
					jogador.atacar(inimigo, jogador);
					//System.out.println("Vida do inimigo: "  + inimigo.getVida() + "/" + inimigo.getVidaMax() + "\n");
					turno++;

					// turno do inimigo (só se os dois ainda estiverem vivos)
					if(inimigo.isAlive() && jogador.isAlive()){
						inimigo.enemyAi(jogador);
					}
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
		}
		
		if (!jogador.isAlive()) {
			System.out.println("Fim de jogo: o jogador morreu.");
		}
		
		

		if(!inimigo.isAlive()){
			System.out.println(jogador.getNome() + " derrotou " + inimigo.getNome() + " o " + inimigo.getRaca());
		}

		if(inimigo.getFuga()){
			System.out.println("Inimigo fugiu...");
		}
	}

}
