package Utils;

import library.Library;
import objects.Boek;

public class TestContext {
    protected static Library library;
    private static Exception vorigeException;
    protected static int customerId;
    protected static Boek geleendBoek;
    public ScenarioContext scenarioContext;

    public TestContext() {
        this.scenarioContext = new ScenarioContext();
    }

    protected void verwijderVorigeException(){vorigeException=null;}

    protected void setVorigeException(Exception e){
        System.out.println("De volgende exceptie is opgetreden: " + e.getMessage());
        vorigeException = e;
    }

    protected Exception getVorigeException(){return vorigeException;}
}
