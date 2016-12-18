import TI.*;
public class LineSensor
{
    private int grenswaarde;
    private int pulseLeft;
    private int pulseCenter;
    private int pulseRight;
    private int waarde;
    
    public LineSensor()
    {
        grenswaarde = 1200;
    }
    
    public int getState()
    {
        waarde = 0;
        pulseLeft = BoeBot.analogRead(0);
        pulseCenter = BoeBot.analogRead(1);
        pulseRight = BoeBot.analogRead(2);
        if(pulseLeft > grenswaarde)
            waarde += 3;
        if(pulseCenter > grenswaarde)
            waarde += 5;
        if(pulseRight > grenswaarde)
            waarde += 7;
        return waarde;
    }

}