package shivsproject.mainapp;

public class PrizeCalculator {
	
	public static int calculateScore(int numberOfMatchingNumbers) {
		if (numberOfMatchingNumbers == 3) {
			return 50;
		}
		if (numberOfMatchingNumbers == 4) {
			return 100;
		}
		if (numberOfMatchingNumbers == 5) {
			return 200;
		}
		if (numberOfMatchingNumbers == 6) {
			return 500;
		} else {
			return 0;
		}
	}

}
