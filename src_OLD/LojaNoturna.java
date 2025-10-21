import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public final class LojaNoturna {

		private Jogador jogador;
		private List<Item> itensLoja = new ArrayList<>();
		private double dinheiroJogador;
		private double multVenda = 1.2;
		private Item escolhaItem = null;
		

		public LojaNoturna(Jogador jogador){	
			this.jogador = jogador;
			this.dinheiroJogador = jogador.getDinheiro();
			
			
			itensLoja.add(new EspadaVampirica());
			itensLoja.add(new PocaoCuraMedia());
			itensLoja.add(new Excalibur());
			itensLoja.add(new ArmaduraGuerreiro());
			itensLoja.add(new Tridente());
			itensLoja.add(new PorreteOrc());
			itensLoja.add(new AdagaLadrao());
			itensLoja.add(new PocaoCuraGrande());
			itensLoja.add(new BigBertha());
			itensLoja.add(new EspadaDeOssos());
			
			abrirLoja();
		}		
		
		private void abrirLoja(){
			
			System.out.println("Bem vindo a loja noturna");
			System.out.println("Dinheiro: " + dinheiroJogador);
			System.out.println("======================");
			
			
			
			for (int i = 0; i < itensLoja.size(); i++) {
				System.out.println(i + "- " + itensLoja.get(i).getNome() + " (" + itensLoja.get(i).getRaridade() + ") " + ": " + itensLoja.get(i).getPrecoVenda() * multVenda);
			}
		
			System.out.println("0- Sair");
		
			// Escolha do jogador
			System.out.print(">");
			Scanner sc = new Scanner(System.in);
			int escolha = sc.nextInt();
		
		
		
			if (escolha >= 0 && escolha < itensLoja.size()) {
				escolhaItem = itensLoja.get(escolha);
			} else {
				System.out.println("Opção inválida!");
			}
		
			if(escolhaItem != null){
			
				if(dinheiroJogador >= escolhaItem.getPrecoVenda() * multVenda){
					jogador.addItem(escolhaItem);
					jogador.perderDinheiro(escolhaItem.getPrecoVenda() * multVenda);
				} else {
					System.out.println("Dinheiro insuficiente");
				}
			} else {
				System.out.println("Saindo");
			}
		}
		
}