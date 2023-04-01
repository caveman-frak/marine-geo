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
public class Currency {

	@Id
	@NonNull
	private String code;

	@Column
	private int numericCode;

	@Column
	@NonNull
	private String name;

	@Column
	private int minor;

	@Column
	private String symbol;

}