import entities.Student;
import entities.Teacher;
import facades.SchoolFacade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        SchoolFacade schoolFacade = SchoolFacade.getInstance(emf);

        List<Student> studentList = schoolFacade.getStudents();
        System.out.println("List of students in the db");
        for (Student student : studentList) {
            System.out.println(student.toString());
        }

        // Create new students
        /*Student newStudent = schoolFacade.createStudent("Owais", "Dashti");
        System.out.println(newStudent);*/

        // Create new semester
        /*Semester newSemester = schoolFacade.createSemester("Computer Science 4. sem", "CLcos-v15e");
        System.out.println(newSemester);*/

        // Create a new teacher
        /*Teacher newTeacher = schoolFacade.createTeacher("Elon", "Musk");
        System.out.println(newTeacher);*/

        // Add a student to a semester
        /*Student studentToSemester = schoolFacade.addStudentToSemester(6, 3);
        System.out.println("Adding a student to a semester\n" + studentToSemester);*/

        // Add a teacher to a semester
        /*Teacher teacherToSemester = schoolFacade.addTeacherToSemester(1, 2);
        System.out.println("Adding a teacher to a semester\n" + teacherToSemester);*/

        // Remove teacher from a semester
        /*schoolFacade.removeTeacherFromSemester(3,1);*/

        // Update a semesters name and description - virker, men skal testes og udbedres mht. mulige inputs
        /*Semester changeSemesterData = schoolFacade.updateSemesterNameAndDesc(4,"Datamatiker 4. sem", "CLdat-41e");
        System.out.println(changeSemesterData);*/

        // Get all students from a semester MANGLER
        /*Set<Student> studentSetFromSemester = schoolFacade.getStudentsFromSemester(3);
        System.out.println("List of students from a specific semester:");
        for (Student student : studentSetFromSemester) {
            System.out.println(student);
        }*/

        // Get all students by a specific teacher
        /*List<Student> listStudentsOfSpecificTeacher = schoolFacade.getStudentsFromTeacher(1);
        System.out.println("Students of specific teacher:");
        for (Student student : listStudentsOfSpecificTeacher) {
            System.out.println(student);
        }*/


        // Get all students with firstname Anders
        /*List<Student> listOfStudentWithSpecificFirstName = schoolFacade.studentsWithSpecificFirstName("Anders");
        System.out.println("Student with first name Anders");
        for (Student student : listOfStudentWithSpecificFirstName) {
            System.out.println(student);
        }*/


        // Get all students with lastname And
        /*List<Student> listOfStudentWithSpecificLastName = schoolFacade.studentsWithSpecificLastName("And");
        System.out.println("Student with last name And");
        for (Student student : listOfStudentWithSpecificLastName) {
            System.out.println(student);
        }*/


        //total number of students, for a semester given the semester name as a parameter.
        String semesterName = "CLdat-b14e";
        long amountOfStudentsForASemester = schoolFacade.amountOfStudentsForASemester(semesterName);
        System.out.println("For the semester: " + semesterName + ", there are the following amount of students: " + amountOfStudentsForASemester);



        // the total number of students that has a particular teacher
        long teacherId = 2;
        long amountOfStudentsForTeacher = schoolFacade.amountOfStudentsWithASpecificTeacher(teacherId);
        System.out.println("Teacher with id: " + teacherId + " has the following amount of students: " + amountOfStudentsForTeacher);

        // Find (using JPQL) the teacher who teaches the most semesters.
        Teacher teacher = schoolFacade.teacherWhoTeachesTheMostSemesters();
        System.out.println("Teacher teaching the most semesters:");
        System.out.println(teacher);


        emf.close();
    }
}
