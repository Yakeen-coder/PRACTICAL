public class openHash {
    private Pair[] table;
    private int m;
    private int size;

    public openHash(int m) {
        this.m = m;
        this.table = new Pair[m + 1];
        this.size = 0;
    }

    public int hash(String key) {
        // Uniform distribution hash 
        return (Math.abs(key.hashCode()) % m) + 1;
    }

    public void insert(String key, String value) {
        if (isFull()) return;
        int h = hash(key);
        int i = h;
        int r = 0;
        int p = 7; // Suitable prime for probing 

        while (table[i] != null) {
            if (table[i].key.equals(key)) {
                table[i].value = value;
                return;
            }
            r++;
            i = (h + r * p) % m; // Linear/Prime probing 
            if (i == 0) i = m; 
        }
        table[i] = new Pair(key, value);
        size++;
    }

    public String lookup(String key) {
        int h = hash(key);
        int i = h;
        int r = 0;
        int p = 7;
        while (table[i] != null) {
            if (table[i].key.equals(key)) return table[i].value;
            r++;
            i = (h + r * p) % m;
            if (i == 0) i = m;
            if (r > m) break; // Avoid infinite loops
        }
        return null; // [cite: 49]
    }

    public boolean isFull() { return size >= m; } 
}
