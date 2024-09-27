public class MyArrayDataException extends RuntimeException {
    public MyArrayDataException(int i, int j, String val) {
        super("Некорректный символ на " + i + " строке и " + j + " столбце");
    }
}