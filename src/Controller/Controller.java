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
    
    public static Empleado getEmpleado(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Empleado e;
        e = (Empleado) session.get(Empleado.class, userName);
        tx.commit();
        session.close();
        return e;
    }
    
    public static void modifyEmpleado(Empleado empleado, String tipoCambio, String cambio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        switch (tipoCambio) {
            case "nombrecompleto":
            empleado.setNombrecompleto(cambio);
            break;
            case "telefono":
            empleado.setTelefono(cambio);
            break;
            case "password":
            empleado.setPassword(cambio);
        }
        session.update(empleado);
        System.out.println("User successfully modified");
        
        tx.commit();
        session.close();
    }
    
    public static void deleteEmpleado(Empleado empleado) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(empleado);
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
    public static void addHistorial(EMpleado empleado, String tipo, String fechahora) {
        Transaction tx;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        Historial historial = new Historial(empleado, tipo, fechahora);
        session.save(historial);
        System.out.println("Historial created correctly");
        tx.commit();
        session.close();
    }

    public static Historial getHistorial(String idEvento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Historial h;
        h = (Historial) session.get(Historial.class, idEvento);
        tx.commit();
        session.close();
        return h;
    }

    public static void deleteHistorial(Historial historial) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(historial);
        System.out.println("Historial deleted correctly");
        tx.commit();
        session.close();
    }


    // Incidencias
    public static void addIncidencia(Incidencia incidencia) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(incidencia);
        System.out.println("Incidencia created correctly");
        tx.commit();
        session.close();
    }

    public static Incidencia getIncidencia(String idIncidencia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Incidencia i;
        i = (Incidencia) session.get(Incidencia.class, idIncidencia);
        tx.commit();
        session.close();
        return i;
    }

    public static void deleteIncidencia(Incidencia incidencia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(incidencia);
        System.out.println("Incidencia deleted correctly");
        tx.commit();
        session.close();
    }
}
