package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperites;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepositiry;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;

	private MyBeanWithProperites myBeanWithProperites;
	private UserPojo userPojo;
	private UserRepositiry userRepositiry;

	private UserService userService;



	public FundamentosApplication(@Qualifier("componetTwoimplements") ComponentDependency componentDependency,MyBean myBean,
	MyBeanWithDependency myBeanWithDependency,MyBeanWithProperites myBeanWithProperites,
    UserPojo userPojo,UserRepositiry userRepositiry,UserService userService){
		this.componentDependency =componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency =myBeanWithDependency;
		this.myBeanWithProperites =myBeanWithProperites;
		this.userPojo =userPojo;
		this.userRepositiry =userRepositiry;
		this.userService =userService;

	}

	public static void main(String[] args) {

		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemploAnteriores();
		saveUsersInDatabase();
		getInformationJpqlFromUser();
		saveWithErrorTransaction();

	}

	//trabajando con lo services y inserciones en transaciones
	private void saveWithErrorTransaction(){
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> listaUser =Arrays.asList(test1,test2,test3,test4);
		//usando try cath
		try	{
			userService.saveTransactional(listaUser);
		}catch (Exception e){

			LOGGER.error("esta es una exepcion dentro del metodo transacional " +e);
		}

		userService.getAllUsers().forEach(user-> LOGGER.info("Usuarios listado desde UserService "+user));

 	}

	private void getInformationJpqlFromUser(){
	LOGGER.info("El Usuaario con el metodo findByUserEmail con @Queryy"+
			userRepositiry.findByUserEmail("john@domain.com")
					.orElseThrow(()->new RuntimeException("No se encontro el usuario")));

		userRepositiry.findAndSort("Marc", Sort.by("id").descending()).forEach(LOGGER::info);

		userRepositiry.findByname("Daniela")
				.forEach(user -> LOGGER.info("usuario con query method"+user));

		//buscan con 2 parametros Query methods hace uan busqueda exacta
		LOGGER.info("Usuario con QueryMethod findByEmailAndName "+userRepositiry.findByEmailAndName("carlos@domain.com","Carlos")
				.orElseThrow(()->new RuntimeException("No se encontro el usuario buscado con Query methods")));
		//buscando con like por el nombre
		userRepositiry.findByNameLike("%Paol%")
				.forEach(user -> LOGGER.info("usuario con Query method buscando por like findByNameLike  "+user));

		//buscando por ro
		userRepositiry.findByNameOrEmail("Enrique",null)
				.forEach(user -> LOGGER.info("usuario con Query method buscando por like findByNameOrEmail  "+user));

		//buscando por fechas
		userRepositiry.findByBirthDateBetween
				(LocalDate.of(2021,1,13),LocalDate.of(2021,5,15))
				.forEach(user -> LOGGER.info("usuario con Query method buscando por like findByBirthDateBetween  "+user));
		//buscando por like y ordenando por id
		userRepositiry.findByNameLikeOrderByIdDesc("%Marco%")
				.forEach(user -> LOGGER.info("usuario con Query method buscando por like findByNameLikeOrderByIdDesc  "+user));

		//usando Uso de JPQL con named parameters
		LOGGER.info("El usuario a partir del name parameter es: "+userRepositiry.getAllByBirthDateAndEmail(
			LocalDate.of(2021,3,13),"john@domain.com")
				.orElseThrow(()-> new RuntimeException
						("No se encontro el usuario buscado con JPQL con named parameters")));
	}



	private void saveUsersInDatabase(){
		User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
		User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("Marco2", "marisol@domain.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("Marco3", "karen@domain.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("Luis Paol", "luis@domain.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9);
		list.stream().forEach(userRepositiry::save);

	}

	private void ejemploAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperites.function());;
		System.out.println(userPojo.toString());
		LOGGER.error("sto es un error");
	}
}
