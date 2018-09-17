package sge;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SuperClase {
	@Id @GeneratedValue
	private int id;

}
