
import java.util.LinkedList;

public class chainedHash {
    private LinkedList<Pair>[] table;
    private int m;

    @SuppressWarnings("unchecked")
    public chainedHash(int m) {
        this.m = m;
        this.table = new LinkedList[m + 1]; 
        for (int i = 1; i <= m; i++) table[i] = new LinkedList<>();
    }

    private int hash(String key) {
        return (Math.abs(key.hashCode()) % m) + 1; 
    }

    public void insert(String key, String value) {
        int i = hash(key);
        for (Pair p : table[i]) {
            if (p.key.equals(key)) {
                p.value = value; 
                return;
            }
        }
        table[i].add(new Pair(key, value)); 
    }

    public String lookup(String key) {
        int i = hash(key);
        for (Pair p : table[i]) {
            if (p.key.equals(key)) return p.value;
        }
        return null; 
    }
}