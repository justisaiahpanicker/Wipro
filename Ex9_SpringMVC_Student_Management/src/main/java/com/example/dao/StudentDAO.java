package com.example.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Student;

/**
 * Step 7: DAO class that uses the JPA EntityManager for all database operations.
 * Spring injects the EntityManager and wraps each method in a transaction.
 */
@Repository
@Transactional
public class StudentDAO {

    @PersistenceContext
    private EntityManager em;

    /** Add a new student. */
    public void save(Student student) {
        em.persist(student);
    }

    /** Display all students. */
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return em.createQuery("SELECT s FROM Student s ORDER BY s.regno", Student.class)
                 .getResultList();
    }

    /** Search a student by ID (returns null if not found). */
    @Transactional(readOnly = true)
    public Student findById(int regno) {
        return em.find(Student.class, regno);
    }

    /** Update student details. */
    public void update(Student student) {
        em.merge(student);
    }

    /** Delete a student by ID. Returns false if no such student exists. */
    public boolean deleteById(int regno) {
        Student s = em.find(Student.class, regno);
        if (s == null) {
            return false;
        }
        em.remove(s);
        return true;
    }
}
