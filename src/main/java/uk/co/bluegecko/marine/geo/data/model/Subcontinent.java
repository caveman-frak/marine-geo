package uk.co.bluegecko.marine.geo.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Subcontinent {

	@Id
	int id;

	@Column
	@NonNull
	String name;

	@ManyToOne(optional = false)
	@JoinColumn(name = "continent")
	@NonNull
	final Continent continent;


}