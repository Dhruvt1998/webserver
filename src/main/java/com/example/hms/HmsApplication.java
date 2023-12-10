package com.example.hms;

import com.example.hms.Model.Krankenhaus;
import com.example.hms.repository.KrankenhausRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HmsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(HmsApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(HmsApplication.class, args);
		KrankenhausRepository krankenhausRepository = context.getBean(KrankenhausRepository.class);
		Krankenhaus krankenhaus = Krankenhaus.getInstance();

		krankenhaus.setId(1);
		krankenhaus.setName("Beispiel-Krankenhaus");
		krankenhaus.setTotalBeds(150);
		krankenhaus.setAvailableBeds(75);

		System.out.println("Krankenhausname: " + krankenhaus.getName());
		System.out.println("KrankenhausID: " + krankenhaus.getId());
		System.out.println("Gesamtbetten: " + krankenhaus.getTotalBeds());
		System.out.println("Verfügbare Betten: " + krankenhaus.getAvailableBeds());
		krankenhausRepository.save(krankenhaus);

	}

}
