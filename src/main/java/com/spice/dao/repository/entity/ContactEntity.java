package com.spice.dao.repository.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spice.util.JsonDateDeserializer;
import com.spice.util.JsonDateSerializer;
@Entity
public class ContactEntity {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String uniqueId;
	
	private String type;
	
	private Date dateOfIncorporation;
	
	private String phone;
	
	private String email;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="contact_id")
	private Set<AddressEntity> addresses;
	
	public ContactEntity() {
		super();
	}
	
	public ContactEntity(Long id, String name, String uniqueId, String type, Date dateOfIncorporation, String phone,
			String email) {
		super();
		this.id = id;
		this.name = name;
		this.uniqueId = uniqueId;
		this.type = type;
		this.dateOfIncorporation = dateOfIncorporation;
		this.phone = phone;
		this.email = email;
		//this.addresses = addresses;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateOfIncorporation() {
		return dateOfIncorporation;
	}
	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setDateOfIncorporation(Date dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ContactEntity [id=" + id + ", name=" + name + ", uniqueId=" + uniqueId + ", type=" + type
				+ ", dateOfIncorporation=" + dateOfIncorporation + ", phone=" + phone + ", email=" + email
				+ ", addresses=" + addresses + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
		result = prime * result + ((dateOfIncorporation == null) ? 0 : dateOfIncorporation.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactEntity other = (ContactEntity) obj;
		if (addresses == null) {
			if (other.addresses != null)
				return false;
		} else if (!addresses.equals(other.addresses))
			return false;
		if (dateOfIncorporation == null) {
			if (other.dateOfIncorporation != null)
				return false;
		} else if (!dateOfIncorporation.equals(other.dateOfIncorporation))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		return true;
	}

}
