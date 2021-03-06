package proyectolena.repositorios;

import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import proyectolena.BDLena.HibernateUtil;
import proyectolena.dominio.DefArticulo;
import proyectolena.dominio.TipoArticulo;

public class RepositorioDefArticulo {
    public void guardar(DefArticulo defArticulo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(defArticulo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error no se pudo guardar, vuelva a intentarlo", null, JOptionPane.ERROR_MESSAGE);
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public List<DefArticulo> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List defArticulos = session.createCriteria(DefArticulo.class).list();
        session.close();
        return defArticulos;
    }

    public List<DefArticulo> findByNombre(String nombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List defArticulos = session.createCriteria(DefArticulo.class)
                .add(Restrictions.eq("nombre", nombre))
                .list();
        session.close();
        return defArticulos;
    }
    public List<DefArticulo> findByTipoArticulo(TipoArticulo tipo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List defArticulos = session.createCriteria(DefArticulo.class)
                .add(Restrictions.eq("tipoArticulo", tipo))
                .list();
        session.close();
        return defArticulos;
    }
}
