package com.company;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String[] romanOneToHundred = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
            "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII",
            "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV",
            "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII",
            "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI",
            "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };// массив с римскими цифрами для операций с пределом 10х10
    static String[] arabicOneToTen = {"1","2","3","4","5","6","7","8","9","10","0"
    };//массив с арабским цифрами для ввода 1-10
    static String[] romanOnetoTen = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"
    };//массив с римскими цифрами для ввода 1-10

    static char sign;// переменная знака операции
    static int FirstNumber, SecondNumber, Result;
    static int n;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);// создаем сканер
        System.out.print("Введите числа от 1 до 10/ I до X:");
        String InsertOperation = scan.nextLine();// вводим операцию
        String abc = InsertOperation.replaceAll("\\s+", "");// в переменную abc заносим строку с введенным данными без пробелов
        String[] newStroka = InsertOperation.replaceAll("\\s+", "").split("[+-/*]");/* создаем массив,
        в который заносятся числа стоящие по обоим сторонам от разделителя(знака операции)*/
        char[] arr = new char[abc.length()];// создаем массив куда заносим символ знака операции
        String a, b;// объявляем переменные для работы с числами
        int kol = newStroka.length;// в переменную заносим длину массива

        //проверка на размер, если размер массива меньше 2(тк работаем с 2 числами)
        try{
            a = newStroka[0];
            b = newStroka[1];
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("строка не является математической операцией");
        }

        //цикл для поиска знака операции и добавления в переменную
        for (int i = 0; i < abc.length(); i++) {
            arr[i] = abc.charAt(i);
            switch (arr[i]) {
                case '+', '-', '*', '/':
                    sign = arr[i];
            }
        }

        // проверка на различные ограничения и получение значений в случае удовлетворения условий
        if(kol>2){
            System.out.println("ВНИМАНИЕ: введено больше двух чисел");
        }
        else {
            if ((Arrays.asList(arabicOneToTen).contains(a)) && (Arrays.asList(romanOnetoTen).contains(b))) {
                System.out.println("ВНИМАНИЕ: используются одновременно разные системы счисления");
            }
            else if ((Arrays.asList(romanOnetoTen).contains(a)) && (Arrays.asList(arabicOneToTen).contains(b))) {
                System.out.println("ВНИМАНИЕ: используются одновременно разные системы счисления");
            }
            else if((Arrays.asList(romanOneToHundred).indexOf(a)>10)||(Arrays.asList(romanOneToHundred).indexOf(b)>10)){
                System.out.print("ВНИМАНЕ:введите числа в диапазоне от I до X");
            }
            else if((Arrays.asList(romanOnetoTen).contains(a))&&(Arrays.asList(romanOnetoTen).contains(b))) {
                FirstNumber = RomanToArabic(a);
                SecondNumber = RomanToArabic(b);
                if((FirstNumber< SecondNumber)&& sign == '-'){
                    System.out.print("ВНИМАНЕ:в римской системе нет отрицательных чисел");
                }
                else{
                    Result = Calculation(FirstNumber, SecondNumber, sign);
                    String RomanResult = ArabicToRoman(Result);
                    System.out.print("Римский калькулятор : " + RomanResult);
                }
            }
            else if(Integer.parseInt(a)>10&&Integer.parseInt(b)>10){
                System.out.print("ВНИМАНЕ: введите числа в диапазоне от 1 до 10 ");
            }
            else if (Integer.parseInt(a)>10||Integer.parseInt(b)>10){
                System.out.print("ВНИМАНЕ: введите числа в диапазоне от 1 до 10 ");
            }
            else if((Arrays.asList(arabicOneToTen).contains(a))&&(Arrays.asList(arabicOneToTen).contains(b))) {
                FirstNumber = Integer.parseInt(a);
                SecondNumber = Integer.parseInt(b);
                Result = Calculation(FirstNumber, SecondNumber, sign);
                System.out.print("Арабский калькулятор: " + Result);
            }
        }

    }
    // конвертер из Арабских в Римские
    private static String ArabicToRoman(int RomanIndex) {
        String ConvertedInRoman = romanOneToHundred[RomanIndex];
        return ConvertedInRoman;
    }
    //конвертер из Римских в Арабские
    private static int RomanToArabic(String rom) {
        for (int i = 0; i < romanOneToHundred.length; i++){
            if(rom.equals(romanOneToHundred[i])){
                n=Arrays.asList(romanOneToHundred).indexOf(rom);
            }
        }
        return n;
    }
    //рассчитываем введенную операцию
    public static int Calculation(int first, int second, char symbol) {
        int Total = 0;
        switch (symbol) {
            case '+':
                Total = first + second;
                break;
            case '-':
                Total = first - second;
                break;
            case '*':
                Total = first * second;
                break;
            case '/':
                if(second==0){
                    System.out.println("Внимание: знаменатель равен нулю");
                    break;
                }else {
                    Total = first / second;
                }
                break;
        }
        return Total;
    }
}
