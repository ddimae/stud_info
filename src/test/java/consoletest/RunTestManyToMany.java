package consoletest;

import java.util.List;

import HBNdao.HBNDADiscipline;
import HBNdao.HBNDAStudGroup;
import enums.Gender;
import enums.TestType;
import model.Discipline;
import model.Stud;
import model.StudGroup;

public class RunTestManyToMany {

	public static void main(String[] args) {
		System.out.println("Discipline list");
		Discipline discipline1 = new Discipline(0, "Spring framework", 32, 16, TestType.TEST);
		System.out.println(discipline1);
		Discipline discipline2 = new Discipline(0, "Advanced Java 2", 16, 32, TestType.TEST);
		System.out.println(discipline2);
		Discipline discipline3 = new Discipline(0, "APPZ-2", 32, 32, TestType.EXAM);
		System.out.println(discipline3);

		System.out.println("\nGroup list");
		StudGroup group1 = new StudGroup(0, "KN-419а", 122);
		System.out.println(group1);
		StudGroup group2 = new StudGroup(0, "KN-719", 122);
		System.out.println(group2);
		StudGroup group3 = new StudGroup(0, "KN-219а", 122);
		System.out.println(group3);

		System.out.println("\nAfter join Discipline-Group");
		// Связываем дисциплины и группы
		// !!!!! ДОБАВЛЯЕМ НОВЫЕ ЭЛЕМЕНТЫ В СПИСКИ !!!!!
		/// Дисциплины добавляем в disciplineToStudy в соотвествующих StudGroup
		group1.getDisciplineToStudy().add(discipline1);
		group1.getDisciplineToStudy().add(discipline2);
		group2.getDisciplineToStudy().add(discipline1);
		group2.getDisciplineToStudy().add(discipline2);
		group3.getDisciplineToStudy().add(discipline1);
		group3.getDisciplineToStudy().add(discipline2);
		group3.getDisciplineToStudy().add(discipline3);
		// Группы добавляем в groupsStudyDiscipline в соотвествующих Discipline
		discipline1.getGroupsStudyDiscipline().add(group1);
		discipline1.getGroupsStudyDiscipline().add(group2);
		discipline1.getGroupsStudyDiscipline().add(group3);
		discipline2.getGroupsStudyDiscipline().add(group1);
		discipline2.getGroupsStudyDiscipline().add(group2);
		discipline2.getGroupsStudyDiscipline().add(group3);
		discipline3.getGroupsStudyDiscipline().add(group3);

		System.out.println("Discipline list after");
		System.out.println(discipline1);
		System.out.println(discipline2);
		System.out.println(discipline3);

		System.out.println("\nGroup list after");
		System.out.println(group1);
		System.out.println(group2);
		System.out.println(group3);

		// Поиск группы в БД
		System.out.println("\nFind group by cod");
		long idGroup = HBNDAStudGroup.findByName("КН-419а");
		StudGroup grupa = HBNDAStudGroup.getByID(idGroup);
		System.out.println(grupa);

		// Поиск дисциплины в БД
		System.out.println("\nFind discipline by codD");
		long idDiscipline = HBNDAStudGroup.findByName("АППЗ-ПИ");
		StudGroup discipline = HBNDAStudGroup.getByID(idDiscipline);
		System.out.println(discipline);

		// Выводится информация по всем дисциплинам
		// с выводом списка групп, изучающих дисциплину
		List<Discipline> disciplines = HBNDADiscipline.getAll();
		System.out.println("\n************************\nDiscipline list");
		disciplines.stream().forEach((d) -> System.out.println(d.listGroups()));

		// Выводится информация по всем группам
		// с выводом списка дисциплин, которые изучает группа
		List<StudGroup> groups = HBNDAStudGroup.getAll();
		System.out.println("\n************************\nStudGroups list");
		groups.stream().forEach((g) -> System.out.println(g.listDisciplines()));

	}

}
