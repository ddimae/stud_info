package HBNdao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hbn.HibernateUtil5;
import model.Stud;
import model.StudDopInfo;

/**
 * @author Dvukhhlavov D.E.
 */
public class HBNDAStud {
	// https://www.javaguides.net/2019/02/hibernate-5-create-read-update-and-delete-operations-example.html
	// Метод получения ArrayList<> объектов из таблицы
	public static List<Stud> getAll() {

		List<Stud> students = new ArrayList<>();
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {

			students = session.createQuery("from Stud", model.Stud.class).list();

		} catch (Exception e) {
			System.err.println("=== Students#getAll - get all records ===");
			e.printStackTrace();
		}
		return students;
	}

	// Метод добавления Insert
	public static boolean insert(Stud newStud) {
		boolean res = false;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			System.out.println(newStud);

			session.save(newStud);

			// commit transaction
			transaction.commit();
			res = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== Students#insert - problem ===");
			res = false;
			// e.printStackTrace();
		}
		return res;
	}

	// Метод добавления Insert
	public static boolean insert(Stud newStud, StudDopInfo dopInfo) {
		boolean res = false;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			// ОБЯЗАТЕЛЬНА!!! Будут добавляться данные в обе таблицы
			transaction = session.beginTransaction();
			// 1. Передается студент в доп инфо как владелец. Если такая информация указана!
			if (dopInfo != null) {
				dopInfo.setStud(newStud);
			}
			// 2. Новому студенту устанавливается новая доп информация
			newStud.setDopInfo(dopInfo);
			// 3. Новый студент "отправляется на запись".
			// Запрос на добавление в таблицу доп инфо будет создан автоматически
			session.save(newStud);

			// commit transaction
			transaction.commit();
			res = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== Students#insert - problem ===");
			res = false;
			// e.printStackTrace();
		}
		return res;
	}

	public static long findBySName(String nameStud) {
		List<Stud> students = new ArrayList<>();
		long id = -1;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			//Внимательно пишем названия КЛАССОВ!!! После from НЕ ТАБЛИЦА БД, 
			// а название класса JAVA, т.е. РЕГИСТР ИМЕЕТ ЗНАЧЕНИЕ!!!
			String testHQL = "from Stud s where s.name = \'" + nameStud + "\'";
			System.out.println("SQL " + testHQL);
			students = session.createQuery(testHQL, model.Stud.class).list();

			if (!students.isEmpty()) {
				id = students.get(0).getIdStud();
			}
		} catch (Exception e) {
			System.err.println("=== Students#findBySName - find id by name  ===");
			e.printStackTrace();
		}

		return id;
	}

	public static Stud getByID(long id) {
		System.out.println("getByID: id to find = " + id);
		Stud stud = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get Stud entity using get() method
			stud = session.get(model.Stud.class, (int) id);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== Institute#getByID - find Object by id  ===");
			e.printStackTrace();
		}
		return stud;
	}

	// Метод изменения формирует Update
	public static boolean update(long id, Stud newStud) {
		boolean res = false;
		// Изначально предполагается, что іd принадлежит одной из записей!!!
		// Это проверяется где-то в другом месте перед вызовом метода
		System.out.println("ID for update = " + id);
		Stud stud = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get Stud entity using get() method
			stud = session.get(model.Stud.class, (int) id);
			System.out.println("OLD " + stud);
			System.out.println("NEW " + newStud);

			// update Stud Обновляем все, кроме id
			stud.setName(newStud.getName());
			stud.setGender(newStud.getGender());
			stud.setAge(newStud.getAge());

			// Save changes into database
			session.update(stud);

			// commit transaction
			transaction.commit();
			res = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== Students#update - problem  ===");
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	// Метод изменения дополнительной информации Update
	public static boolean updateDopInfo(String nameStud, StudDopInfo newDopInfo) {
		boolean res = false;
		Stud stud = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Stud where name = :name");
			query.setParameter("name", nameStud);
			stud = (Stud) query.getSingleResult();
			if (stud != null) {
				System.out.println("Stud for update\n" + stud);
				System.out.println("NEW dop info\n" + newDopInfo);
				if (newDopInfo != null) {
					// Проверка - была ли доп информация
					if (stud.getDopInfo() != null) {
						// update StudDopInfo Обновляем все ИНФОРМАЦИОННЫЕ ПОЛЯ
						// FOREIGN KEY не трогать!
						stud.getDopInfo().setChannel(newDopInfo.getChannel());
						stud.getDopInfo().setActivity(newDopInfo.getActivity());
					} else {
						// не было - просто устанавливаем, как при добавлении
						newDopInfo.setStud(stud);
						stud.setDopInfo(newDopInfo);
					}
					session.update(stud);
				} else {
					// Это запрос на УДАЛЕНИЕ ДОП ИНФОРМАЦИИ!!!
					session.delete(stud.getDopInfo());
					stud.setDopInfo(null);
					session.update(stud);

				}
				// Save changes into database
				// Обновляет Stud!!! А так как в нем изменилась доп информация,
				// то таблица StudDopInfo обновиться автоматически

			} else {
				new Exception();
			}
			// commit transaction
			transaction.commit();
			res = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== Students#update - problem  ===");
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
		Stud stud = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get Stud entity using get() method
			stud = session.get(model.Stud.class, (int) id);

			// Delete from database
			//27/04/22 - ТАКЖЕ УДАЛЯЕТСЯ ВСЯ СВЯЗАННАЯ ИНФОРМАЦИЯ
			session.delete(stud);

			// commit transaction
			transaction.commit();
			res = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("=== Students#delete - problem  ===");
			System.err.println(e.getLocalizedMessage());
			res = false;
		}
		return res;
	}

}
