package frc.robot.subsystems.Intake;

public class IntakeConstants {
  // MOTOR ID'S
  public static final int FRONT_MOTOR_ID = 0;
  public static final int BACK_MOTOR_ID = 0;


  public static final int INTAKE_MOTOR_ID = 14;
  public static final double INTAKE_IN_SPEED = 0.3;
  public static final double INTAKE_WEAK_IN_SPEED = 0;
  public static final double INTAKE_OUT_SPEED = -0.3;
  public static final double INTAKE_TOLERANCE = 1;

  public static class IntakeSimConstants {
    public static final double kIntakeP = 0.001;
    public static final double kIntakeI = 0.0;
    public static final double kIntakeD = 0.0;

    public static final double kIntakeGearing = 1.2;
    public static final double kIntakeDrumRadius = 0.03;
    public static final double kCarriageMass = 0.15; // Mass in Kg
    public static final double kMomentOfInertia =
        0.5
            * kCarriageMass
            * kIntakeDrumRadius
            * kIntakeDrumRadius; // Moment of inertia represents how resistant to force
    // something is
  }
}
