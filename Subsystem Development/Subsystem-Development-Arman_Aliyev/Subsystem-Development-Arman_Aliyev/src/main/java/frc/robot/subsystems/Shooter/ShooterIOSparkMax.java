package frc.robot.subsystems.Shooter;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
//import com.revrobotics.CANSparkMax;

public class ShooterIOSparkMax {
    public class ShooterIOSparkMax implements ShooterIO {
private final CANSparkMax frontMotor;
    private final CANSparkMax rearMotor;
    private final RelativeEncoder frontEncoder;
    private final RelativeEncoder rearEncoder;
    private final SparkPIDController frontPID;
    private final SparkPIDController rearPID;

    private boolean frontBrakeMode = false;
    private boolean rearBrakeMode = false;

    private double Ks = 0.0; // Static gain
    private double Kv = 0.0; // Velocity gain  
    private double Ka = 0.0; // Acceleration gain

    @Override
    public void setFeedforwardGains(double Ks, double Kv, double Ka) {
        this.Ks = Ks;
        this.Kv = Kv;
        this.Ka = Ka;
}

@Override
    public void setPIDGains(double kP, double kI, double kD) {
        
        frontPID.setP(kP);
        frontPID.setI(kI);
        frontPID.setD(kD);
    
        rearPID.setP(kP);
        rearPID.setI(kI);
        rearPID.setD(kD);
    }
    @Override
    public void setFeedforwardGains(double Ks, double Kv, double Ka) {
    
        this.Ks = Ks;
        this.Kv = Kv;
        this.Ka = Ka;
    }
        

public ShooterIOSparkMax() {

    frontMotor = new CANSparkMax(FRONT_SHOOTER_MOTOR_ID, MotorType.kBrushless);
    rearMotor = new CANSparkMax(REAR_SHOOTER_MOTOR_ID, MotorType.kBrushless);

    frontEncoder = frontMotor.getEncoder();
    rearEncoder = rearMotor.getEncoder();
    frontPID = frontMotor.getPIDController();
    rearPID = rearMotor.getPIDController();

    

}

@Override
    public void updateInputs(ShooterIOIn inputs) {
        //updates front and rear motor data, which data im not sure
    
}
@Override
  public void setFrontVoltage(double motorVolts) {
    Logger.recordOutput("Front/Desired Voltage", motorVolts);
    frontMotor.setVoltage(motorVolts);
  }
  @Override
  public void setRearVoltage(double motorVolts) {
    Logger.recordOutput("Rear/Desired Voltage", motorVolts);
    rearMotor.setVoltage(motorVolts);
  }

  public CANSparkMax getFrontMotor() {
    return frontMotor;
}

public CANSparkMax getRearMotor() {
    return rearMotor;
}

}
}

