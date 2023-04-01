package uk.co.bluegecko.marine.geo.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Continent {

	@Id
	int id;

	@Column
	@NonNull
	String code;

	@Column
	@NonNull
	String name;

}