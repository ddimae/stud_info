package consoletest;

import java.util.List;

import HBNdao.HBNDAStudGroup;
import enums.Gender;
import model.Stud;
import model.StudGroup;

public class RunTestManyToOne {

	public static void main(String[] args) {
		StudGroup group = new StudGroup(0, "KN-419", 122);
		System.out.println(group);
		
		Stud stud = new Stud(0, "DDE", 49, Gender.MALE);
		System.out.println(stud);
		
		System.out.println("\nAfter join Stud-Group");
		stud.setGroup(group);
		group.getStudentsInGroup().add(stud);
		System.out.println(stud);
		System.out.println(group);
		
		//Поиск группы в БД
		long idGroup = HBNDAStudGroup.findByName("КН-419а");
		group = HBNDAStudGroup.getByID(idGroup);
		System.out.println(group);
		

		// Выводится информация по всем студентам
		// При этом доп информация
		List<StudGroup> groups = HBNDAStudGroup.getAll();
		System.out.println("StudGroups list");
		groups.stream().forEach(System.out::println);

	}

}
