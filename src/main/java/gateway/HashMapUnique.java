package gateway;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

public class HashMapUnique {
    /**
     * Data structure Hashmap using sigleton for only one instanciation
     */
        private static HashMapUnique instance;
        private HashMap<String, Integer> sharedMap = new HashMap<>();

        private HashMapUnique() {

        }

    /**
     * Method for create only one instance of HashMapUnique
     * @return instance
     */
    public static synchronized HashMapUnique getInstance() {
            if (instance == null) {
                instance = new HashMapUnique();
            }
            return instance;
        }

    /**
     * Method for add in HashMap a key and a value
     * @param key the key of HashMap
     * @param value the value of HashMap
     */
    public synchronized void addInMap(String key, Integer value) {
            sharedMap.put(key, value);
        }

    /**
     * Method for remove the value and key of HashMap
     * @param key the key of HashMap
     */
    public synchronized void removeInMap(String key){
            sharedMap.remove(key);
        }

    /**
     * Method for consult the value in HashMap which have the key
     * @param key the key of HashMap
     * @return the value which have the key
     */
        public synchronized Integer getFromMap(String key) {
            return sharedMap.get(key);
        }

    /**
     * Method for show of all values from map in a String
     * @return String
     */
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
