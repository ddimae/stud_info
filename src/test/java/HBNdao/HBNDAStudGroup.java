package HBNdao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hbn.HibernateUtil5;
import model.StudGroup;

/**
 * @author Dvukhhlavov D.E.
 */
public class HBNDAStudGroup {
	// https://www.javaguides.net/2019/02/hibernate-5-create-read-update-and-delete-operations-example.html
	// Метод получения ArrayList<> объектов из таблицы
	public static List<StudGroup> getAll() {

		List<StudGroup> groups = new ArrayList<>();
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {

			groups = session.createQuery("from StudGroup", model.StudGroup.class).list();

		} catch (Exception e) {
			System.err.println("=== StudGroup#getAll - get all records ===");
			e.printStackTrace();
		}
		return groups;
	}

	// Метод добавления Insert
	public static boolean insert(StudGroup newStudGroup) {
		boolean res = false;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			System.out.println(newStudGroup);

			session.save(newStudGroup);

			// commit transaction
			transaction.commit();
			res = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== StudGroup#insert - problem ===");
			res = false;
			// e.printStackTrace();
		}
		return res;
	}

	
	public static long findByName(String codStudGroup) {
		List<StudGroup> groups = new ArrayList<>();
		long id = -1;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			//Внимательно пишем названия КЛАССОВ!!! После from НЕ ТАБЛИЦА БД, 
			// а название класса JAVA, т.е. РЕГИСТР ИМЕЕТ ЗНАЧЕНИЕ!!!
			String testHQL = "from StudGroup sg where sg.cod = \'" + codStudGroup + "\'";
			System.out.println("SQL " + testHQL);
			groups = session.createQuery(testHQL, model.StudGroup.class).list();

			if (!groups.isEmpty()) {
				id = groups.get(0).getIdGr();
			}
		} catch (Exception e) {
			System.err.println("=== StudGroup#findBySName - find id by name  ===");
			e.printStackTrace();
		}

		return id;
	}

	public static StudGroup getByID(long id) {
		System.out.println("getByID: id to find = " + id);
		StudGroup group = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get StudGroup entity using get() method
			group = session.get(model.StudGroup.class, (int) id);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== StudGroup#getByID - find Object by id  ===");
			e.printStackTrace();
		}
		return group;
	}

	// Метод изменения формирует Update
	public static boolean update(long id, StudGroup newStudGroup) {
		boolean res = false;
		// Изначально предполагается, что іd принадлежит одной из записей!!!
		// Это проверяется где-то в другом месте перед вызовом метода
		System.out.println("ID for update = " + id);
		StudGroup group = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get StudGroup entity using get() method
			group = session.get(model.StudGroup.class, (int) id);
			System.out.println("OLD " + group);
			System.out.println("NEW " + newStudGroup);

			// update StudGroup Обновляем все, кроме id
			group.setCod(newStudGroup.getCod());
			group.setSpec(newStudGroup.getSpec());

			// Save changes into database
			session.update(group);

			// commit transaction
			transaction.commit();
			res = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== StudGroup#update - problem  ===");
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	
	public static boolean delete(long id) {
		boolean res = false;
		// Изначально предполагается, что іd принадлежит одной из записей!!!
		// Это проверяется где-то в другом месте перед вызовом метода
		System.out.println("ID for delete = " + id);
		StudGroup group = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get Stud entity using get() method
			group = session.get(model.StudGroup.class, (int) id);

			// Delete from database
			//27/04/22 - ТАКЖЕ УДАЛЯЕТСЯ ВСЯ СВЯЗАННАЯ ИНФОРМАЦИЯ
			session.delete(group);

			// commit transaction
			transaction.commit();
			res = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== StudGroup#delete - problem  ===");
			System.err.println(e.getLocalizedMessage());
			res = false;
		}
		return res;
	}

}
