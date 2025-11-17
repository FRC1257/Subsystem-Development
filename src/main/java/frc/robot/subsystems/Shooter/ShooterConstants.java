package frc.robot.subsystems.Shooter;

public class ShooterConstants {
    public static double kP = 0.1;
    public static double kI = 0.0;
    public static double kD = 0.0;

    public static double kV = 0.2; // Volts per (RPM)
    public static double kA = 0.1; // Volts per (RPM/s)

    public static final double MAX_VELOCITY = 5000.0; 
    public static final double MAX_ACCELERATION = 3000.0;

    public static double flywheelReduction = 1.0; // Gear reduction ratio
    public static double momentOfInertia = 0.1; // Moment of inertia of the flywheel

}
