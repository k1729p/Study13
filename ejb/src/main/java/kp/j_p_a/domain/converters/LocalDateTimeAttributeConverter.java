package kp.j_p_a.domain.converters;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * The {@link AttributeConverter} implementation for the {@link LocalDateTime}.
 *
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	/**
	 * The constructor.
	 */
	public LocalDateTimeAttributeConverter() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
		return Objects.nonNull(localDateTime) ? Timestamp.valueOf(localDateTime) : null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
		return Objects.nonNull(timestamp) ? timestamp.toLocalDateTime() : null;
	}
}