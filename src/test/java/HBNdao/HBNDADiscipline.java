package HBNdao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hbn.HibernateUtil5;
import model.Discipline;

/**
 * @author Dvukhhlavov D.E.
 */
public class HBNDADiscipline {
	// https://www.javaguides.net/2019/02/hibernate-5-create-read-update-and-delete-operations-example.html
	// Метод получения ArrayList<> объектов из таблицы
	public static List<Discipline> getAll() {

		List<Discipline> disciplines = new ArrayList<>();
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {

			disciplines = session.createQuery("from Discipline", model.Discipline.class).list();

		} catch (Exception e) {
			System.err.println("=== Disciplines#getAll - get all records ===");
			e.printStackTrace();
		}
		return disciplines;
	}

	// Метод добавления Insert
	public static boolean insert(Discipline newDiscipline) {
		boolean res = false;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			System.out.println(newDiscipline);

			session.save(newDiscipline);

			// commit transaction
			transaction.commit();
			res = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== Disciplines#insert - problem ===");
			res = false;
			// e.printStackTrace();
		}
		return res;
	}

	
	public static long findBySName(String codDiscipline) {
		List<Discipline> disciplines = new ArrayList<>();
		long id = -1;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			//Внимательно пишем названия КЛАССОВ!!! После from НЕ ТАБЛИЦА БД, 
			// а название класса JAVA, т.е. РЕГИСТР ИМЕЕТ ЗНАЧЕНИЕ!!!
			String testHQL = "from Discipline d where d.codD = \'" + codDiscipline + "\'";
			System.out.println("SQL " + testHQL);
			disciplines = session.createQuery(testHQL, model.Discipline.class).list();

			if (!disciplines.isEmpty()) {
				id = disciplines.get(0).getId();
			}
		} catch (Exception e) {
			System.err.println("=== Disciplines#findBySName - find id by name  ===");
			e.printStackTrace();
		}

		return id;
	}

	public static Discipline getByID(long id) {
		System.out.println("getByID: id to find = " + id);
		Discipline discipline = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get Discipline entity using get() method
			discipline = session.get(model.Discipline.class, (int) id);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== Disciplines#getByID - find Object by id  ===");
			e.printStackTrace();
		}
		return discipline;
	}

	// Метод изменения формирует Update
	public static boolean update(long id, Discipline newDiscipline) {
		boolean res = false;
		// Изначально предполагается, что іd принадлежит одной из записей!!!
		// Это проверяется где-то в другом месте перед вызовом метода
		System.out.println("ID for update = " + id);
		Discipline discipline = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get Discipline entity using get() method
			discipline = session.get(model.Discipline.class, (int) id);
			System.out.println("OLD " + discipline);
			System.out.println("NEW " + newDiscipline);

			// update Discipline Обновляем все, кроме id
			discipline.setCodD(newDiscipline.getCodD());
			discipline.setLect(newDiscipline.getLect());
			discipline.setLab(newDiscipline.getLab());
			discipline.setTestType(newDiscipline.getTestType());
			
			// Save changes into database
			session.update(discipline);

			// commit transaction
			transaction.commit();
			res = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== Disciplines#update - problem  ===");
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
		Discipline discipline = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get Stud entity using get() method
			discipline = session.get(model.Discipline.class, (int) id);

			// Delete from database
			//27/04/22 - ТАКЖЕ УДАЛЯЕТСЯ ВСЯ СВЯЗАННАЯ ИНФОРМАЦИЯ
			session.delete(discipline);

			// commit transaction
			transaction.commit();
			res = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== Disciplines#delete - problem  ===");
			System.err.println(e.getLocalizedMessage());
			res = false;
		}
		return res;
	}

}
