class Assessment implements Keyable {

    private final String lab;
    private final String grade;

    public Assessment(String lab, String grade) {
        this.lab = lab;
        this.grade = grade;
    } 

    public String getGrade() {
        return this.grade;
    }
     
    public String getKey() {
        return this.lab;
    }

    public String toString() {
        return "{" + this.lab + ": " + this.grade + "}";
    }
}

