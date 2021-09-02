package com.proyect.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.proyect.pages.PaginaRegistro;

public class Registro_Test {
	
	private WebDriver driver;
	PaginaRegistro paginaRegistro;

	@Before
	public void setUp() throws Exception {
		paginaRegistro = new PaginaRegistro(driver);
		driver = paginaRegistro.chrimeDriverConection();
		paginaRegistro.visit("http://automationpractice.com/index.php");
	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		paginaRegistro.registro();
		assertTrue(paginaRegistro.DisplayPaginaWelcome());
	}

}
