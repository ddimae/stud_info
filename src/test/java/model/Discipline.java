package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import enums.TestType;

@Entity
@Table(name = "disciplines")
public class Discipline implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_discipline")
	private long id;

	@Column(name = "cod_discipline", nullable = false, unique = true)
	private String codD;

	@Column(name = "hour_lect", nullable = false)
	private int lect;

	@Column(name = "hour_labs", nullable = false)
	private int lab;

	@Column(name = "test_type", nullable = false)
	private TestType testType;

	// ==== ManyToMany ==== for Link with Group
	// Приклад реалізації маппингу такого типу
	// https://www.javaguides.net/2019/12/hibernate-5-many-to-many-annotation-mapping-example.html
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable
		(name = "discipline_group", 
		joinColumns = { 
				@JoinColumn(name = "id_discipline") 
		}, 
		inverseJoinColumns = {
				@JoinColumn(name = "id_group") }
		)
	private Set<StudGroup> groupsStudyDiscipline = new HashSet<>();
	// В качестве коллекции НЕЛЬЗЯ использовать List - будет ошибка
	//Здесь описание ошибок в аналогичной ситуации и ссылка, которая объясняет ситуацию
	//https://stackoverflow.com/questions/16458472/hibernate-multiple-many-to-many-associations-cannot-simultaneously-fetch-mu
	

	public Discipline() {
		super();
	}

	public Discipline(long id, String codD, int lect, int lab, TestType testType) {
		super();
		this.id = id;
		this.codD = codD;
		this.lect = lect;
		this.lab = lab;
		this.testType = testType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodD() {
		return codD;
	}

	public void setCodD(String codD) {
		this.codD = codD;
	}

	public int getLect() {
		return lect;
	}

	public void setLect(int lect) {
		this.lect = lect;
	}

	public int getLab() {
		return lab;
	}

	public void setLab(int lab) {
		this.lab = lab;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}
	
	public Set<StudGroup> getGroupsStudyDiscipline() {
		return groupsStudyDiscipline;
	}

	public void setGroupsStudyDiscipline(Set<StudGroup> groupsStudyDiscipline) {
		this.groupsStudyDiscipline = groupsStudyDiscipline;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codD);
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
		Discipline other = (Discipline) obj;
		return Objects.equals(codD, other.codD);
	}

	@Override
	public String toString() {
		return "Discipline [id=" + id + ", codD=" + codD + ", lect=" + lect + ", lab=" + lab + ", testType=" + testType
				+ "]"
				+ "\nДисциплину изучает - " + groupsStudyDiscipline.size()
				;
	}
	
	public String listGroups() {
		StringBuilder sb = new StringBuilder(codD+"\nList Groups what Studied Discipline\n");
		if (groupsStudyDiscipline.size()>0) {
			for (StudGroup g : groupsStudyDiscipline) {
			      sb.append(g.getCod()).append(System.lineSeparator());
			}
		} else {
			sb.append("Groups list not found!").append(System.lineSeparator());
		}
		return sb.toString(); 
    }

}
