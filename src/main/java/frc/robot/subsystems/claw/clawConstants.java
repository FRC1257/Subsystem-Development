package frc.robot.subsystems.claw;
//Idk how to do the auto importation and idk what to import


public class ClawConstants{
    //abitrary number
    public static final int Claw_Motor_ID = 1;

    //arbitrary number
    public static final double ClawGearing = 4.0;

    //arbitrary number
    public static final double MomentofInertia = 6.0;

    //voltage to open the claw, negative to run the motor in reverse
    public static final double Claw_Open_Voltage = -6.0;
    
    //voltage to close claw
    public static final double Claw_Close_Voltage = 6.0;

    //max safe amps to stop opening voltage
    public static final double Max_Safe_Amps = 50.0;
}