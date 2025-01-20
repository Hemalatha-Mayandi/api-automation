import org.example.utils.PropertyUtils;
import org.junit.jupiter.api.Test;

public class initialTests {

    private static final PropertyUtils propertyUtils = new PropertyUtils("api.properties");

    @Test
    public void test(){
        System.out.println("TESTING ::::" + propertyUtils.getProperty("api.petstore"));
    }

}
