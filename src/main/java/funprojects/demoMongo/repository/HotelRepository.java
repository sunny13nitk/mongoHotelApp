package funprojects.demoMongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import funprojects.demoMongo.domain.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>, QuerydslPredicateExecutor<Hotel>
{
	List<Hotel> findByPricePerNightLessThan(double maxPrice);

	List<Hotel> findByAddressCityAndPricePerNightLessThan(String city, double maxPrice);
}
