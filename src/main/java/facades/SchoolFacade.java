package facades;

import entities.Semester;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

public class SchoolFacade {

    private static SchoolFacade instance;
    private static EntityManagerFactory emf;

    private SchoolFacade() {} // Private constructor

    public static SchoolFacade getInstance(EntityManagerFactory _emf) { //dependency injection
        if(instance == null) {
            emf = _emf;
            instance = new SchoolFacade();
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
    public Teacher addTeacherToSemester(long teacherId, long semesterId) {
        EntityManager em = emf.createEntityManager();

        Teacher teacher = em.find(Teacher.class, teacherId);
        Semester semester = em.find(Semester.class, semesterId);

        em.getTransaction().begin();
            teacher.assignSemester(semester);
        em.getTransaction().commit();
        em.close();
        return teacher;
    }

    // Remove teacher from a semester
    public void removeTeacherFromSemester(long teacherId, long semesterId) {
        EntityManager em = emf.createEntityManager();

        Teacher teacher = em.find(Teacher.class, teacherId);
        Semester semester = em.find(Semester.class, semesterId);

        em.getTransaction().begin();
        semester.removeTeacher(teacher);
        em.getTransaction().commit();
        em.close();
    }

    // Update a semesters name and description
    public Semester updateSemesterNameAndDesc(long semesterId, String description, String name) {
        EntityManager em = emf.createEntityManager();

        Semester semester = em.find(Semester.class, semesterId);

        em.getTransaction().begin();
            semester.setDescription(description);
            semester.setName(name);
        em.getTransaction().commit();
        em.close();
        return semester;
    }

    // Get all students from a semester
    public Set<Student> getStudentsFromSemester(long semesterId) {
        EntityManager em = emf.createEntityManager();
        Semester semester = em.find(Semester.class, semesterId);
        if(semester != null) {
            return semester.getStudents();
        }
        return null;
    }

    // Get all students by a specific teacher - UNDER Persistence -> højre klik på PU -> åbn console -> test JPQL i console
    public List<Student> getStudentsFromTeacher(long teacherId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s " +
                    "JOIN s.currentsemester sem JOIN sem.teachers t " +
                    "WHERE t.id = :id", Student.class);
            query.setParameter("id", teacherId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
