package com.proyect.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.proyect.pages.Links;
import com.proyect.pages.PaginaLogin;

public class RevisionEnlaces_Test {
	
	private WebDriver driver;
	Links links;

	@Before
	public void setUp() throws Exception {
		links = new Links(driver);
		driver = links.chrimeDriverConection();
		links.visit("http://automationpractice.com/index.php");
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void revisionLink() {
		assertTrue("Hay enlaces rotos", links.revisionLink());
	}

}
