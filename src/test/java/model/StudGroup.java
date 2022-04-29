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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stud_groups")
public class StudGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_group")
	private int idGr;

	@Column(name = "cod_group", nullable = false, unique = true)
	private String cod;

	@Column(name = "cod_spec", nullable = false, unique = true)
	private int spec;

	// ==== ManyToOne ==== for Link with Group
	// Приклад для створення цього проекту
	// https://www.javaguides.net/2019/08/jpa-hibernate-one-to-many-bidirectional-mapping-example.html
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stGroup", cascade = CascadeType.ALL)
	private List<Stud> studentsInGroup = new ArrayList<>();
	// cascade = {CascadeType.PERSIST,CascadeType.MERGE,
	// CascadeType.DETACH,CascadeType.REFRESH})

	// ==== ManyToMany ==== for Link with Discipline
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "groupsStudyDiscipline", cascade = { CascadeType.ALL })
	private Set<Discipline> disciplineToStudy = new HashSet<>();

	public StudGroup() {
		super();
	}

	public StudGroup(int idGr, String cod, int spec) {
		super();
		this.idGr = idGr;
		this.cod = cod;
		this.spec = spec;
	}

	public int getIdGr() {
		return idGr;
	}

	public void setIdGr(int idGr) {
		this.idGr = idGr;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public int getSpec() {
		return spec;
	}

	public void setSpec(int spec) {
		this.spec = spec;
	}

	public List<Stud> getStudentsInGroup() {
		return studentsInGroup;
	}

	public void setStudensetDisciplineToStudytsInGroup(List<Stud> studentsInGroup) {
		this.studentsInGroup = studentsInGroup;
	}
	
	public Set<Discipline> getDisciplineToStudy() {
		return disciplineToStudy;
	}

	public void setDisciplineToStudy(Set<Discipline> disciplineToStudy) {
		this.disciplineToStudy = disciplineToStudy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod, studentsInGroup);
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
		StudGroup other = (StudGroup) obj;
		return Objects.equals(cod, other.cod) && Objects.equals(studentsInGroup, other.studentsInGroup);
	}

	public String toString() {
		return "StudGroup [idStud=" + idGr + ", cod=" + cod + ", spec=" + spec + "]" 
	        + "\nСтудентов в группе - " + studentsInGroup.size()
	        + "\nИзучает группа дисциплин - " + disciplineToStudy.size()
		;
	}

	public String listStudents() {
		StringBuilder sb = new StringBuilder("\nList Students in Group "+cod+"\n");
		if (disciplineToStudy.size()>0) {
			for (Discipline d : disciplineToStudy) {
			      sb.append(d.getCodD()).append(System.lineSeparator());
			}
		} else {
			sb.append("Disciplines list not found!").append(System.lineSeparator());
		}
		return sb.toString(); 
    }
	
	public String listDisciplines() {
		StringBuilder sb = new StringBuilder("\nList Disciplines To Study "+cod+"\n");
		if (disciplineToStudy.size()>0) {
			for (Discipline d : disciplineToStudy) {
			      sb.append(d.getCodD()).append(System.lineSeparator());
			}
		} else {
			sb.append("Disciplines list not found!").append(System.lineSeparator());
		}
		return sb.toString(); 
    }
}

