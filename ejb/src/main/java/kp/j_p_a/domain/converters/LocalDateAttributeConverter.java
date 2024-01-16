package kp.j_p_a.domain.converters;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * The {@link AttributeConverter} implementation for the {@link LocalDate}.
 *
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

	/**
	 * The constructor.
	 */
	public LocalDateAttributeConverter() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Date convertToDatabaseColumn(LocalDate localDate) {
		return Objects.nonNull(localDate) ? Date.valueOf(localDate) : null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public LocalDate convertToEntityAttribute(Date date) {
		return Objects.nonNull(date) ? date.toLocalDate() : null;
	}
}