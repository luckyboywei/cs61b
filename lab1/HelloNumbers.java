public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
	int s = 0;
        while (x < 10) {
	    s = s + x;
	    x = x + 1;
	    System.out.print(s + " ");
        }
    }
}
