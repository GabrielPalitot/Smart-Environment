package gateway;
import java.util.HashMap;

public class HashMapUnique2 {
    private static HashMapUnique2 instance;
    private HashMap<String, String> sharedMap = new HashMap<>();

    private HashMapUnique2() {

    }

    public static synchronized HashMapUnique2 getInstance() {
        if (instance == null) {
            instance = new HashMapUnique2();
        }
        return instance;
    }

    public synchronized void addInMap(String key, String value) {
        sharedMap.put(key, value);
    }

    public synchronized String getFromMap(String key) {
        return sharedMap.get(key);
    }

    public synchronized String getTotalFromMap() {
        StringBuilder names = new StringBuilder();

        for (String key : sharedMap.keySet()) {
            names.append(key).append(", ");
        }

        // Remove comma on the final string
        if (names.length() > 0) {
            names.setLength(names.length() - 2);
        }

        return names.toString();
    }
}
