package funprojects.demoMongo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel
{
	@Id
	private String id;
	private String name;

	@Indexed(direction = IndexDirection.ASCENDING)
	private double pricePerNight;
	private Address address;
	private List<Review> reviews = new ArrayList<Review>();
}
