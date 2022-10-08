public class OffByN implements CharacterComparator{

    private final int Num;

    public OffByN(int N) {
        Num = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == Num;
    }
}
