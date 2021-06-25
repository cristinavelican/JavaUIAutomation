import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:cucumber.json"},
        features = "src/main/java/com/TSN/features",
        tags = "@search",
        glue = {"com.TSN.features.step_definitions"})

public class RunnerTest {
}
