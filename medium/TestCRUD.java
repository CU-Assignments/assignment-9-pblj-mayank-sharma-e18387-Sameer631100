package com.example.hibernate;

public class TestCRUD {

    public static void main(String[] args) {
        StudentDao dao = new StudentDao();

        // Create
        Student student = new Student("Alice", 22);
        dao.saveStudent(student);
        System.out.println("Saved: " + student);

        // Read
        Student fetched = dao.getStudent(student.getId());
        System.out.println("Fetched: " + fetched);

        // Update
        fetched.setName("Alice Johnson");
        fetched.setAge(23);
        dao.updateStudent(fetched);
        System.out.println("Updated: " + dao.getStudent(fetched.getId()));

        // Delete
        dao.deleteStudent(fetched.getId());
        System.out.println("Deleted student with ID: " + fetched.getId());

        HibernateUtil.shutdown();
    }
}
