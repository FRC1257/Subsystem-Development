package frc.robot.subsystems.shooter;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import java.util.function.DoubleSupplier;

// Main class 
public class Shooter extends SubsystemBase {
    
    public ShooterIO io; 
  
    public command void runVoltage(DoubleSupplier voltage) {
    
        public Command runVoltage(DoubleSupplier voltage) {
            return new RunCommand(()->setVoltage(voltage.getAsDouble()), this)
        .withName("voltage");
        }
    }
    public Command void runRPM(DoubleSupplier rpm) {
        public Command runRPM(DoubleSupplier rpm) {
            return new RunCommand(()->setRPM(rpm.getAsDouble()), this)
        .withName("RPM");
        }
    }
    

   
}

