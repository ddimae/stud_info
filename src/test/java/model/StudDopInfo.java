package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="stud_dop_info")
public class StudDopInfo implements Serializable {
	//Приклад описания нашего случая
    //https://www.javaguides.net/2019/08/jpahibernate-one-to-one-bidirectional-mapping-annotation-example.html
		
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idstud_dop_info")
	private long id;
	
	private String channel;
	
	private String activity;
	
	//==== OneToOne for Link with StudPobInfo ====
	//Приклад описания нашего случая
    //https://www.javaguides.net/2019/08/jpahibernate-one-to-one-bidirectional-mapping-annotation-example.html
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stud")
	private Stud stud;
	

	public StudDopInfo() {
		super();
	}
	
	public StudDopInfo(String channel, String activity) {
		super();
		this.channel = channel;
		this.activity = activity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public Stud getStud() {
		return stud;
	}

	public void setStud(Stud stud) {
		this.stud = stud;
	}


	@Override
	public String toString() {
		return "StudDopInfo [channel=" + channel + ", activity=" + activity + "]";
	}
	
	
	
	
	

}
