package frc.robot.subsystems.shooter;

import edu.wpi.first.math.util.Units;

public class ShooterConstants {

    public static final int SHOOTER_MASTER_ID = 10;
    public static final int SHOOTER_FOLLOWER_ID = 11;

    public static final double SHOOTER_GEARING = 1.0 / 2.0;

    public static final double[] SHOOTER_PID_REAL = {0.1, 0.0, 0.0};
    public static final double[] SHOOTER_FEEDFORWARD_REAL = {0.05, 0.0001, 0.0};

    public static final double[] SHOOTER_PID_REAL_ACTIVE = {0.0, 0.0, 0.0};
    public static final double[] SHOOTER_FEEDFORWARD_REAL_ACTIVE = {0.0, 0.0, 0.0};

    public static final double SHOOTER_RPM_TOLERANCE = 75;
    public static final double SHOOTER_RPM_VELOCITY_TOLERANCE = 50;

    public static final double SHOOTER_OFFSET = 0.2299176;


    public static final double SHOOTER_MAX_VOLTAGE = 12.0;
    public static final double SHOOTER_MIN_VOLTAGE = 0.0;

    public static final double SYSID_RAMP_RATE = 0.5;
    public static final double SYSID_STEP_VOLTAGE = 1.0;
    public static final double SYSID_TIME = 10;
}