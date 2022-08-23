package com.example.demo.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PERSON_OTM")
@Setter
@Getter
public class Person implements Serializable{
	@Id
	@SequenceGenerator(name = "gen1",initialValue=1000,allocationSize=5,sequenceName="PID_SEQ")
	@GeneratedValue(generator="gen1",strategy=GenerationType.SEQUENCE)
	private Integer pid;
	@Column(length=20)
	private String Pname;
	@Column(length=20)
	private String paddr;
	@OneToMany(targetEntity=PhoneNumber.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="PERSON_ID",referencedColumnName="PID")
	Set<PhoneNumber> phones;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return Pname;
	}
	public void setPname(String pname) {
		Pname = pname;
	}
	public String getPaddr() {
		return paddr;
	}
	public void setPaddr(String paddr) {
		this.paddr = paddr;
	}
	public Set<PhoneNumber> getPhones() {
		return phones;
	}
	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
	}
	@Override
	public String toString() {
		return "Person [pid=" + pid + ", Pname=" + Pname + ", paddr=" + paddr + ", phones=" + phones + "]";
	}

}
