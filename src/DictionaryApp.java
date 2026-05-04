import java.util.Scanner;

public class DictionaryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;

        // Внешний цикл: выбор типа словаря
        while (!exitProgram) {
            Dictionary dictionary = null;
            String fileName = "";

            System.out.println("\n=== Выбор типа словаря ===");
            System.out.println("1. Словарь (4 латинские буквы)");
            System.out.println("2. Словарь (5 цифр)");
            System.out.println("3. Выйти из программы");
            System.out.print("Ваш выбор: ");
            String choice = scanner.nextLine();

            if ("3".equals(choice)) {
                System.out.println("Выход из программы. До свидания!");
                exitProgram = true;
                break;
            }

            if ("1".equals(choice)) {
                dictionary = new FourLetterDictionary();
                fileName = "dictionary_4letters.txt";
            } else if ("2".equals(choice)) {
                dictionary = new FiveDigitDictionary();
                fileName = "dictionary_5digits.txt";
            } else {
                System.out.println("Неверный выбор. Попробуйте снова.");
                continue; // Возврат к началу внешнего цикла
            }

            // Загрузка файла
            try {
                dictionary.loadFromFile(fileName);
            } catch (Exception e) {
                System.err.println("Ошибка загрузки файла: " + e.getMessage());
                continue;
            }

            // Внутренний цикл: работа с выбранным словарём
            while (true) {
                System.out.println("\n--- Меню словаря ---");
                System.out.println("1. Просмотреть весь словарь");
                System.out.println("2. Добавить запись");
                System.out.println("3. Найти запись по ключу");
                System.out.println("4. Удалить запись по ключу");
                System.out.println("5. Сохранить и вернуться к выбору словаря");
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
                            System.out.println("Данные сохранены. Возврат к выбору словаря...");
                            // Выход из внутреннего цикла → возврат к внешнему (выбор словаря)
                            break;
                        default:
                            System.out.println("Неверная команда.");
                            break;
                    }

                    // Если нажали "5" — выходим из внутреннего цикла
                    if ("5".equals(action)) {
                        break;
                    }

                } catch (Exception e) {
                    System.err.println("Произошла ошибка: " + e.getMessage());
                }
            }
        }

        // Закрытие ресурсов только при реальном выходе из программы
        scanner.close();
    }
}