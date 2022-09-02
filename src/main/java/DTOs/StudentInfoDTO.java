package DTOs;

public class StudentInfoDTO {
    private String fullName;
    private long studentId;
    private String thisSemesterName;
    private String thisSemesterDescription;

    public StudentInfoDTO(String fullName, long studentId, String thisSemesterName, String thisSemesterDescription) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.thisSemesterName = thisSemesterName;
        this.thisSemesterDescription = thisSemesterDescription;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getThisSemesterName() {
        return thisSemesterName;
    }

    public void setThisSemesterName(String thisSemesterName) {
        this.thisSemesterName = thisSemesterName;
    }

    public String getThisSemesterDescription() {
        return thisSemesterDescription;
    }

    public void setThisSemesterDescription(String thisSemesterDescription) {
        this.thisSemesterDescription = thisSemesterDescription;
    }

    @Override
    public String toString() {
        return "StudentInfoDTO{" +
                "fullName='" + fullName + '\'' +
                ", studentId=" + studentId +
                ", thisSemesterName='" + thisSemesterName + '\'' +
                ", thisSemesterDescription='" + thisSemesterDescription + '\'' +
                '}';
    }
}
