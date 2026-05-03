import java.io.*;
import java.util.*;

public class FiveDigitDictionary implements Dictionary {
    private Map<String, String> data = new HashMap<>();

    @Override
    public boolean isValidKey(String key) {
        if (key == null || key.length() != 5) return false;
        return key.matches("\\d{5}");
    }

    @Override
    public boolean addEntry(String key, String value) {
        if (!isValidKey(key)) {
            System.out.println("Ошибка: Ключ должен содержать ровно 5 цифр.");
            return false;
        }
        data.put(key, value);
        return true;
    }

    @Override
    public String search(String key) {
        return data.getOrDefault(key, "Запись не найдена");
    }

    @Override
    public boolean deleteEntry(String key) {
        if (data.containsKey(key)) {
            data.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public void loadFromFile(String filename) throws Exception {
        data.clear();
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("=", 2);
            if (parts.length == 2) {
                data.put(parts[0].trim(), parts[1].trim());
            }
        }
        reader.close();
    }

    @Override
    public void saveToFile(String filename) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Map.Entry<String, String> entry : data.entrySet()) {
            writer.write(entry.getKey() + "=" + entry.getValue());
            writer.newLine();
        }
        writer.close();
    }

    @Override
    public void printAll() {
        if (data.isEmpty()) {
            System.out.println("Словарь пуст.");
            return;
        }
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}