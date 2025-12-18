package frc.robot.subsystems.dropper;

import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;

public class DropperConstants {

  public static Constraints kDropperConstraints;

  public static final int LEFT_DROPPER_MOTOR_ID = 0;
  public static final int RIGHT_DROPPER_MOTOR_ID = 0;

  public static final double UPPER_LIMIT = 1;
  public static final double LOWER_LIMIT = -1;
  public static final double PI =
      3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679; // 100 digits of pi just cuz
}
