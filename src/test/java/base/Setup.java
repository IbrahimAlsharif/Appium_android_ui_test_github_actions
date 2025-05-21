package base;

import configs.pipeline.PipelineConfig;
import configs.testRail.TestRailManager;
import configs.testdata.TestData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;


public class Setup {

    public static WebDriver driver;
    public static TestData testData;
    public static WebDriverWait wait;
    public static String testCaseId;
    public static final TestRailManager testRail = new TestRailManager();


    @Test(priority = 1)
    @Parameters({"language", "branch", "device", "platform"})
    public void setUpLocalDriver(String language, String branch, String device, String platform) throws Exception {
        System.setProperty("selenium.http.factory", "apache");
        String apkPath = "/Users/ibrahimalsharif/IdeaProjects/Appium_android_ui_test_github_actions/src/app-debug.apk";
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(device).
                setPlatformName(platform).
                setAutomationName("UiAutomator2")
                .setApp(apkPath)
                .setAppPackage("jinyoung.dev.todolist")
                .setAppActivity(".ui.actionitem.MainActivity")
                .setAllowDelayAdb(true);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        configureHelperComponents();

        if (PipelineConfig.testRailReport) {
            TestRailManager testRailManager = new TestRailManager();
            Go.testRunId = testRailManager.createTestRun("ProjectName", 1);
        }

    }

    private void configureHelperComponents() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        Go.initialize(driver, javascriptExecutor, wait);
        Finder.initialize(driver, wait);
    }

}
