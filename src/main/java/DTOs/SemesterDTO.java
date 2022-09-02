package DTOs;

public class SemesterDTO {
    private long semesterId;
    private String semesterDescription;
    private String semesterName;
    private long semesterStudentCount;

    public SemesterDTO(long semesterId, String semesterDescription, String semesterName, long semesterStudentCount) {
        this.semesterId = semesterId;
        this.semesterDescription = semesterDescription;
        this.semesterName = semesterName;
        this.semesterStudentCount = semesterStudentCount;
    }

    public long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(long semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesterDescription() {
        return semesterDescription;
    }

    public void setSemesterDescription(String semesterDescription) {
        this.semesterDescription = semesterDescription;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public long getSemesterStudentCount() {
        return semesterStudentCount;
    }

    public void setSemesterStudentCount(long semesterStudentCount) {
        this.semesterStudentCount = semesterStudentCount;
    }

    @Override
    public String toString() {
        return "SemesterDTO{" +
                "semesterId=" + semesterId +
                ", semesterDescription='" + semesterDescription + '\'' +
                ", semesterName='" + semesterName + '\'' +
                ", semesterStudentCount=" + semesterStudentCount +
                '}';
    }
}
