package com.example;

import com.example.model.joined.Developpeur;
import com.example.model.joined.Employe;
import com.example.model.joined.Manager;
import com.example.model.singletable.Moto;
import com.example.model.singletable.Vehicule;
import com.example.model.singletable.Voiture;
import com.example.model.tableperclass.Electronique;
import com.example.model.tableperclass.Livre;
import com.example.model.tableperclass.Produit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-inheritance");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();


        em.persist(new Voiture("Toyota", 4));
        em.persist(new Moto("Harley", true));


        em.persist(new Developpeur("Alice", "Java"));
        em.persist(new Manager("Bob", 3));


        em.persist(new Livre("Clean Code", "Robert C. Martin"));
        em.persist(new Electronique("Smartphone", 24));

        em.getTransaction().commit();
        em.clear();

        System.out.println("--- Liste des véhicules ---");
        em.createQuery("SELECT v FROM Vehicule v", Vehicule.class)
                .getResultList()
                .forEach(v -> System.out.println(v.getClass().getSimpleName() + ": " + v.getMarque()));

        System.out.println("--- Liste des employés ---");
        em.createQuery("SELECT e FROM Employe e", Employe.class)
                .getResultList()
                .forEach(e -> System.out.println(e.getClass().getSimpleName() + ": " + e.getNom()));

        System.out.println("--- Liste des produits ---");
        em.createQuery("SELECT p FROM Produit p", Produit.class)
                .getResultList()
                .forEach(p -> System.out.println(p.getClass().getSimpleName() + ": " + p.getNom()));

        em.close();
        emf.close();
    }
}