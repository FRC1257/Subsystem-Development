package frc.robot.subsystems.elevator;

public class ElevatorConstants {
    public static final double[] ELEVATOR_REAL_PID = {0,0,0,0};
    public static final double[] ELEVATOR_REAL_FF = {0,0,0,0};
    public static final double MAX_VELOCITY = 0.0;
    public static final double MAX_ACCELERATION = 0.0;
    public static int LEFT_MOTOR_ID = 1;
    public static int RIGHT_MOTOR_ID = 2;
    public static final double SETPOINT_TOLERANCE_METERS = 0.0;
    public static final int LIMIT_SWITCH_CHANNEL = 0;
    public static double POSITION_CONVERSION_FACTOR = 0.0;
    public static final boolean MOTOR_DEFAULT_IDLE_MODE = true;
    // MAX AND MIN HEIGHTS
    public static final double ELEVATOR_MAX_HEIGHT = 1.0; // meters
    public static final double ELEVATOR_MIN_HEIGHT = 0.005; 

    // MAX velocity and acceleration
    public static final double MAX_ACCELERATION = 4.0; // m/s^2
    public static final double MAX_VELOCITY = 2.0; 

}
