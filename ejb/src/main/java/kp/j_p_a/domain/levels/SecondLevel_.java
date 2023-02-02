package kp.j_p_a.domain.levels;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

/**
 * The {@link SecondLevel} entity class Metamodel.
 *
 */
@SuppressWarnings("java:S101") // switch off Sonarqube rule 'class naming convention'
@StaticMetamodel(SecondLevel.class)
public class SecondLevel_ extends Level_ {
	/**
	 * The third levels.
	 */
	public static volatile SetAttribute<SecondLevel, ThirdLevel> thirdLevels;// NOSONAR not thread-safe with "volatile"

	/**
	 * The constructor.
	 */
	public SecondLevel_() {
		super();
	}
}