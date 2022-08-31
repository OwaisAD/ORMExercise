package facades;

import entities.Semester;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class TeachingFacade {

    private static TeachingFacade instance;
    private static EntityManagerFactory emf;

    private TeachingFacade() {} // Private constructor

    public static TeachingFacade getInstance(EntityManagerFactory _emf) { //dependency injection
        if(instance == null) {
            emf = _emf;
            instance = new TeachingFacade();
        }
        return instance;
    }

    // *** Facade metoder
    public List<Student> getStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);    //klassen sættes på for at vise at vi forventer at få tilbage objekter af typen student
            return query.getResultList();
        } finally {
            em.close();
        }
        //TypedQuery ved man hvad man får tilbage contra Query hvor man får typen Object tilbage
    }

    public Student createStudent(String firstName, String lastName) {
        EntityManager em = emf.createEntityManager();
        Student newStudent = new Student(firstName, lastName);
            em.getTransaction().begin();
            em.persist(newStudent); //nu er vores objekt managed
            em.getTransaction().commit();
            em.close();
        return newStudent;
    }

    public Semester createSemester(String name, String description) {
        EntityManager em = emf.createEntityManager();
        Semester newSemester = new Semester(name, description);
        em.getTransaction().begin();
        em.persist(newSemester);
        em.getTransaction().commit();
        em.close();
        return newSemester;
    }

    public Teacher createTeacher(String firstName, String lastName) {
        EntityManager em = emf.createEntityManager();
        Teacher newTeacher = new Teacher(firstName, lastName);
        em.getTransaction().begin();
        em.persist(newTeacher);
        em.getTransaction().commit();
        em.close();
        return newTeacher;
    }

    // Add a student to a semester
    public Student addStudentToSemester(long studentId, long semesterId) {
        EntityManager em = emf.createEntityManager();

        Student student = em.find(Student.class, studentId);
        Semester semester = em.find(Semester.class, semesterId);

        em.getTransaction().begin();
            student.assignCurrentsemester(semester);
        em.getTransaction().commit();
        em.close();
        return student;
    }

    // Add a teacher to a semester


    // Remove teacher from a semester

    // Update a semesters name and description

    // Get all students from a semester

    // Get all students by a specific teacher
}
