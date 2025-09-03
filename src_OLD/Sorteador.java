import java.util.Random;

public class Sorteador {
    private static final Random random = new Random();

    /**
     * @param chancePercent valor de 0 a 100 representando a chance de sucesso
     * @return true se "passar" na chance, false caso contrário
     */
    //public static boolean chance(double chancePercent) {
    //    return random.nextDouble() * 100 < chancePercent;
    //}
	
	public static boolean chance(double chance){
		if (chance > 100) {
			chance = 100;
		} else if(chance < 0) {
			chance = 0;
		}
		
        return random.nextInt(100) < chance;
    }
}