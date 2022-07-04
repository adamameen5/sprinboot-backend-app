package com.example.demo;

import org.assertj.core.api.AssertionsForClassTypes;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class DemoApplicationTests {

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		//Given
		int numberOne = 20;
		int numberTwo = 30;

		//When
		int result = underTest.add(numberOne,numberTwo);

		//then
		int expected = 50;
		assertThat(result).isEqualTo(expected);

	}

	class Calculator{
		int add(int a, int b){
			return a+b;
		}
	}

}
