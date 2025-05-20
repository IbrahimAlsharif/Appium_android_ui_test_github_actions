package base;

import configs.pipeline.PipelineConfig;
import configs.testRail.TestRailManager;
import configs.testdata.TestData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Setup {

    public static WebDriver driver;
    public static TestData testData;
    public static WebDriverWait wait;
    public static String testCaseId;
    public static final TestRailManager testRail = new TestRailManager();


    @Test(priority = 1)
    @Parameters({"language", "branch", "device","platform"})
    public void setUpLocalDriver(String language, String branch, String device, String platform) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", device);
        capabilities.setCapability("app_activity", ".ui.actionitem.MainActivity");
        capabilities.setCapability("allowTestPackages", "true");
        capabilities.setCapability("app_package", "jinyoung.dev.todolist");

        if (PipelineConfig.testRailReport) {
            TestRailManager testRailManager = new TestRailManager();
            Go.testRunId = testRailManager.createTestRun("ProjectName", 1);
        }
        String apkPath = "src/app-debug.apk";
        capabilities.setCapability(MobileCapabilityType.APP, apkPath);
        File app = new File(apkPath);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        configureHelperComponents();
    }

    private void configureHelperComponents() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        Go.initialize(driver, javascriptExecutor, wait);
        Finder.initialize(driver, wait);
    }
}
