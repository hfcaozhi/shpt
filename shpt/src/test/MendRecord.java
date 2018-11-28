package test;

// Generated 2013-6-3 11:33:12 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MendRecord generated by hbm2java
 */
@Entity
@Table(name = "mend_record", catalog = "igov")
public class MendRecord implements java.io.Serializable {

	private Long id;
	private Car car;
	private Date createTime;
	private Float cost;
	private String description;
	private String mendUser;

	public MendRecord() {
	}

	public MendRecord(Car car, Date createTime, Float cost, String description,
			String mendUser) {
		this.car = car;
		this.createTime = createTime;
		this.cost = cost;
		this.description = description;
		this.mendUser = mendUser;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id")
	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "cost", precision = 6)
	public Float getCost() {
		return this.cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "mend_user", length = 40)
	public String getMendUser() {
		return this.mendUser;
	}

	public void setMendUser(String mendUser) {
		this.mendUser = mendUser;
	}

}