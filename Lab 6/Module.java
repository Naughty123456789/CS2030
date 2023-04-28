import java.util.Optional;

class Module extends KeyableMap<Assessment> implements Keyable {
    private final String moduleCode;
    private final KeyableMap<Assessment> assessments;

    //Constructor
    public Module(String moduleCode) {
        super(moduleCode);
        this.moduleCode = moduleCode;
        this.assessments = new KeyableMap<Assessment>(moduleCode);
    }

    public Module(String moduleCode, KeyableMap<Assessment> assessments) {
        super(moduleCode);
        this.moduleCode = moduleCode;
        this.assessments = assessments;
    }


    public String getKey() {
        return this.moduleCode;
    }

    public Module put(Assessment assessment) {
        return new Module(moduleCode, assessments.put(assessment));
    }

    public Optional<Assessment> get(String moduleCode) {
        return assessments.get(moduleCode);
    }


    public String toString() {
        
        return this.moduleCode + "" + assessments.toString();
    }
}



 