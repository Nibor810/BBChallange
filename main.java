import TI.*;
public class main
{
    public static void main(String[] args)
    {
        Engine engine = new Engine();
        while(true)
        {
            engine.followLine();
            BoeBot.wait(10);
        }
    }
}
