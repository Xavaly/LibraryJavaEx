public interface Dictionary {
    void loadFromFile(String filename) throws Exception;
    void saveToFile(String filename) throws Exception;
    boolean addEntry(String key, String value);
    String search(String key);
    boolean deleteEntry(String key);
    void printAll();
    boolean isValidKey(String key);
}