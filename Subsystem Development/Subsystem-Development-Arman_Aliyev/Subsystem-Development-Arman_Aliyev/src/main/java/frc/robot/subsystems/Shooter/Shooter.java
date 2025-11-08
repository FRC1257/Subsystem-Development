package frc.robot.subsystems.Shooter;
   

//do not directly refrense the motor
//higher level logic here

//periodic method: runs every 20ms, needs io.updateIn, and proccesssInputs


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax; 


import java.util.function.DoubleSupplier;
import java.util.logging.Logger;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class Shooter extends SubsystemBase {

    private final ShooterIO io;
    private ShooterIOInputsAutoLogged inputs = new ShooterIOInAutoLogged();//??

    public Shooter(ShooterIO io){
        this.io = io; // ??
    }

    @Override
    public void periodic(){
        io.updateIn(inputs);
        Logger.processInputs(key:"Shooter", inputs); //key makes a drop down in adv kit
    }

public class Shooter extends SubsystemBase{

public double getVoltage(){
    return io.getVoltage;
}


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

    public void setPIDGains(double Kp, double Ki, double Kd) {
        io.setPIDGains(Kp, Ki, Kd);
      }
 



}
}


