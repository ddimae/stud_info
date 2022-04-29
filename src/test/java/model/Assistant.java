package model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hbn.HibernateUtil5;

public class Assistant {
	
	    // Метод добавления Insert правильный
		public static boolean insert(Stud newStud, StudDopInfo dopInfo) {
			boolean res = false;
			Transaction transaction = null;
			try (Session session = HibernateUtil5.getSessionFactory().openSession()) {
				if (dopInfo != null) {
					dopInfo.setStud(newStud);
				}
				newStud.setDopInfo(dopInfo);
				session.save(newStud);
				transaction.commit();
				res = true;
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				System.err.println("=== Students#insert - problem ===");
				res = false;
			}
			return res;
		}

}
