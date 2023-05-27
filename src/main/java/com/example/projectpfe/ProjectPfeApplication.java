package com.example.projectpfe;

import com.example.projectpfe.pojo.model.UserRole;
import com.example.projectpfe.pojo.emuns.RoleEnum;
import com.example.projectpfe.pojo.req.AuthReq;
import com.example.projectpfe.pojo.req.UserReq;
import com.example.projectpfe.pojo.req.UserRoleReq;
import com.example.projectpfe.repository.UserRepository;
import com.example.projectpfe.repository.UserRoleRepository;
import com.example.projectpfe.service.AuthService;
import com.example.projectpfe.service.UserRoleService;
import com.example.projectpfe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })

public class ProjectPfeApplication {
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;


	@Autowired
	private AuthService authService;
	public static void main(String[] args) {
		SpringApplication.run(ProjectPfeApplication.class, args);
	}

	@Bean
	CommandLineRunner start() {
		return args -> {
/*
			UserRoleReq roleOffer = new UserRoleReq();
			roleOffer.setRole(RoleEnum.OFFER);
			UserRole roleOffre1=  userRoleService.createUserRole(roleOffer);

			UserRoleReq roleReferential = new UserRoleReq();
			roleReferential.setRole(RoleEnum.REFERENTIAL);
			UserRole roleReferential1= userRoleService.createUserRole(roleReferential);

			UserRoleReq delegation = new UserRoleReq();
			delegation.setRole(RoleEnum.DELEGATION);
			UserRole roleDelegation1 = userRoleService.createUserRole(delegation);

			UserRoleReq contactCadre = new UserRoleReq();
			contactCadre.setRole(RoleEnum.CONTRACT_CADRE);
			 UserRole contactCadre1=userRoleService.createUserRole(contactCadre);

			UserRoleReq validatorContractCadre = new UserRoleReq();
			validatorContractCadre.setRole(RoleEnum.VALIDATOR_CONTRACT_CADRE);
			userRoleService.createUserRole(validatorContractCadre);

			UserRoleReq report = new UserRoleReq();
			report.setRole(RoleEnum.REPORT);
			userRoleService.createUserRole(report);

			UserRoleReq document = new UserRoleReq();
			document.setRole(RoleEnum.DOCUMENT);
			userRoleService.createUserRole(document);

			UserRoleReq contractMass = new UserRoleReq();
			contractMass.setRole(RoleEnum.CONTRACT_MASS);
			userRoleService.createUserRole(contractMass);

			UserRoleReq materRole = new UserRoleReq();
			materRole.setRole(RoleEnum.MASTER_ROLE);
			userRoleService.createUserRole(materRole);

			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(roleOffre1);
			userRoles.add(roleDelegation1);
			userRoles.add(roleReferential1);

			Set<UserRole> userRoles2 = new HashSet<>();
			userRoles2.add(roleOffre1);
			userRoles2.add(roleDelegation1);

			UserReq user1 = new UserReq();
			user1.setFirstName("aoubahassou");
			user1.setLastName("sanae");
			user1.setEmail("sanaeoubahassou55@gmail.com");
			user1.setCountry("Maroc");
			user1.setCity("Casablanca");
			user1.setUserRoles(userRoles);
			userService.createUser(user1);

			UserReq user2 = new UserReq();
			user2.setFirstName("boubahassou");
			user2.setLastName("soso");
			user2.setEmail("sanaeoubahassou5@gmail.com");
			user2.setCountry("Maroc");
			user2.setCity("Rabat");
			user2.setUserRoles(userRoles2);
			userService.createUser(user2);

			UserReq user3 = new UserReq();
			user3.setFirstName("zadouk");
			user3.setLastName("laila");
			user3.setEmail("sanaeoubahassou@gmail.com");
			user3.setCountry("Maroc");
			user3.setCity("fes");
		    userService.createUser(user3);


			UserReq user4 = new UserReq();
			user4.setFirstName("zadouk");
			user4.setLastName("fati");
			user4.setEmail("sanaeoubahassou66@gmail.com");
			user4.setCountry("Maroc");
			user4.setCity("errachia");
			userService.createUser(user4);

			UserReq user5 = new UserReq();
			user5.setFirstName("eltachari");
			user5.setLastName("halima");
			user5.setEmail("sanaeoubahassou55@gmail.com");
			user5.setCountry("Maroc");
			user5.setCity("kenitra");
			userService.createUser(user5);

			UserReq user6 = new UserReq();
			user6.setFirstName("nomsalma");
			user6.setLastName("halima");
			user6.setEmail("sanaeoubahassou55@gmail.com");
			user6.setCountry("Maroc");
			user6.setCity("kenitra");
			userService.createUser(user6);

			UserReq user7 = new UserReq();
			user7.setFirstName("yousra");
			user7.setLastName("dalla");
			user1.setEmail("sanaeoubahassou55@gmail.com");
			user7.setCountry("Egypte");
			user7.setCity("caire");
			userService.createUser(user7);

			UserReq user8 = new UserReq();
			user8.setFirstName("serdi");
			user8.setLastName("mohamed");
			user8.setEmail("sanaeoubahassou55@gmail.com");
			user8.setCountry("australia");
			user8.setCity("Canberra");
			userService.createUser(user8);

			UserReq user9 = new UserReq();
			user9.setFirstName("maalouf");
			user9.setLastName("lamyae");
			user9.setEmail("sanaeoubahassou55@gmail.com");
			user9.setCountry("Maroc");
			user9.setCity("fes");
			userService.createUser(user9);

			UserReq user10 = new UserReq();
			user10.setFirstName("sakhi");
			user10.setLastName("abir");
			user10.setEmail("sanaeoubahassou55@gmail.com");
			user10.setCountry("australia");
			user10.setCity("Canberra");
			userService.createUser(user10);

			UserReq user11 = new UserReq();
			user11.setFirstName("ayoub");
			user11.setLastName("kassi");
			user11.setEmail("sanaeoubahassou55@gmail.com");
			user11.setCountry("France");
			user11.setCity("bordo");
			userService.createUser(user11);

 */


		};
	}

}
