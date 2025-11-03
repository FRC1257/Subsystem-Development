package frc.robot.subsystems.shooter;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    public ShooterIO ShooterIO;
    public Shooter(ShooterIO io){
            ShooterIO = io;
    }

    public Command runVoltage(DoubleSupplier voltage){
        return new RunCommand(()->ShooterIO.setVoltage(voltage.getAsDouble()), this).withName("Shooter Voltage");
    }
    public Command runRPM(DoubleSupplier rpm){
        return new RunCommand(()->ShooterIO.setRPM(rpm.getAsDouble()), this).withName("Shooter RPM");
    }
}