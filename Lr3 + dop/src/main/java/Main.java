public class Main {
    static int sum = 0;

    public static void main(String[] args)
    {
        String[][] array = {
                {"12", "7", "7", "15"},
                {"70", "13", "22", "7"},
               //{"7", "137", "13", "7"},
                {"7", "100", "28", "7"},
                {"170", "7", "7", "6"}
        };

        int[] fibbo = new int[17];
        fibbo[0] = 0;
        fibbo[1] = 1;

        for (int i = 2; i < fibbo.length; i++) {
            fibbo[i] = fibbo[i - 1] + fibbo[i - 2]; // Заполнение чисел Фибоначчи
        }

        try {
            test(array, fibbo);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static void test(String[][] array, int[] fibbo) throws MyException {
        try {
            check(array);

            int arrRow = array.length;
            int arrColl = array[0].length;

            // Обработка элементов массива
            for (int i = 0; i < arrRow; i++) {
                for (int j = 0; j < arrColl; j++) {
                    try {
                        int value = Integer.valueOf(array[i][j]);
                        sum += value;

                        // Проверка числа Фибоначчи
                        for (int k : fibbo) {
                            if (k == value) {
                                throw new MyFibonacciException(i + 1, j + 1, value);
                            }
                        }

                        // Проверка на непростое трёхзначное число
                        if (value >= 100 && value <= 999 && !isPrime(value)) {
                            throw new MyNoSimpleException(i + 1, j + 1, array[i][j]);
                        }

                    } catch (NumberFormatException e) {
                        throw new MyException(i, j, array[i][j], "MyArrayDataException");
                    } catch (MyFibonacciException | MyNoSimpleException e) {
                        e.printStackTrace(); // Вывод исключения, но продолжение выполнения
                    }
                }
            }
            System.out.println("Сумма элементов массива: " + sum);

        } catch (MyArraySizeException e) {
            // Если размер массива некорректный, выбрасываем исключение
            throw e;
        }
    }

    public static boolean isPrime(int number) {
        if (number < 100)
        {
            return false;
        }
        for (int i = 2; i <= number / 2; i++)
        {
            if (number % i == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean check(String[][] array) throws MyArraySizeException
    {
        int arrRow = array.length;
        for (String[] row : array)
        {
            if (row.length != 4)
            {
                throw new MyArraySizeException("Некорректный массив! Ожидалось 4x4.");
            }
        }
        if (arrRow != 4)
        {
            throw new MyArraySizeException("Некорректный массив! Ожидалось 4x4.");
        }
        return true;
    }

    static class MyException extends Exception {
        int row, col;
        String value;
        String type;

        public MyException(int row, int col, String value, String type) {
            super("Ошибка в ячейке (" + (row + 1) + ", " + (col + 1) + "): " + value);
            this.row = row;
            this.col = col;
            this.value = value;
            this.type = type;
        }
    }
}