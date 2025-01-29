import io.restassured.path.json.JsonPath;
import org.example.steps.CarTextStepDefs;
import org.junit.jupiter.api.Test;

public class carTextTests {

    private final CarTextStepDefs carTextStepDefs = new CarTextStepDefs();

    @Test
    public void readText() {
        carTextStepDefs.extractCarInputWithContains();
    }

    @Test
    public void readTextRegix() {
        String carRegNo = carTextStepDefs.extractRandomCarInputWithPattern();

        System.out.println("carRegNo Input :: " + carRegNo);
        String strings = carTextStepDefs.extractCarOutput(carRegNo);

        System.out.println("Car Reg Test  " + strings);
        JsonPath js = new JsonPath(strings);
        js.getString("");
    }
}
