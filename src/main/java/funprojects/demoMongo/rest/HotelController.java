package funprojects.demoMongo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import funprojects.demoMongo.domain.Hotel;
import funprojects.demoMongo.domain.QHotel;
import funprojects.demoMongo.repository.HotelRepository;

@RestController
@RequestMapping("/hotels")
public class HotelController
{
	@Autowired
	private HotelRepository repoHotel;

	@GetMapping("/")
	private List<Hotel> showAll()
	{
		List<Hotel> hotels = repoHotel.findAll();
		return hotels;
	}

	@PostMapping("/")
	private void saveorUpdateHotel(@RequestBody Hotel hotel)
	{
		if (hotel != null)
		{
			repoHotel.save(hotel);
		}

	}

	@DeleteMapping("/{hotelID}")
	private void deleteHotel(@PathVariable String hotelID)
	{
		if (StringUtils.hasText(hotelID))
		{
			repoHotel.deleteById(hotelID);
		}
	}

	@GetMapping("/byCityPrice")
	private List<Hotel> findByCityandMaxPrice(@RequestParam(name = "city", required = true) String city,
			@RequestParam(name = "maxPrice", required = false) double maxPrice)
	{
		List<Hotel> hotels = null;
		if (StringUtils.hasText(city) && maxPrice > 0)
		{
			hotels = repoHotel.findByAddressCityAndPricePerNightLessThan(city, maxPrice);
		}

		return hotels;
	}

	@GetMapping("/byCityPriceRatings")
	private List<Hotel> findByCityandMaxPriceRating(@RequestParam(name = "city", required = true) String city,
			@RequestParam(name = "maxPrice", required = false) String maxPrice,
			@RequestParam(name = "minRating", required = false) String minRating)
	{
		List<Hotel> hotels = null;
		if (StringUtils.hasText(city) && maxPrice != null && minRating != null)
		{

			// create a query class (QHotel)
			QHotel qHotel = new QHotel("hotel");

			// using the query class we can create the filters

			BooleanExpression filterByCity = qHotel.address.city.eq(city);
			BooleanExpression filterByPrice = qHotel.pricePerNight.lt(new Double(maxPrice));
			BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(new Integer(minRating));

			// we can then pass the filters to the findAll() method
			hotels = (List<Hotel>) repoHotel.findAll(filterByCity.and(filterByPrice).and(filterByRating));

		} else if (StringUtils.hasText(city) && maxPrice == null && minRating == null)
		{
			// create a query class (QHotel)
			QHotel qHotel = new QHotel("hotel");
			BooleanExpression filterByCity = qHotel.address.city.eq(city);

			// we can then pass the filters to the findAll() method
			hotels = (List<Hotel>) repoHotel.findAll(filterByCity);

		} else if (StringUtils.hasText(city) && maxPrice == null && minRating != null)
		{
			// create a query class (QHotel)
			QHotel qHotel = new QHotel("hotel");

			// using the query class we can create the filters

			BooleanExpression filterByCity = qHotel.address.city.eq(city);
			BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(new Integer(minRating));

			// we can then pass the filters to the findAll() method
			hotels = (List<Hotel>) repoHotel.findAll(filterByCity.and(filterByRating));
		}

		return hotels;
	}

}
