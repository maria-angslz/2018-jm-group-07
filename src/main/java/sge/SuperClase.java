package sge;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.gson.annotations.Expose;


@MappedSuperclass
public class SuperClase implements Serializable{
	@Id @GeneratedValue
	@Expose(serialize = false, deserialize = false)
	protected int id;
	
	public int getid() {
		return id;
	}

}
