package funprojects.demoMongo.seeder;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import funprojects.demoMongo.domain.Address;
import funprojects.demoMongo.domain.Hotel;
import funprojects.demoMongo.domain.Review;
import funprojects.demoMongo.repository.HotelRepository;

@Component
public class DBSeeder implements CommandLineRunner
{

	@Autowired
	private HotelRepository repoHotel;

	@Override
	public void run(String... args) throws Exception
	{

		// Initialize only if no Data Present

		if (repoHotel.count() == 0)
		{

			Hotel hotel = new Hotel();
			hotel.setName("Marriot");
			hotel.setAddress(new Address("Gurugram", "India"));
			hotel.setPricePerNight(120.56);
			hotel.setReviews(Arrays.asList(new Review("John", 8, true), new Review("Martin", 7, false)));

			repoHotel.save(hotel);
		}
	}

}
