import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> toDoList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String code = "";
        String text = "";
        String input;
        int index = 0;
        toDoList.add("Первое дело");
        toDoList.add("Vtoroe delo");

        do {
            System.out.print("Введите команду и строку: ");
            input = scanner.nextLine().trim();

            String pattern = "^([A-Z]+)\\s*(\\d*)?(\\s+.*)?$";

            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(input);

            if (m.find()) {
                code = m.group(1);
                String indexString = m.group(2);
                index = indexString.isEmpty() ? -1 : Integer.parseInt(indexString);
                text = m.group(3);

            } else {
                System.out.println("Некорректный формат введенной строки!");
            }

            switch (code) {
                case "LIST" -> {
                    // Выводим список задач
                    System.out.println("Список задач:");
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.printf("%d, %s%n", i + 1, toDoList.get(i));
                    }
                }
                case "ADD" -> {
                    // Добавляем задачу в список
                    toDoList.add(text.trim());
                    System.out.println("Добавлена задача " + text.trim());
                }
                case "EDIT" -> {
                    // Редактируем задачу в списке
                    if (index == -1) {
                        System.out.println("Не указан номер задачи!");
                    } else {
                        toDoList.set(--index, text.trim());
                        System.out.println("Отредактирована задача с номером " + ++index + " на " + text.trim());
                    }
                }
                case "DELETE" -> {
                    // Удаляем задачу из списка
                    if (index == -1) {
                        System.out.println("Не указан номер задачи!");
                    } else {
                        toDoList.remove(index);
                        System.out.println("Удалена задача под номером " + index);
                    }
                }
                case "END" ->
                    // Завершаем программу
                        System.out.println("Программа завершена.");
                default -> System.out.println("Некорректная команда!");
            }
        } while (!input.startsWith("END"));
    }
}