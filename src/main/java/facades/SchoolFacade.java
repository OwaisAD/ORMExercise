package facades;

import DTOs.StudentInfoDTO;
import entities.Semester;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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

    // Find all Students in the System with the first name Anders
    public List<Student> studentsWithSpecificFirstName(String firstName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.firstname = :name", Student.class);
            query.setParameter("name", firstName);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Find (using JPQL) all Students in the system with the last name And
    public List<Student> studentsWithSpecificLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.lastname = :name", Student.class);
            query.setParameter("name", lastName);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


    // Find (using JPQL) the total number of students, for a semester given the semester name as a parameter.
    public Long amountOfStudentsForASemester(String semesterName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT count(s) as amount FROM Student s JOIN s.currentsemester sem WHERE sem.name = :name", Long.class);
            query.setParameter("name", semesterName);
            return query.getSingleResult();
        }finally {
            em.close();
        }
    }

    // Find (using JPQL) the total number of students that has a particular teacher.
    public Long amountOfStudentsWithASpecificTeacher(long teacherId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT count(s) as amount FROM Student s JOIN s.currentsemester sem JOIN sem.teachers t WHERE t.id = :id", Long.class);
            query.setParameter("id", teacherId);
            return query.getSingleResult();
        }finally {
            em.close();
        }
    }

    // Find (using JPQL) the teacher who teaches the most semesters. SKAL JEG LAVE EN DTO som return type?
    public void teacherWhoTeachesTheMostSemesters() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Teacher> query = em.createQuery("SELECT count(s) as amount, t.firstname from Semester s JOIN s.teachers t GROUP BY t.id ORDER BY amount DESC", Teacher.class);
            // hvordan finder man ud af hvilken objekt type vi har med at gøre?
            System.out.println(query.getResultList());
            //return ;
        } finally {
            em.close();
        }
    }

    // Find the semester that has the fewest students
    /*public List<Semester> semesterWithFewestStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Semester> query = em.createQuery("SELECT count(sem) as amount, sem.name FROM Semester sem JOIN sem.students st GROUP BY sem.id ORDER BY amount ASC", Semester.class);
            return query.getResultList();

        } finally {
            em.close();
        }*/

    public List<StudentInfoDTO> getStudentInfo(int studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<StudentInfoDTO> query = em.createQuery("SELECT NEW DTOs.StudentInfoDTO(CONCAT(s.firstname, ' ', s.lastname), s.id, s.currentsemester.name, s.currentsemester.description) FROM Student s", StudentInfoDTO.class);
            List<StudentInfoDTO> result = query.getResultList();
            return result;
        }finally {
            em.close();
        }
    }

    }

