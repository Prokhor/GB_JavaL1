package gb.l2hw;

import java.util.Arrays;
import java.util.Random;

public class MainClass {

    private static Random rand = new Random();

    public static void main(String[] args) {
        // 1.
        int[] arr = initZeroOneArray(20);
        System.out.println(Arrays.toString(arr));
        changeZeroOneArray(arr);
        System.out.println(Arrays.toString(arr));
        // 2.
        System.out.println(Arrays.toString(initArrayEight()));
        // 3.
        arr = initIntArray(15, 20);
        System.out.println(Arrays.toString(arr));
        arr = lessThanSix(arr);
        System.out.println(Arrays.toString(arr));
        // 4.
        int[][] squareArr = initSquareArray(20);
        for (int i = 0; i < squareArr.length; i++) {
            System.out.println(Arrays.toString(squareArr[i]));
        }
        // 5.
        arr = initIntArray(20, 100);
        System.out.println(Arrays.toString(arr));
        System.out.println(findMinMax(arr));
        // 6.
        arr = initIntArray(5, 10);
        System.out.println(Arrays.toString(arr));
        System.out.println(checkBalance(arr));
        // 7.
        arr = initIntArray(5, 20);
        System.out.println(Arrays.toString(arr));
        arr = shiftArray(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] initIntArray(int size, int weight){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(weight);
        }
        return arr;
    }

    /* Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0 */
    private static int[] initZeroOneArray(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(2);
        }
        return arr;
    }
    private static int[] changeZeroOneArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0){
                arr[i] = 1;
            } else{
                arr[i] = 0;
            }
        }
        return arr;
    }

    /* Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21 */
    private static int[] initArrayEight(){
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }
        return arr;
    }

    /* Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2 */
    private static int[] lessThanSix(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6){
                arr[i] *= 2;
            }
        }
        return arr;
    }

    /* Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами */
    private static int[][] initSquareArray(int size){
        int[][] squareArr = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i + j == size - 1){
                    squareArr[i][j] = 1;
                } else {
                    squareArr[i][j] = 0;
                }
            }
        }
        return squareArr;
    }

    /* ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета) */
    private static String findMinMax(int[] arr){
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]){
                min = arr[i];
            }
            if (max < arr[i]){
                max = arr[i];
            }
        }
        return String.format("Минимальный элемент: %d\nМаксимальный элемент: %d", min, max);
    }

    /* ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место,
       в котором сумма левой и правой части массива равны. Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
       граница показана символами ||, эти символы в массив не входят */
    private static boolean checkBalance(int[] arr){
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            sumLeft = 0;
            sumRight = 0;
            for (int j = 0; j <= i; j++) {
                sumLeft += arr[j];
            }
            for (int j = i + 1; j < arr.length; j++) {
                sumRight += arr[j];
            }
            if (sumLeft == sumRight) break;
        }
        return sumLeft == sumRight;
    }

    /* ****Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным), при этом метод должен сместить
       все элементы массива на n позиций. Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
       Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
       При каком n в какую сторону сдвиг можете выбирать сами */
    private static int[] shiftArray(int[] arr, int n){
        if (n == 0){
            return arr;
        } else if (n > 0){
            for (int i = 1; i <= n; i++) {
                moveOne(arr, "right");
            }
        } else{
            for (int i = 1; i <= Math.abs(n); i++) {
                moveOne(arr, "left");
            }
        }
        return arr;
    }
    private static int[] moveOne(int[] arr, String side){
        switch (side){
            case "right":
                int last = arr[arr.length - 1];
                for (int i = arr.length - 1; i >= 1; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[0] = last;
                break;
            case "left":
                int first = arr[0];
                for (int i = 1; i < arr.length; i++) {
                    arr[i - 1] = arr[i];
                }
                arr[arr.length - 1] = first;
                break;
        }
        return arr;
    }
}