package com.finalProject.demo;

import com.finalProject.demo.models.Users.AccountHolder;
import com.finalProject.demo.models.Users.Address;
import com.finalProject.demo.models.Users.Admin;
import com.finalProject.demo.models.Users.ThirdParty;
import com.finalProject.demo.repositories.Users.AccountHolderRepository;
import com.finalProject.demo.repositories.Users.AdminRepository;
import com.finalProject.demo.repositories.Users.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	AccountHolderRepository accountHolderRepository;

	@Autowired
	ThirdPartyRepository thirdPartyRepository;

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Admins
		Admin admin1 = adminRepository.save(new Admin("Javi"));
		Admin admin2 = adminRepository.save(new Admin("Alex"));
		Admin admin3 = adminRepository.save(new Admin("David"));

		//AccountHolders
		AccountHolder ah1 = accountHolderRepository.save(new AccountHolder("Joan", LocalDate.of(1999,2,15), new Address("Calle falsa 123", "82345", "Barcelona", "España"), "joan1@gmail.com"));
		AccountHolder ah2 = accountHolderRepository.save(new AccountHolder("Rosa", LocalDate.of(2001, 4, 23), new Address("Calle Mexico 23", "85438", "Sabadell", "España"), "rosa2@gmail.com"));
		AccountHolder ah3 = accountHolderRepository.save(new AccountHolder("Miguel", LocalDate.of(1987, 7, 17), new Address("Calle Napoles 182", "25478", "Girona", "España"), "miguel3@gmail.com"));
		AccountHolder ah4 = accountHolderRepository.save(new AccountHolder("Arturo", LocalDate.of(1978, 9, 28), new Address("Calle Segovia 02", "68534", "Murcia", "España"), "arturo4@gmail.com"));
		AccountHolder ah5 = accountHolderRepository.save(new AccountHolder("Maia", LocalDate.of(1996, 11, 2), new Address("Calle Madrid 87", "54786", "Gijon", "España"), "maia5@gmail.com"));
		AccountHolder ah6 = accountHolderRepository.save(new AccountHolder("Anna", LocalDate.of(2003, 11, 16), new Address("Calle Lucrecia 128", "64557", "Pontevedra", "España"), "anna6@gmail.com"));
		AccountHolder ah7 = accountHolderRepository.save(new AccountHolder("Luis", LocalDate.of(1997, 2, 1), new Address("Calle SanJuan 352", "86542", "Tarragona", "España"), "luis7@gmail.com"));
		AccountHolder ah8 = accountHolderRepository.save(new AccountHolder("Pedro", LocalDate.of(1988, 9, 27), new Address("Calle Plata 46", "96451", "Sevilla", "España"), "pedro8@gmail.com"));
		AccountHolder ah9 = accountHolderRepository.save(new AccountHolder("Maria", LocalDate.of(2000, 3, 20), new Address("Calle Jupiter 223", "65478", "Bilbao", "España"), "maria9@gmail.com"));

		//ThirdParties
		ThirdParty thirdParty1 = thirdPartyRepository.save(new ThirdParty("123455", "Cajero1"));
		ThirdParty thirdParty2 = thirdPartyRepository.save(new ThirdParty("584678", "Cajero2"));
		ThirdParty thirdParty3 = thirdPartyRepository.save(new ThirdParty("987631", "Cajero3"));
	}
}
