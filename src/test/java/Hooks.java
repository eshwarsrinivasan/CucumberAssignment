import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.Context;
import utils.ScenarioContext;

public class Hooks extends ScenarioContext {
    private ScenarioContext context;

    public Hooks(ScenarioContext context){
        this.context = context;
    }

    @Before
    public void beforeScenario() {
    }
    @After
    public void afterScenario(){
        System.out.println(context.getContext(Context.RESPONSE));
        context.cleanContext();
        System.out.println("Cleaned context variable for next scenario");
        System.out.println(context.getContext(Context.RESPONSE));
        }
    }

