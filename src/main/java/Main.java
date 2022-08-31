import entities.Semester;
import entities.Student;
import facades.SchoolFacade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        SchoolFacade schoolFacade = SchoolFacade.getInstance(emf);

        List<Student> studentList = schoolFacade.getStudents();
        for (Student student : studentList) {
            System.out.println(student.toString());
        }

        // Create new students
        //Student newStudent = teachingFacade.createStudent("Owais", "Dashti");
        //System.out.println(newStudent);

        // Create new semester
        //Semester newSemester = teachingFacade.createSemester("Computer Science 4. sem", "CLcos-v15e");
        //System.out.println(newSemester);

        // Create a new teacher
        //Teacher newTeacher = teachingFacade.createTeacher("Elon", "Musk");
        //System.out.println(newTeacher);

        // Add a student to a semester
        //Student studentToSemester = teachingFacade.addStudentToSemester(6, 3);
        //System.out.println("Adding a student to a semester\n" + studentToSemester);

        // Add a teacher to a semester
        //Teacher teacherToSemester = teachingFacade.addTeacherToSemester(1, 2);
        //System.out.println("Adding a teacher to a semester\n" + teacherToSemester);

        // Remove teacher from a semester
        schoolFacade.removeTeacherFromSemester(3,1);

        // Update a semesters name and description MANGLER
        Semester changeSemesterData = schoolFacade.updateSemesterNameAndDesc(4,"Datamatiker 4. sem", "CLdat-41e");
        System.out.println(changeSemesterData);

        // Get all students from a semester MANGLER
        List<Student> studentListFromSemester = schoolFacade.getStudents();
        for (Student student : studentListFromSemester) {
            System.out.println(student.toString());
        }

        // Get all students by a specific teacher MANGLER

        emf.close();
    }
}
