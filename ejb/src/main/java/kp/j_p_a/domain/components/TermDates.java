package kp.j_p_a.domain.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import kp.Constants;

/**
 * The <B>term dates</B>.
 *
 */
@Embeddable
public class TermDates {
	/*-
	 * The '@Temporal' may only be used with 'java.util.Date' or 'java.util.Calendar'.
	 * 
	 * For 'LocalDate' and 'LocalDateTime' it was implemented the 'AttributeConverter'.
	 */

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar calendar;

	private LocalDate localDate;

	private LocalDateTime localDateTime;

	/**
	 * The constructor with initialization.
	 * 
	 */
	public TermDates() {
		super();
		this.date = Date.from(Constants.EXAMPLE_ZONED_DATE_TIME.toInstant());
		this.calendar = GregorianCalendar.from(Constants.EXAMPLE_ZONED_DATE_TIME);
		this.localDate = Constants.EXAMPLE_LOCAL_DATE;
		this.localDateTime = Constants.EXAMPLE_LOCAL_DATE_TIME;
	}

	/**
	 * Gets the {@link Date}.
	 * 
	 * @return the {@link Date}
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the {@link Date}.
	 * 
	 * @param date the {@link Date} to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the {@link Calendar}.
	 * 
	 * @return the {@link Calendar}
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * Sets the {@link Calendar}.
	 * 
	 * @param calendar the {@link Calendar} to set
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	/**
	 * Gets the {@link LocalDate}.
	 * 
	 * @return the {@link LocalDate}
	 */
	public LocalDate getLocalDate() {
		return localDate;
	}

	/**
	 * Sets the {@link LocalDate}.
	 * 
	 * @param localDate the {@link LocalDate} to set
	 */
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	/**
	 * Gets the {@link LocalDateTime}.
	 * 
	 * @return the {@link LocalDateTime}
	 */
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	/**
	 * Sets the {@link LocalDateTime}.
	 * 
	 * @param localDateTime the {@link LocalDateTime} to set
	 */
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
}