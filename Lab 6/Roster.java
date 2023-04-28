import java.util.Optional;

class Roster extends KeyableMap<Student> implements Keyable {
    private final String studyYear;
    private final KeyableMap<Student> students;

    public Roster(String studyYear) {
        super(studyYear);
        this.studyYear = studyYear;
        this.students = new KeyableMap<Student>(studyYear);
    } 

    public Roster(String studyYear, KeyableMap<Student> students) {
        super(studyYear);
        this.studyYear = studyYear;
        this.students = students;
    }

    public String getKey() {
        return this.studyYear;
    }

    public KeyableMap<Student> getStudents() {
        return this.students;
    }

    public Roster put(Student student) {
        return new Roster(studyYear, students.put(student));
    }

    public Optional<Student> get(String studentName) {
        return students.get(studentName);
    }

    public String getGrade(String studentName, String moduleCode, String assessmentTitle) {

        return students.get(studentName)
        .flatMap(student -> student.get(moduleCode))
        .flatMap(module -> module.get(assessmentTitle))
        .map(assessment -> assessment.getGrade())
        .orElse("No such record: " + studentName + " " + moduleCode + " " + assessmentTitle);
    }

    public  Roster add(String studentName, String moduleCode, 
        String assessmentTitle, String grade) {

        Student existingStudent = students.get(studentName).orElse(new Student(studentName));
        Module existingModule = existingStudent.getModules()
            .get(moduleCode).orElse(new Module(moduleCode));

        Module updatedModule = existingModule.put(new Assessment(assessmentTitle, grade));
        Student updatedStudent = existingStudent.put(updatedModule);

        KeyableMap<Student> updatedStudents = students.put(updatedStudent);
        return new Roster(this.studyYear, updatedStudents);     
    }
    

    public String toString() {
        return this.studyYear + "" + students.toString();
    }


}