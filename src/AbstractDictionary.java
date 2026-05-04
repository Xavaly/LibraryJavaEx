import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDictionary implements Dictionary {
    protected Map<String, String> data = new HashMap<>();

    @Override
    public boolean addEntry(String key, String value) {
        if (key == null || value == null || !isValidKey(key)) {
            System.out.println("Ошибка: Некорректные данные для добавления.");
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
        return data.remove(key) != null;
    }

    @Override
    public void loadFromFile(String filename) throws Exception {
        data.clear();
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
            return;
        }

        // Исправлено: FileInputStream + InputStreamReader + UTF-8
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    data.put(parts[0].trim(), parts[1].trim());
                }
            }
        }
    }

    @Override
    public void saveToFile(String filename) throws Exception {
        // Исправлено: FileOutputStream + OutputStreamWriter + UTF-8
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {

            for (Map.Entry<String, String> entry : data.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        }
    }

    @Override
    public void printAll() {
        if (data.isEmpty()) {
            System.out.println("Словарь пуст.");
            return;
        }
        // Java 8: используем анонимный класс вместо лямбды для forEach, если есть проблемы
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    @Override
    public abstract boolean isValidKey(String key);
}