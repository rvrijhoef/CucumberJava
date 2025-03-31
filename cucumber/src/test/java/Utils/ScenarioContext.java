package Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ScenarioContext is bedoeld om step varaiabele waardes te delen over steps heen.
 * Voorbeeld gebruik:
 * Setter: testContext.scenarioContext.setContext("key",value);
 * Getter: String keyValue = (String)testContext.scenarioContext.getContext("key");
 */
public class ScenarioContext {

    private  Map<String, Object> scenarioContext;

    public ScenarioContext(){
        this.scenarioContext = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        this.scenarioContext.put(key, value);
    }

    public Object getContext(String key){
        return this.scenarioContext.get(key);
    }

    public Boolean isContains(String key){
        return this.scenarioContext.containsKey(key);
    }

}