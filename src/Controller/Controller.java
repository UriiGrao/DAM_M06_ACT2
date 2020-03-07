/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Empleado;
import Models.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author uriishii
 */
public class Controller {

    // Empleado
    public static void addEmpleado(String userName, String pass, String completName, String phone) {
        Transaction tx;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        Empleado e = new Empleado(userName, pass, completName, phone);
        session.save(e);
        System.out.println("User created correctly");
        tx.commit();
        session.close();
    }

    public static void getEmpleado(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Empleado e;
        e = (Empleado) session.get(Empleado.class, userName);
        System.out.println("User caught correctly.");
        tx.commit();
        session.close();
    }

    public static void deleteEmpleado(String userName) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Empleado e = new Empleado(userName);
        session.delete(e);
        System.out.println("User deleted correctly");
        tx.commit();
        session.close();
    }

    public static void consultaEmpleado(String c) {
        System.out.println("Out of Query");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery(c);
        List results = q.list();
        Iterator empleadoIterator = results.iterator();
        while (empleadoIterator.hasNext()) {
            Empleado empleado = (Empleado) empleadoIterator.next();
            System.out.println(empleado.getNombreusuario());
        }
        session.close();
    }

    //Historial
    public static void addHistorial() {

    }
}
