
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
 


public class Selenium {


        WebDriver driver=new ChromeDriver();
		
    @BeforeClass
    
    public void before(){
		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
		driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    //n11 sayfa a��l���
    @Test
    public void Siten11(){

        driver.get("http://www.n11.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue(driver.getTitle().equals("n11.com - Al��veri�in U�urlu Adresi")); //kontrol
        System.out.println("Web Sitesi A��ld�");
    }

    //Login sayfas� a��l���
    @Test
    public void Loginpage(){
       driver.findElement(By.className("btnSignIn")).click();
       Assert.assertTrue(driver.findElement(By.id("loginButton")).getText().equals("�ye Giri�i"));
       Assert.assertTrue(driver.getTitle().equals("Giri� Yap - n11.com"));
       System.out.println("Kullan�c� giri� sayfas� a��ld�.");
    }


    //Kullan�c� giri�i
    @Test
    public void login(){
       driver.findElement(By.id("email")).sendKeys("sgrserap@hotmail.com");
       driver.findElement(By.id("password")).sendKeys("asd123w");
       driver.findElement(By.id("loginButton")).click();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       Assert.assertTrue(driver.getTitle().equals("n11.com - Al��veri�in U�urlu Adresi"));
       System.out.println("Kullan�c� ba�ar�l� giri� sa�lad�.");

    }
 

    //Arama
    @Test
   public void search(){
       Wait<WebDriver> wait = new WebDriverWait(driver, 10);
       wait.until(ExpectedConditions.elementToBeClickable((By.id("searchData"))));
       driver.findElement(By.id("searchData")).sendKeys("samsung");
       driver.findElement(By.className("searchBtn")).click();
       wait.until(ExpectedConditions.elementToBeClickable((By.className("active"))));
       String arama=driver.findElement(By.xpath("//*[@id='contentListing']/div/div/div[2]/section/div[1]/div[1]")).getText();
       Assert.assertTrue(arama.contains("sonu� bulundu."));
       System.out.println("Samsung i�in sonu� bulundu");
    }

    //2.sayfa a��l�r
    @Test
    public void secondpage(){
       driver.findElement(By.xpath("//*[@id='contentListing']/div/div/div[2]/div[4]/a[2]")).click();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       Assert.assertTrue(driver.getTitle().contains("Samsung - n11.com - 2/11563"));
       System.out.println("2. Sayfa G�sterimde ...");
    }

 
    // �r�n se�me
    @Test
    public void product(){
       Wait<WebDriver> wait = new WebDriverWait(driver, 10);
       wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id='p-324125523']/div[2]/span"))));
       driver.findElement(By.xpath("//*[@id='p-274590775']/div[1]/a/h3")).getText();
       driver.findElement(By.xpath("//*[@id='p-274590775']/div[2]/span")).click();
       System.out.println("Favoriye Eklenen �r�n Ad�");

    }

    //Favorilere ekleme
    @Test
    public void addfavorite(){
       WebElement admin = driver.findElement(By.xpath("//*[@id='header']/div/div/div[2]/div[2]/div[2]/div[1]"));
       new Actions(driver).moveToElement(admin).perform();
       WebElement userManagement = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id("//*[@id='header']/div/div/div[2]/div[2]/div[2]/div[2]/div/a[2]")));
       userManagement.click();
       driver.findElement(By.xpath("//*[@id='myAccount']/div[3]/ul/li[1]/div/a")).click();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

 
    //�r�n kontrol
    @Test
    public void ProductControl(){
       String favproduct =driver.findElement(By.xpath("//*[@id='p-274590775']/div[1]/a/h3")).getText();
       WebElement selected=driver.findElement(By.id("p-324125523"));
       if (favproduct.equals(selected)){
       System.out.println("Favorilere eklenen �r�n do�ruland�");
       Assert.assertTrue(favproduct.equals(selected));
       }
    }


    //�r�n� silme
    @Test
    public void delete() {
       driver.findElement(By.xpath("//*[@id='p-274590775']/div[3]/span")).click();
       WebElement message= driver.findElement(By.className("message"));
       Assert.assertTrue(message.getText().equals("�r�n�n�z listeden silindi."));
       driver.findElement(By.xpath("/html/body/div[5]/div/div")).click();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

     }

    //Silme kontrol�
    @Test
    public void deleteControl(){
       driver.findElement(By.xpath("//*[@id='myAccount']/div[3]/div/h2"));
       if(driver.findElement(By.id("p-324125523")) != null){
    	   System.out.println("�r�n favorilerim listesinden silinmemi�tir.");
       }
       else {
    	   System.out.println("�r�n silinmi�tir.");
       }
        }

    @AfterClass
    
    public void tearDown() throws InterruptedException{
       Thread.sleep(3000);
       driver.quit();
    }
}


