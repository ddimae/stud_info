package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import enums.Gender;

@Entity
@Table(name="students")
public class Stud implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_student")
	private int idStud;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false, unique = true)
	private int age;

	@Column(nullable = false)
	private Gender gender;
	
	//==== OneToOne for Link with StudPobInfo ====
	//Приклад описания нашего случая
	//https://www.javaguides.net/2019/08/jpahibernate-one-to-one-bidirectional-mapping-annotation-example.html
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "stud", fetch = FetchType.LAZY)
	private StudDopInfo dopInfo;

	//==== ManyToOne ==== for Link with Group
	//Приклад для створення цього проекту
	//https://www.javaguides.net/2019/08/jpa-hibernate-one-to-many-bidirectional-mapping-example.html
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "id_group", nullable = false)
	private StudGroup stGroup;
	

	
	public Stud() {
	}

	public Stud(int idStud, String name, int age, Gender gender) {
		super();
		this.idStud = idStud;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Stud other = (Stud) obj;
		return Objects.equals(name, other.name);
	}

	public int getIdStud() {
		return idStud;
	}

	public void setIdStud(int idStud) {
		this.idStud = idStud;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public StudDopInfo getDopInfo() {
		return dopInfo;
	}

	public void setDopInfo(StudDopInfo dop_info) {
		this.dopInfo = dop_info;
	}
	
	public StudGroup getGroup() {
		return stGroup;
	}

	public void setGroup(StudGroup group) {
		this.stGroup = group;
	}

	@Override
	public String toString() {
		return "Stud [idStud=" + idStud + ", name=" + name 
				+ ", age=" + age + ", gender=" + gender + "]" 
				+(stGroup!=null?"\n"+stGroup.getCod():"\nІнформація про групу відсутня")
				+(dopInfo!=null?"\n"+dopInfo.toString():"\nДоп інфо відсутня");
		
	}

	
	
	
	
	
	

}
