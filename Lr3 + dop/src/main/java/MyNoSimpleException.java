public class MyNoSimpleException extends RuntimeException {
    public MyNoSimpleException(int i, int j, String s) {
        super("Найдено непростое 3-хзначное число: '" + s + "' Номер ячейки: " + i + " x " + j);
    }
}