package gateway;
import java.util.HashMap;
public class HashMapUnique {
        private static HashMapUnique instance;
        private HashMap<String, Integer> sharedMap = new HashMap<>();

        private HashMapUnique() {

        }

        public static synchronized HashMapUnique getInstance() {
            if (instance == null) {
                instance = new HashMapUnique();
            }
            return instance;
        }

        public synchronized void addInMap(String key, Integer value) {
            sharedMap.put(key, value);
        }

        public synchronized Integer getFromMap(String key) {
            return sharedMap.get(key);
        }
}