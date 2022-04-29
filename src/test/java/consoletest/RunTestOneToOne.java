package consoletest;

import java.util.List;

import HBNdao.HBNDAStud;
import enums.Gender;
import model.Stud;
import model.StudDopInfo;

public class RunTestOneToOne {

	public static void main(String[] args) {

		Stud stud = new Stud(0, "DDE", 49, Gender.MALE);
		System.out.println(stud);

		StudDopInfo dopI = new StudDopInfo("Telegram", "Смотреть спорт передачи");
		// System.out.println(dopI);

		stud.setDopInfo(dopI);

		System.out.println(stud);

		// Выводится информация по всем студентам
		// При этом доп информация
		List<Stud> students = HBNDAStud.getAll();
		System.out.println("Students list");
		students.stream().forEach(System.out::println);

		// Добавление студента с доп информацией в БД
		// 1. Создается новый экземпляр студент"
		Stud studNew = new Stud(0, "DTE", 45, Gender.FEMALE);
		// 2.Создается объект с доп записью
		StudDopInfo dopInfoForNewStud = new StudDopInfo("Phone call", "Лазить по соц сетям");
		// 3. Оба объекта передаются на вход метода добавления
		HBNDAStud.insert(studNew, dopInfoForNewStud);
		// Просмотр после добавления
		students = HBNDAStud.getAll();
		System.out.println("\nStudents list after insert");
		students.stream().forEach(System.out::println);

		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// ДО РАЗРАБОТКИ МЕТОДА УДАЛЕНИЯ НЕОБХОДИМО
		// УДАЛЯТЬ ОТРЕДАКТИРОВАННУЮ ЗАПИСЬ ИЗ БД РУЧКАМИ!!!!
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// Удаление доп информации студента
		// Поиск Id студента, которого только что добавили
		System.out.println("\n\nDELETING DopInfo");
		boolean resUpdateStud = HBNDAStud.updateDopInfo(studNew.getName(), null);
		if (resUpdateStud) {
			// Просмотр после добавления
			students = HBNDAStud.getAll();
			System.out.println("\nStudents list after delete dop_info");
			students.stream().forEach(System.out::println);
		} else {
			System.err.println("Удаление доп информации не выполнено!");
		}

		// Обновление доп информации студента
		// Поиск Id студента, которого только что добавили
		System.out.println("\n\nUPDATING");
		StudDopInfo dopInfoForUpdate = new StudDopInfo("Personal meeting", "Волонтер");
		resUpdateStud = HBNDAStud.updateDopInfo(studNew.getName(), dopInfoForUpdate);
		if (resUpdateStud) {
			// Просмотр после добавления
			students = HBNDAStud.getAll();
			System.out.println("\nStudents list after update dop_info");
			students.stream().forEach(System.out::println);
		} else {
			System.err.println("Обновление доп информации не выполнено!");
		}

		// Удаление СТУДЕНТА. Ожидается, что удалится и все связанная доп информация
		// Поиск Id студента, которого только что добавили

		System.out.println("\n\nDELETING Student ");
		long idDelStud = HBNDAStud.findBySName(studNew.getName());
		System.out.println("Id student for DELETE " + idDelStud);
		idDelStud = HBNDAStud.findBySName(studNew.getName());
		System.out.println("Id student for DELETE " + idDelStud);
		if (idDelStud != -1) {
			boolean resDelStud = HBNDAStud.delete(idDelStud);
			if (resDelStud) {
				// Просмотр после удаления
				students = HBNDAStud.getAll();
				System.out.println("\nStudents list after delete dop_info");
				students.stream().forEach(System.out::println);
			} else {
				System.err.println("Удаление доп информации не выполнено!");
			}
		} else {
			System.err.println("Нет такого Студента! Удаление невозможно");

		}

	}

}
