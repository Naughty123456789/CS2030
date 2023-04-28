import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Roster roster = new Roster("9999");
        int numRecords = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numRecords; i++) {     
            roster = roster.add(sc.next(), sc.next(), sc.next(), sc.next());
        }

        String grades = "";
        while (sc.hasNext()) {  
            grades += roster.getGrade(sc.next(), sc.next(), sc.next()) + "\n";  
        }
        grades = grades.trim();
        System.out.println(grades);
    }
}
