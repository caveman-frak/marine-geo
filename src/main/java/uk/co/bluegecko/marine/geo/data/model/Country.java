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
public class Country {

	@Id
	@NonNull
	String code;

	@NonNull
	String code3;

	@Column
	Integer numericCode;

	@ManyToOne(optional = false)
	@JoinColumn(name = "subcontinent")
	@NonNull
	final Subcontinent subcontinent;

	@Column
	@NonNull
	String name;

	@Column
	String nativeName;

	@Column
	String flag;

	@Column
	String flagUnicode;

}