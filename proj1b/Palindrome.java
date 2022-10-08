public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int start = 0;
        int end = word.length() - 1;
        while (start < end) {
            if (!cc.equalChars(word.charAt(start), word.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPalindrome(d);
    }

    /** attention: please not use Deque.get() method
     *  If Deque is based on the LinkListDeque, it will waste lots of time.
     *  */
    private boolean isPalindrome(Deque<Character> d) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        } else if (d.removeFirst() != d.removeLast()) {
            return false;
        }
        return isPalindrome(d);
    }
}
