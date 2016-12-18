import TI.*;
/**
 * Deze klasse laat de Boebot een lijn volgen.
 */
public class Engine
{
    private Wheels wheels;
    private LineSensor lineSensor;
    private final int standardSpeed;
    private final int maxSpeed;
    private int currentSpeed;
    private int speedIncrease;
    
    /**
     * In de contructor worden de benodigdheden aangemaakt om de engine goed te kunnen besturen.
     * De standard speed is de standaard snelheid die de BoeBot zal rijden.Dit is NIET de maximumsnelheid.
     * De maximumsnelheid van de BoeBot staat standaard op 1700, wat in deze code omgezet word naar 200.
     * De currentSpeed houd de huidige snelheid van de BoeBot bij, zodat er dynamisch gebruik kan worden gemaakt van een versneller.
     * De speedIncrease is de snelheid waarmee de BoeBot word verhoogd zolang hij op een recht stuk rijd.
     */
    public Engine()
    {
        wheels = new Wheels();
        lineSensor = new LineSensor();
        standardSpeed = 100;
        maxSpeed = 200;
        
        currentSpeed = 100;
        speedIncrease = 1;
    }
    
    /**
     * In de contructor worden de benodigdheden aangemaakt om de engine goed te kunnen besturen.
     * De standard speed is de standaard snelheid die de BoeBot zal rijden. Deze word aangegeven met een parameter. Dit is NIET de maximumsnelheid.
     * De maximumsnelheid van de BoeBot staat standaard op 1700, wat in deze code omgezet word naar 200.
     * De currentSpeed houd de huidige snelheid van de BoeBot bij, zodat er dynamisch gebruik kan worden gemaakt van een versneller.
     * De speedIncrease is de snelheid waarmee de BoeBot word verhoogd zolang hij op een recht stuk rijd.
     */
    public Engine(int standardSpeed)
    {
        wheels = new Wheels();
        lineSensor = new LineSensor();
        this.standardSpeed = standardSpeed;
        maxSpeed = 200;
        
        currentSpeed = 100;
        speedIncrease = 1;
    }
    
    /**
     * 0 = Alle sensoren zien wit                               -rechtdoor
     * 3 = linker sensor ziet zwart                             -naar rechts bijsturen
     * 5 = middelste sensor ziet zwart                          -rechtdoor
     * 7 = rechter sensor ziet zwart                            -naar links bijsturen
     * 8 = midden en links zien zwart                           -een beetje naar rechts bijsturen
     * 10 = links en rechts zien zwart                          -niet mogelijk
     * 12 = midden en rechts zien zwart                         -een beetje naar links bijsturen
     * 15 = Alle sensoren zien zwart                            -op een kruising
     */
    public boolean followLine()
    {
        switch(lineSensor.getState())
        {
            case 0:     goForwardFaster();  break;
            case 3:     adjustToRight();    break;
            case 5:     goForwardFaster();  break;
            case 7:     adjustToLeft();     break;
            case 8:     adjustToRight();    break;
            case 12:    adjustToLeft();     break;
            case 15:    stop();             return true;
            default:    goForwardFaster();  break;
        }
        return false;
    }
    
    /**
     * Elke keer dat deze methode word aangeroepen gaat de Boebot een klein beetje sneller.
     * Uitgaande van een wait van 10 in de main loop, en een increase van 1, zal de BoeBot om maximale snelheidzijn in --> (1*100) * 10 = 1000 ms --> in ongeveer 1 seconden.
     * Zodra een methode word aangeroepen waarin de BoeBot niet meer rechtdoor gaat zal de snelheid worden gereset naar de standaart snelheid van 100
     */
    public void goForwardFaster(){
        if(currentSpeed >= maxSpeed)
            currentSpeed = maxSpeed;
        else if(currentSpeed < standardSpeed)
            currentSpeed = standardSpeed;
        else
            currentSpeed += speedIncrease;
        wheels.setSpeed(currentSpeed);      
    }
    
    /**
     * Deze methode reset de currentSpeed weer naar de standaard snelheid.
     * Deze methode word bij elke andere methode aangeroepen die de BoeBot iets anders laat doen dan rechtdoorrijden.
     */
    public void speedReset(){
        currentSpeed = standardSpeed;
    }
    
    /**
     * Laat de BoeBot met de standaard snelheid vooruit rijden.
     */
    public void goForward(){
        wheels.setSpeed(standardSpeed);
    }
    
    /**
     * Laat de BoeBot met de standaard snelheid achteruit rijden.
     */
    public void goBackward(){
        wheels.setSpeed(-standardSpeed);
        speedReset();
    }
    
    /**
     * Laat de BoeBot stoppen
     */
    public void stop(){
        wheels.stop();
        speedReset();
    }
    
    /**
     * stuurt de BoeBot bij naar links, door 1 servo op de standaard snelheid te zetten en de andere op 10% van de standaard snelheid.
     */
    public void adjustToLeft(){
        wheels.setSpeed(standardSpeed,(standardSpeed/10));
        speedReset();
    }
    
    /**
     * stuurt de BoeBot bij naar rechts, door 1 servo op de standaard snelheid te zetten en de andere op 10% van de standaard snelheid.
     */
    public void adjustToRight(){
        wheels.setSpeed((standardSpeed/10),standardSpeed);
        speedReset();
    }
}
