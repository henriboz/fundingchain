package fundingchain.models;

import javax.persistence.*;
import java.util.Date;

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

	@Column(nullable = false, columnDefinition = "DATETIME")
	private Date fundingdate = new Date();

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
	public Date getFundingdate() {	return fundingdate;}
	public void setFundingdate(Date fundingdate) { this.fundingdate = fundingdate; }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funding(){}
	public Funding(Project project, User funder, double value){
		this.project = project;
		this.funder = funder;
		this.value = value;
	}
	
}