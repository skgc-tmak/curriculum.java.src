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
    public static final int NUM_OF_CHALLENGE_LIMIT = 5;
    public static final int ONE_LINE = 1;
    public static final String MESSAGE_FOR_SUGGEST_INPUT_INT = "数値で入力してください：";
    public static final String MESSAGE_FOR_INPUT_INT = "%d桁の数字を入力してください：";
    public static final String MESSAGE_FOR_CLEAR = "おめでとう！%d回目で成功♪%n";
    public static final String MESSAGE_FOR_HIT_AND_BLOW_NUM = "ヒット：%d個、ブロー：%d個%n";
    public static final String MESSAGE_FOR_FAILURE = "残念！正解は%dだよ%n";

    public static void main(String[] args) {
        List<Integer> elementNum = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int[] dealerNums = new int[NUM_OF_DIGITS];
        
        generateNum(dealerNums, elementNum);
        testShowNum(dealerNums, IS_TEST);
        
        showBlankLine(ONE_LINE);
        
        playHitAndBlow(dealerNums);

    }

    private static void playHitAndBlow(int[] dealerNums) {
        int challengeTime = 0;
        int hitNum=0;
        int blowNum=0;
        int[] playerNums = new int[NUM_OF_DIGITS];
        while (!isOverLimitNum(++challengeTime)&&!isClear(hitNum)) {
            showInputDigitNumMessage();

            changeIntToArray(playerNums, receiveValidatedNum());

            hitNum = validateHit(dealerNums, playerNums);

            blowNum = validateBlow(dealerNums, playerNums,hitNum);

            showResult(hitNum,blowNum,challengeTime);
        }
        if(!isClear(hitNum))showLoseMessage(dealerNums);
    }

    private static void showLoseMessage(int[] dealerNums) {
        System.out.format(MESSAGE_FOR_FAILURE,showArray(dealerNums));;
    }

    private static void showResult(int hitNum, int blowNum, int challengeTime) {
        if(isClear(hitNum)){
            showClearMessage(challengeTime);
        }else{
            showHitAndBlowNums(hitNum,blowNum);
        }
    }

    private static void showClearMessage(int challengeTime) {
        System.out.format(MESSAGE_FOR_CLEAR,challengeTime);
    }

    private static boolean isClear(int hitNum) {
        return hitNum==NUM_OF_DIGITS;
    }

    private static void showHitAndBlowNums(int hitNum, int blowNum) {
        System.out.format(MESSAGE_FOR_HIT_AND_BLOW_NUM,hitNum,blowNum);
    }

    private static int validateBlow(int[] dealerNums, int[] playerNums, int hitNum) {
        int tempNum = 0;
        for (int i = 0; i < NUM_OF_DIGITS; i++) {
            for (int j = 0; j < NUM_OF_DIGITS; j++) {
                if (isEqualNums(dealerNums[i], playerNums[j]))
                tempNum++;
            }
        }
        return tempNum-hitNum;
    }

    private static int validateHit(int[] dealerNums, int[] playerNums) {
        int hitNum = 0;
        for (int i = 0; i < NUM_OF_DIGITS; i++) {
            if (isEqualNums(dealerNums[i], playerNums[i]))
                hitNum++;
        }
        return hitNum;
    }

    private static boolean isEqualNums(int num1, int num2) {
        return num1 == num2;
    }

    private static boolean isOverLimitNum(int challengeTime) {
        return challengeTime > NUM_OF_CHALLENGE_LIMIT;
    }

    private static void showInputDigitNumMessage() {
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
            showInputDigitNumMessage();
            return receiveValidatedNum();
        }
        return inputInt;
    }

    private static String changeIntToStr(int num) {
        return Integer.toString(num);
    }

    private static void changeIntToArray(int[] playerNums, int inputInt) {
        for (int i = NUM_OF_DIGITS - 1; i >= 0; i--) {
            playerNums[i] = inputInt % 10;
            inputInt /= 10;
        }
    }

    private static boolean isEqualNumOfDigits(String str) {
        return getLength(str) == NUM_OF_DIGITS;
    }

    private static int getLength(String inputStr) {
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
            showArray(generatedNum);
            System.out.println();
        }
    }

    private static void showArray(int[] generatedNum) {
        for (int num : generatedNum) {
            System.out.print(num);
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
