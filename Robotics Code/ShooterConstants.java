private static final class ShooterConstants {
    private static final int Motor1 = 1;
    private static final int Motor2 = 2;

    private static final MotorType kMotorType = MotorType.kBrushless;
    
    private static final double kP = 0.0001; 
    private static final double kI = 0.0;     
    private static final double kD = 0.0;     
   
    private static final double kMinOutput = -1.0;
    private static final double kMaxOutput = 1.0;

    // Target shooter speed in RPM
    private static final double TARGET_RPM = 4000.0;
  }
    