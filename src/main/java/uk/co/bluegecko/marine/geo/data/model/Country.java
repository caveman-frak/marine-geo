package uk.co.bluegecko.marine.geo.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode
@ToString
public class Country {

	@Id
	@NonNull
	String code;

	@NonNull
	String code3;

	@Column
	private Integer numericCode;

	@ManyToOne(optional = false)
	@JoinColumn(name = "subcontinent")
	@NonNull
	Subcontinent subcontinent;

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