import TI.*;
public class Wheels
{
    private Servo leftServo;
    private Servo rightServo;

    public Wheels(){
        leftServo = new Servo(15);
        rightServo = new Servo(14);
    }

    public void stop(){
        leftServo.stop();
        rightServo.stop();
    }

    public void setSpeed(int speed){
        setSpeedLeft(speed);
        setSpeedRight(speed);
    }
    
    public void setSpeed(int speedLeft, int speedRight){
        setSpeedLeft(speedLeft);
        setSpeedRight(speedRight);
    }
    
    public void setSpeedLeft(int speed){
        leftServo.update(1500+speed);
    }
    
    public void setSpeedRight(int speed){
        rightServo.update(1500-speed);
    }
    
    public void turnRightOnAxle(){
        setSpeed(100,-100);
    }
    
    public void turnLeftOnAxle(){
        setSpeed(-100,100);
    }
}