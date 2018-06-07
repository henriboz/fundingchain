package fundingchain.models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 300)
	private String title;
	
	@Lob @Column(nullable=false)
	private String description;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User owner;
	
	@Column(nullable = false, columnDefinition="TINYINT")
	private boolean active;
	
	@Column(nullable = false)
	private Date creation_date = new Date();
	
	@Column(nullable = false)
	private Date due_date = new Date();
	
	@Column(nullable = false)
	private double funding_value;
	
	@OneToMany(mappedBy = "project")
	private List<Funding> fundings = new ArrayList<Funding>();

	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getTitle(){
		return title;
	}
	public void setTile(String title){
		this.title = title;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public User getOwner()
	{
		return owner;
	}
	public void setOwner(User owner){
		this.owner = owner;
	}
	public boolean getActive(){
		return this.active;
	}
	public void setActive(boolean active){
		this.active = active;
	}
	public Date getCreationDate(){
		return creation_date;
	}
	public void setCreationDate(Date creation_date){
		this.creation_date = creation_date;
	}
	public Date getDueDate(){
		return due_date;
	}
	public void setDueDate(Date due_date){
		this.due_date = due_date;
	}
	public double getFundingValue(){
		return funding_value;
	}
	public void setFundingValue(double funding_value){
		this.funding_value = funding_value;
	}
	public List<Funding> getFundings(){
		return this.fundings;
	}
	public void setFunders(List<Funding> fundings){
		this.fundings = fundings;
	}
	public void addFunding(Funding funding){
		this.fundings.add(funding);
	}
	public Project(){}
	public Project(Long id, String title, String description, double funding_value){
		this.id = id; 
		this.title = title; 
		this.description = description; 
		//this.owner = owner;
		//this.due_date = due_date;
		this.funding_value = funding_value;
	}
	@Override
	public String toString(){
		return "";
	}
}