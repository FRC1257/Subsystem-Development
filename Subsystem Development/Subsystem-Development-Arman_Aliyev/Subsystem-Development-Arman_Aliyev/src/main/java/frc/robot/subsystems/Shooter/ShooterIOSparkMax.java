package frc.robot.subsystems.Shooter;
import com.revrobotics.RelativeEncoder;

//lower level logic like setters 
//need configure resetmode and persistmode
//create the pid controller here , configs here too inside contructer
//comfigs - defaults are wrong, configs make adjustments ot settnng and pass, apply - copies from one to another
//follow config - follows some other thing here be set voltage ands get 
// also set and get rpm
//encoder - tells motor where it is, information about the location of motor
//


//update inputs takes previouslt taken inputs and adjusts them to be based on the new inputs

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
//import com.revrobotics.CANSparkMax;

public class ShooterIOSparkMax {
    public class ShooterIOSparkMax implements ShooterIO {
private final CANSparkMax frontMotor;
    private final CANSparkMax rearMotor;
    private final CANSparkMax frontMotor;
    private final RelativeEncoder frontEncoder;
    private final SparkPIDController frontPID;
    
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
    
        //only really needed 1 pid because same speed.
        //backconfig follow each other
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
        inputs.Velocity = getVelocity;
        inputs.voltage = getVoltage;
        
        
        
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

