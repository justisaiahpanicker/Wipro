package inventory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProductDao {

    private SessionFactory factory;

    public ProductDao(SessionFactory factory) {
        this.factory = factory;
    }

    // INSERT
    public void addProduct(Product product) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.persist(product);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    // VIEW - One Product
    public Product getProduct(int id) {
        Session session = factory.openSession();
        try {
            return session.get(Product.class, id);
        } finally {
            session.close();
        }
    }

    // VIEW - All Products
    public List<Product> getAllProducts() {
        Session session = factory.openSession();
        try {
            return session
                    .createQuery("from Product order by id", Product.class)
                    .getResultList();
        } finally {
            session.close();
        }
    }

    // UPDATE
    public void updateProduct(int id, String name, String category, double price, int quantity) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.setName(name);
                product.setCategory(category);
                product.setPrice(price);
                product.setQuantity(quantity);
            }
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    // DELETE
    public void deleteProduct(int id) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.remove(product);
            }
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
