package frc.robot.subsystems.Shooter;
    


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax; 


import java.util.function.DoubleSupplier;


public class Shooter extends SubsystemBase {
   
    private final MotorController frontMotor;
    private final MotorController rearMotor;

public class Shooter extends SubsystemBase{


public Command runVoltage(DoubleSupplier voltage){
        return this.run(() -> {
            double volts = voltage.getAsDouble();
            setVoltageInternal(volts);
        }).finallyDo(() -> stop());
        }


public Command runRPM(DoubleSupplier rpm){
    return this.run(() -> {
        double targetRPM = rpm.getAsDouble();
        setVelocityInternal(targetRPM);
    }).finallyDo(() -> stop());
    }


 public void setSpeed(double speed) {
        frontMotor.set(speed);
        rearMotor.set(speed);
 }
 public void setSpeeds(double frontSpeed, double rearSpeed) {
    frontMotor.set(frontSpeed);
    rearMotor.set(rearSpeed);
    //maybe not needed
}

public void shoot(){
setSpeed(defaultShootSpeed);
}

public void stop(){
    frontMotor.set(0);
    rearMotor.set(0);
}
}
}

