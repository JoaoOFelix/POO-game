import java.util.Random;

public class Sorteador {
    private static final Random random = new Random();

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
	
	
	public static int num100(){
		//numero aleatorio de 1 a 100
		//return random.nextInt(1, 101);
		return 0;
	}
	
	
}