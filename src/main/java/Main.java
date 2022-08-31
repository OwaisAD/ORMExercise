import entities.Semester;
import entities.Student;
import entities.Teacher;
import facades.TeachingFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        TeachingFacade teachingFacade = TeachingFacade.getInstance(emf);

        List<Student> studentList = teachingFacade.getStudents();
        for (Student student : studentList) {
            System.out.println(student.toString());
        }

        // Create new students
        Student newStudent = teachingFacade.createStudent("Owais", "Dashti");
        System.out.println(newStudent);

        // Create new semester
        Semester newSemester = teachingFacade.createSemester("Computer Science 4. sem", "CLcos-v15e");
        System.out.println(newSemester);

        // Create a new teacher
        Teacher newTeacher = teachingFacade.createTeacher("Elon", "Musk");
        System.out.println(newTeacher);

        // Add a student to a semester MANGLER
        Student studentToSemester = teachingFacade.addStudentToSemester(7, 4);
        System.out.println("Adding a student to a semester\n" + studentToSemester);

        // Add a teacher to a semester MANGLER

        // Remove teacher from a semester MANGLER

        // Update a semesters name and description MANGLER

        // Get all students from a semester MANGLER

        // Get all students by a specific teacher MANGLER

        emf.close();
    }
}
