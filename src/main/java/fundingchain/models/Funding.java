package fundingchain.models;

import javax.persistence.*;

@Entity
@Table(name = "fundings")
public class Funding{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Project project = new Project();
	
	@ManyToOne (fetch = FetchType.LAZY)
	private User funder  = new User();
	
	@Column(nullable = false)
	private double	value;
	
	
	public Project getProject(){
		return this.project;
	}
	public void setProject(Project project){
		this.project = project;
	}
	public User getFunder(){
		return this.funder;
	}
	public void setFunder(User funder){
		this.funder = funder;
	}
	public double getValue(){
		return this.value;
	}
	public void setValue(double value){
		this.value = value;
	}
	
	public Funding(){}
	public Funding(Project project, User funder, double value){
		this.project = project;
		this.funder = funder;
		this.value = value;
	}
	
}