package fundingchain.models;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
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

	@Lob @Column(nullable = false)
	private String about;

	@ManyToOne(optional = false)
	private User owner;
	
	@Column(nullable = false, columnDefinition="TINYINT")
	private boolean active;
	
	@Column(nullable = false, columnDefinition = "DATETIME")
	private Date creationdate = new Date();
	
	@Column(nullable = false, columnDefinition = "DATETIME")
	private Date duedate = new Date();
	
	@Column(nullable = false)
	private double funding_value;
	
	@OneToMany(mappedBy = "project")
	private List<Funding> fundings = new ArrayList<Funding>();

	@OneToOne
	private Reward reward;

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
	public String getAbout(){ return this.about;}
	public void setAbout(String about){ this.about = about;}
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
	public Date getCreationdate(){ return creationdate;	}
	public void setCreationdate(Date creationdate){
		this.creationdate = creationdate;
	}
	public Date getDuedate(){
		return duedate;
	}

	public void setDuedate(Date duedate){
		this.duedate = duedate;
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
	public Reward getReward() { return reward; }
	public void setReward(Reward reward) { this.reward = reward; }
	public String getStatus(){
		if (this.getActive()) return "Active";
		else{
			if (getSumFundingsValue() >= getFundingValue()) return "Successful!";
			else return "Failed!";
		}
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

	public double getSumFundingsValue(){
		double value = 0;
		for (Funding f: this.getFundings()){
			value += f.getValue();
		}
		return value;
	}

	public String getDuedateString(){
		Date now = new Date();
		long ms = this.duedate.getTime() - now.getTime();
		int time;
		String result;
		String post_result;
		if (ms < 86400000 )
		{
			time = (int)TimeUnit.HOURS.convert(ms, TimeUnit.MILLISECONDS);
			if (time == 1) post_result = "hour";
			else post_result = "hours";
			result = String.valueOf(time) + " " + post_result;
		}
		else
		{
			time = (int)TimeUnit.DAYS.convert(ms, TimeUnit.MILLISECONDS);
			if (time == 1) post_result = "day";
			else post_result = "days";
			result = String.valueOf(time) + " " + post_result;
		}
		return result;
	}

	public int getProjectPercentComplete(){
		return (int)(100*this.getSumFundingsValue()/this.getFundingValue());
	}

	public String getValueToReward(double value){
		if (this.getActive()) return "";
		else{
			if (getSumFundingsValue() >= getFundingValue()){
				if (value < this.reward.getLowervalue()) return this.reward.getLowerDesc();
				else if (value > this.reward.getUppervalue()) return this.reward.getUpperDesc();
				else return this.reward.getMidDesc();
			}
			else return "No Reward!";
		}
	}

	@Override
	public String toString(){
		return "";
	}
}