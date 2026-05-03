import java.util.Scanner;

public class DictionaryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = null;
        String fileName = "";

        System.out.println("Выберите тип словаря:");
        System.out.println("1. Словарь (4 латинские буквы)");
        System.out.println("2. Словарь (5 цифр)");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            dictionary = new FourLetterDictionary();
            fileName = "dictionary_4letters.txt";
        } else if (choice.equals("2")) {
            dictionary = new FiveDigitDictionary();
            fileName = "dictionary_5digits.txt";
        } else {
            System.out.println("Неверный выбор.");
            return;
        }

        try {
            dictionary.loadFromFile(fileName);
        } catch (Exception e) {
            System.out.println("Ошибка загрузки файла: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Просмотреть весь словарь");
            System.out.println("2. Добавить запись");
            System.out.println("3. Найти запись по ключу");
            System.out.println("4. Удалить запись по ключу");
            System.out.println("5. Сохранить и выйти");
            System.out.print("Выберите действие: ");

            String action = scanner.nextLine();

            try {
                switch (action) {
                    case "1":
                        dictionary.printAll();
                        break;
                    case "2":
                        System.out.print("Введите ключ: ");
                        String key = scanner.nextLine();
                        System.out.print("Введите значение (перевод): ");
                        String value = scanner.nextLine();
                        if (dictionary.addEntry(key, value)) {
                            System.out.println("Запись добавлена.");
                        }
                        break;
                    case "3":
                        System.out.print("Введите ключ для поиска: ");
                        String searchKey = scanner.nextLine();
                        System.out.println("Результат: " + dictionary.search(searchKey));
                        break;
                    case "4":
                        System.out.print("Введите ключ для удаления: ");
                        String delKey = scanner.nextLine();
                        if (dictionary.deleteEntry(delKey)) {
                            System.out.println("Запись удалена.");
                        } else {
                            System.out.println("Запись не найдена.");
                        }
                        break;
                    case "5":
                        dictionary.saveToFile(fileName);
                        System.out.println("Данные сохранены. Выход.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Неверная команда.");
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
    }
}