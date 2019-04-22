package test;

import static org.junit.Assert.*;

import java.util.Optional;
import java.util.Properties;

import org.junit.Test;

import ex10.OptionalExample;

public class OptionalTest {

	@Test
	public void readDurationTest() {
		Properties param = new Properties();
		param.setProperty("a", "5");
		param.setProperty("b", "true");
		param.setProperty("c", "-3");

		assertEquals(5, readDuration(param, "a"));
		assertEquals(0, readDuration(param, "b"));
		assertEquals(0, readDuration(param, "c"));
		assertEquals(0, readDuration(param, "d"));
	}

	public int readDuration(Properties props, String name) {
		return Optional.ofNullable(props.getProperty(name))
					    .flatMap(OptionalExample::stringToInt)
					    .filter(i -> i > 0)
					    .orElse(0);
	}
}
