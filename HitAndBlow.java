import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HitAndBlow {
    public static final Random RANDOM = new Random();
    public static final Scanner STDIN = new Scanner(System.in);

    public static final boolean IS_TEST = true;
    public static final int NUM_OF_DIGITS = 5;
    public static final int[] BASE_NUM = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    public static final String MESSAGE_FOR_SUGGEST_INPUT_INT = "数値で入力してください：";
    public static final int ONE_LINE = 1;
    public static final String MESSAGE_FOR_INPUT_INT = "%d桁の数字を入力してください：";

    public static void main(String[] args) {
        List<Integer> elementNum = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int[] dealerNums = new int[NUM_OF_DIGITS];
        int[] playerNums = new int[NUM_OF_DIGITS];
        generateNum(dealerNums, elementNum);
        testShowNum(dealerNums, IS_TEST);

        showBlankLine(ONE_LINE);

        showMessageInputNumDigit();

        changeIntToArray(playerNums, receiveValidatedNum());

        testShowNum(playerNums, IS_TEST);
    }

    private static void showMessageInputNumDigit() {
        System.out.format(MESSAGE_FOR_INPUT_INT, NUM_OF_DIGITS);
    }

    private static void showBlankLine(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println();
        }
    }

    private static int receiveValidatedNum() {

        int inputInt = validateStrToInt(recieveInputStr());

        return validateDigit(inputInt);
    }

    private static int validateDigit(int inputInt) {
        if (!isEqualNumOfDigits(changeIntToStr(inputInt))) {
            showMessageInputNumDigit();
            return receiveValidatedNum();
        }
        return inputInt;
    }

    private static String changeIntToStr(int num) {
        return Integer.toString(num);
    }

    private static void changeIntToArray(int[] playerNums, int inputInt) {
        for (int i = NUM_OF_DIGITS - 1; i > 0; i--) {
            playerNums[i] = inputInt % 10;
            inputInt /= 10;
        }
    }

    private static boolean isEqualNumOfDigits(String str) {
        return getStrLength(str) == NUM_OF_DIGITS;
    }

    private static int getStrLength(String inputStr) {
        return inputStr.length();
    }

    private static int validateStrToInt(String inputStr) {
        int inputInt = 0;
        try {
            inputInt = changeStrToInt(inputStr);
        } catch (NumberFormatException e) {
            show(MESSAGE_FOR_SUGGEST_INPUT_INT);
            return receiveValidatedNum();
        }
        return inputInt;
    }

    private static void show(String str) {
        System.out.format(str);
    }

    private static int changeStrToInt(String inputStr) {
        return Integer.parseInt(inputStr);
    }

    private static String recieveInputStr() {
        return STDIN.nextLine();
    }

    private static void testShowNum(int[] generatedNum, boolean isTest) {
        if (isTest) {
            for (int num : generatedNum) {
                System.out.print(num);
            }
        }
    }

    private static void generateNum(int[] nums, List<Integer> elementNum) {
        for (int i = 0; i < NUM_OF_DIGITS; i++) {
            fetchRandomNum(i, nums, elementNum);
        }
    }

    private static void fetchRandomNum(int num, int[] array, List<Integer> elementNum) {
        int randomNum = getRandomNum(elementNum.size());
        array[num] = getFromList(elementNum, randomNum);
        removeFromList(elementNum, randomNum);
    }

    private static void removeFromList(List<Integer> elementNum, int randomNum) {
        elementNum.remove(randomNum);
    }

    private static int getFromList(List<Integer> elementNum, int randomNum) {
        return elementNum.get(randomNum);
    }

    private static int getRandomNum(int bound) {
        return RANDOM.nextInt(bound);
    }
}
