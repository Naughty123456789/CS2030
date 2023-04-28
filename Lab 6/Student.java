import java.util.Optional;

class Student extends KeyableMap<Module> implements Keyable {
    private final String studentName;
    private final KeyableMap<Module> modules;

    public Student(String studentName) {
        super(studentName);
        this.modules = new KeyableMap<Module>(studentName);
        this.studentName = studentName;
    }

    public Student(String studentName, KeyableMap<Module> modules) {
        super(studentName);
        this.studentName = studentName;
        this.modules = modules;
    }

    public String getKey() {
        return this.studentName;
    }

    public KeyableMap<Module> getModules() {
        return modules;
    }

    public Student put(Module module) {
        return new Student(studentName, modules.put(module));
    }

    public Optional<Module> get(String studentName) {
        return modules.get(studentName);
    }

    @Override
    public String toString() {
        return this.studentName + "" + modules.toString();
    }


}


