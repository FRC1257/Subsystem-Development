package frc.robot.subsystems.arm;

import edu.wpi.first.math.util.Units;

public class ArmConstants {
  public static final double MAX_POSITION = 300;
  public static final double MIN_POSITION = 20;

  public static final double MAX_VELOCITY = 4;
  public static final double MAX_ACCELERATION = 8;

  // sim
  public static final double[] kPivotSimPID = {15, 0, 0, 0};
  public static final double[] kPivotSimFF = {0, 0.574, 0, 0};

  // The P gain for the PID controller that drives this arm.
  public static final double kDefaultArmSetpointDegrees = Units.degreesToRadians(75.0);

  // distance per pulse = (angle per revolution) / (pulses per revolution)
  // = (2 * PI rads) / (4096 pulses)
  public static final double kArmEncoderDistPerPulse = 1 / 4096;

  public static final double kArmReduction = 200;
  public static final double kArmMass = 10.0; // Kilograms
  public static final double kArmLength = Units.inchesToMeters(20);
}
