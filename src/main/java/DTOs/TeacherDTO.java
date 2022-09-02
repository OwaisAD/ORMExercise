package DTOs;

public class TeacherDTO {

    private String firstName;
    private String lastName;
    private long teacherId;
    private long amountOfSemesters;

    public TeacherDTO(String firstName, String lastName, long teacherId, long amountOfSemesters) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.teacherId = teacherId;
        this.amountOfSemesters = amountOfSemesters;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public long getAmountOfSemesters() {
        return amountOfSemesters;
    }

    public void setAmountOfSemesters(int amountOfSemesters) {
        this.amountOfSemesters = amountOfSemesters;
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", teacherId=" + teacherId +
                ", amountOfSemesters=" + amountOfSemesters +
                '}';
    }
}
