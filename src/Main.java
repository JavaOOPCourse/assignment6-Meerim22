import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> students = new HashMap<>();

        // ====================== TASK 1 ======================
        // Добавь минимум 5 студентов (ключ = ID)
        // Сделай минимум два студента с одинаковым GPA (для Task 3)
        students.put("S01", new Student("Alice", 3.8, 20));
        students.put("S02", new Student("Bob", 3.5, 21));
        students.put("S03", new Student("Charlie", 3.9, 19));
        students.put("S04", new Student("David", 3.5, 22)); // same GPA as Bob
        students.put("S05", new Student("Eve", 3.2, 20));
        students.put("S06", new Student("Frank", 3.7, 21));

        // Напечатай всех студентов (ID + объект)
        System.out.println("=== Task 1: All Students ===");
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " -> " + entry.getValue());
        }

        // Найди студента по ID и выведи его
        System.out.println("\n--- Find student by ID (S03) ---");
        System.out.println("Found: " + students.get("S03"));

        // Удали одного студента по ID
        System.out.println("\n--- Remove student (S06) ---");
        students.remove("S06");
        System.out.println("Removed S06. Total students now: " + students.size());

        // Обнови GPA у одного студента
        System.out.println("\n--- Update GPA for S01 ---");
        Student alice = students.get("S01");
        if (alice != null) {
            alice.setGpa(4.0);
            System.out.println("Updated S01: " + alice);
        }

        // ====================== SORTING (IMPORTANT) ======================
        // Создай ArrayList из всех студентов (students.values())
        List<Student> studentList = new ArrayList<>(students.values());

        // 6a: Отсортируй по GPA (natural ordering) и выведи
        Collections.sort(studentList);
        System.out.println("\n=== Sorted by GPA (Natural Ordering) ===");
        for (Student s : studentList) {
            System.out.println(s);
        }

        // 6b: Отсортируй по имени (Comparator) и выведи
        studentList.sort(Comparator.comparing(Student::getName));
        System.out.println("\n=== Sorted by Name (Comparator) ===");
        for (Student s : studentList) {
            System.out.println(s);
        }

        // ====================== TASK 2 ======================
        System.out.println("\n=== Task 2: Top 3 by GPA ===");
        // Создай новый список, отсортируй по GPA по убыванию, выведи первые 3
        List<Student> topStudents = new ArrayList<>(students.values());
        topStudents.sort(Comparator.comparing(Student::getGpa).reversed());
        for (int i = 0; i < Math.min(3, topStudents.size()); i++) {
            System.out.println((i + 1) + ". " + topStudents.get(i));
        }

        // ====================== TASK 3 ======================
        System.out.println("\n=== Task 3: Students with same GPA ===");
        // Сгруппируй студентов по GPA и выведи только те, где больше 1 студента
        HashMap<Double, List<String>> gpaMap = new HashMap<>();
        for (Student s : students.values()) {
            gpaMap.putIfAbsent(s.getGpa(), new ArrayList<>());
            gpaMap.get(s.getGpa()).add(s.getName());
        }
        for (Map.Entry<Double, List<String>> entry : gpaMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("GPA " + entry.getKey() + " -> " + String.join(", ", entry.getValue()));
            }
        }

        // ====================== TASK 4 ======================
        System.out.println("\n=== Task 4: Courses ===");
        HashMap<Course, List<Student>> courseMap = new HashMap<>();
        // Создай 2–3 курса, добавь студентов, выведи всё
        Course javaCourse = new Course("Java");
        Course pythonCourse = new Course("Python");

        List<Student> javaStudents = new ArrayList<>();
        javaStudents.add(students.get("S01"));
        javaStudents.add(students.get("S02"));
        
        List<Student> pythonStudents = new ArrayList<>();
        pythonStudents.add(students.get("S03"));
        pythonStudents.add(students.get("S04"));
        pythonStudents.add(students.get("S05"));

        courseMap.put(javaCourse, javaStudents);
        courseMap.put(pythonCourse, pythonStudents);

        for (Map.Entry<Course, List<Student>> entry : courseMap.entrySet()) {
            System.out.println(entry.getKey() + " has " + entry.getValue().size() + " students:");
            for (Student s : entry.getValue()) {
                System.out.println("  - " + s);
            }
        }

        // ====================== TASK 5 ======================
        System.out.println("\n=== Task 5: GPA desc + Name ===");
        // Создай Comparator (GPA убывание → если равно, то имя возрастание) и отсортируй
        List<Student> task5List = new ArrayList<>(students.values());
        task5List.sort(Comparator.comparing(Student::getGpa).reversed().thenComparing(Student::getName));
        for (Student s : task5List) {
            System.out.println(s);
        }
    }
}
