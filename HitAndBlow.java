import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HitAndBlow {
    public static final boolean IS_TEST = true;
    public static final int NUMBER_OF_DIGITS = 4;
    public static final int[] BASE_NUMBER = {0,1,2,3,4,5,6,7,8,9};
    
    public static final Random RANDOM = new Random();
    public static final Scanner STDIN = new Scanner(System.in);
    public static final String MESSAGE_FOR_INPUT_INT = "数値で入力してください：";

    public static void main(String[] args) {
        List<Integer> elementNum = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        int[] generatedNum = generateNum(elementNum);
        testShowNum(generatedNum,IS_TEST);

        receiveInputNumOfDigits();

    }

    private static void receiveInputNumOfDigits() {
        String inputStr=recieveInputStr();

        int inputInt=validateStrToInt(inputStr);
    }

    private static int validateStrToInt(String inputStr) {
        int inputInt=0;
        try {
            changeStrToInt(inputStr);
        } catch (Exception e) {
            show(MESSAGE_FOR_INPUT_INT);
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
        if(isTest){
            for(int num:generatedNum){
                System.out.print(num);
            }
        }
    }

    private static int[] generateNum(List<Integer> elementNum) {
        int[] nums= new int[NUMBER_OF_DIGITS];
        for(int i=0;i<NUMBER_OF_DIGITS;i++){
            nums[i]=fetchRandomNum(elementNum);
        }
        return nums;
    }

    private static int fetchRandomNum(List<Integer> elementNum) {
        int randomNum=getRandomNum(elementNum.size());
        getFromList(elementNum, randomNum);
        removeInList(elementNum, randomNum);
        return randomNum;
    }

    private static void removeInList(List<Integer> elementNum, int randomNum) {
        elementNum.remove(randomNum);
    }

    private static void getFromList(List<Integer> elementNum, int randomNum) {
        elementNum.get(randomNum);
    }

    private static int getRandomNum(int bound) {
        return RANDOM.nextInt(bound);
    }
}
