package br.ce.rafael.bcsilva.tasks.functional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no bot�o Add Todo
			driver.findElement(By.id("addTodo")).click();
			// Escrever a descri��o
			driver.findElement(By.id("task")).sendKeys("Selenium Tests");
			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("25/12/2022");
			// Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {
			// fechar browser
			driver.quit();
		}

	}

	@Test
	public void NaodeveSalvarTarefaSemDescricao() {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no bot�o Add Todo
			driver.findElement(By.id("addTodo")).click();
			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("25/12/2022");
			// Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally {
			// fechar browser
			driver.quit();
		}

	}

	@Test
	public void naodeveSalvarTarefaSemData() {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no bot�o Add Todo
			driver.findElement(By.id("addTodo")).click();
			// Escrever a descri��o
			driver.findElement(By.id("task")).sendKeys("Selenium Tests");
			// Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {
			// fechar browser
			driver.quit();
		}

	}
	@Test
	public void naodeveSalvarTarefaComDataPassada() {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no bot�o Add Todo
			driver.findElement(By.id("addTodo")).click();
			// Escrever a descri��o
			driver.findElement(By.id("task")).sendKeys("Selenium Tests");
			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("25/12/2020");
			// Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			// fechar browser
			driver.quit();
		}

	}
}
