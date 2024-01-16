package kp.j_p_a.domain.levels;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

/**
 * The {@link FirstLevel} entity class Metamodel.
 *
 */
@SuppressWarnings("java:S101") // switch off Sonarqube rule 'class naming convention'
@StaticMetamodel(FirstLevel.class)
public class FirstLevel_ extends Level_ {
	/**
	 * The second levels
	 */
	public static volatile SetAttribute<FirstLevel, SecondLevel> secondLevels;// NOSONAR not thread-safe with "volatile"

	/**
	 * The constructor.
	 */
	public FirstLevel_() {
		super();
	}
}
