package org.kshell.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Person {
	/**
	 * mysql数据库中,只有使用GenerationType.IDENTITY注解
	 * 才能正常使用数据库的自动增长特性
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String lastName;
	private String email;
	private Date birth;
}
