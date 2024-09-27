public class MyFibonacciException extends RuntimeException {
    public MyFibonacciException(int i, int j, int fibbo) {
        super("Число Фибоначчи " + fibbo + ". Номер ячейки: " + i + " x " + j);
    }
}