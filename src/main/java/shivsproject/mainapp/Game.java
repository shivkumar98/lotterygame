package shivsproject.mainapp;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {

	static Scanner scanner = new Scanner(System.in);
	static Random random = new Random();
	static int[] userSelectedNumbers = new int[6];
	static int[] lotteryNumbers = new int[6];
	static final int gameCost = 10;
	static LotteryGame lotteryGame = new LotteryGame();
	static PrizeCalculator prizeCalculator = new PrizeCalculator();

	public static void gameSelector(int gameOption) {

		if (gameOption == 1) {
			manualPick();
		} else if (gameOption == 2) {
			luckyDip();
		} else if (gameOption == 3) {
			startGame();
		} else if (gameOption == 4) {
			quickRun();
		} else if (gameOption == 5) {
			userSelectedNumbers[0] = 0;
			App.main(null);
		} else {
			System.out.println("Please input a valid number");
			showMenu();
		}
	}

	public static void showMenu() {
		System.out.println("Please select one of the following: ");
		System.out.println("1 = Manual Pick");
		System.out.println("2 = Lucky Dip");
		System.out.println("3 = Start Game");
		System.out.println("4 = Quick Run");
		System.out.println("5 = Reset Game");
		try {
			int gameOption = scanner.nextInt();
			gameSelector(gameOption);
		} catch (InputMismatchException e) {
			System.out.println("Please enter numbers only");
		}

	}

	public static void manualPick() {

		for (int i = 0; i < 6; i++) {

			System.out.print("Please select a number for position: " + (i + 1) + " ");
			int inputNumber = scanner.nextInt();
			if (inputNumber > 0 && inputNumber < 60) {
				userSelectedNumbers[i] = inputNumber;
			} else {
				System.out.println("The number must be within range");
				manualPick();
			}
		}
		System.out.println("You have selected your numbers");
		System.out.println("");
		showMenu();

	}

	public static void luckyDip() {
		for (int j = 0; j < 6; j++) {
			userSelectedNumbers[j] = random.nextInt(59) + 1;
		}
	}

	public static void startGame() {

		if (userSelectedNumbers[0] == 0) {
			System.out.println("Select your numbers first, otherwise select Lucky Dip");
			System.out.println("");
			showMenu();
		}

		lotteryNumbers = lotteryGame.runLottery();

		int numberOfMatchingNumbers = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (userSelectedNumbers[i] == lotteryNumbers[j]) {
					numberOfMatchingNumbers = numberOfMatchingNumbers + 1;
				}
			}
		}

		System.out.println("The number of matches are: " + numberOfMatchingNumbers);
		int numberOfPoints = PrizeCalculator.calculateScore(numberOfMatchingNumbers);
		System.out.println("You have won: " + numberOfPoints + " points!");
		System.out.println("");
		showMenu();
	}

	public static void quickRun() {
		
		System.out.print("How many times would you like to play Lucky Dip ");
		System.out.println(" ");
		int numberOfGames = scanner.nextInt();
		int pointsSpentTotal = gameCost * numberOfGames;

		int pointsWonTotal = 0;
		for (int i = 0; i < numberOfGames; i++) {

			luckyDip();
			lotteryNumbers = lotteryGame.runLottery();

			int numberOfMatchingNumbers = 0;
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 6; k++) {
					if (userSelectedNumbers[j] == lotteryNumbers[k]) {
						numberOfMatchingNumbers = numberOfMatchingNumbers + 1;
					}
				}
			}

			pointsWonTotal = pointsWonTotal + PrizeCalculator.calculateScore(numberOfMatchingNumbers);
			System.out.println("For game " + (i + 1) + " the number of matches were: " + numberOfMatchingNumbers
					+ ". Your total points won so far are: " + pointsWonTotal);

		}
		
		System.out.println("The total points spent is: " + pointsSpentTotal);
		double amountReturned = pointsWonTotal;
		double amountBetted = pointsSpentTotal;
		double RTP = (amountReturned / amountBetted) * 100;
		System.out.println("The RTP percentage is " + RTP);
		System.out.println("");
		showMenu();

	}

}
