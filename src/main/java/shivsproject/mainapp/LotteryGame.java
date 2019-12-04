package shivsproject.mainapp;

import java.util.Random;

public class LotteryGame {

	static int[] lotteryNumbers = new int[6];
	static Random random = new Random();

	public int[] runLottery() {
		

		for (int j = 0; j < lotteryNumbers.length; j++) {
			lotteryNumbers[j] = random.nextInt(59) + 1;
		}

		System.out.println("The Lottery numbers are: ");
		for (int j = 0; j < lotteryNumbers.length - 1; j++) {
			System.out.print(lotteryNumbers[j] + " ");
		}
		System.out.print(" " + lotteryNumbers[5]);
		System.out.println(" ");
		return lotteryNumbers;

	}

}
