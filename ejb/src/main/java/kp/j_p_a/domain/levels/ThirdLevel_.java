package kp.j_p_a.domain.levels;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

/**
 * The {@link ThirdLevel} entity class Metamodel.
 *
 */
@SuppressWarnings("java:S101") // switch off Sonarqube rule 'class naming convention'
@StaticMetamodel(ThirdLevel.class)
public class ThirdLevel_ extends Level_ {
	/**
	 * The fourth levels.
	 */
	public static volatile SetAttribute<ThirdLevel, FourthLevel> fourthLevels;// NOSONAR not thread-safe with "volatile"

	/**
	 * The constructor.
	 */
	public ThirdLevel_() {
		super();
	}
}