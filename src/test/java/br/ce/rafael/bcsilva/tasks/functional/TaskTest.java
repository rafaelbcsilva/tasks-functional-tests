package br.ce.rafael.bcsilva.tasks.functional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TaskTest {

	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.1.223:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no botao Add Todo
			driver.findElement(By.id("addTodo")).click();
			// Escrever a descricao
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
	public void NaodeveSalvarTarefaSemDescricao() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no botao Add Todo
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
	public void naodeveSalvarTarefaSemData() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no botao Add Todo
			driver.findElement(By.id("addTodo")).click();
			// Escrever a descricao
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
	public void naodeveSalvarTarefaComDataPassada() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no botao Add Todo
			driver.findElement(By.id("addTodo")).click();
			// Escrever a descricao
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
