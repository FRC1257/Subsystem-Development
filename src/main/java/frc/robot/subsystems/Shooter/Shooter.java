package frc.robot.subsystems.Shooter;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;







import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.LoggedDashboardBoolean;


import static frc.robot.subsystems.Shooter.ShooterIO.*;

import java.util.function.DoubleSupplier;



import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.LoggedDashboardBoolean;

import static frc.robot.subsystems.Shooter.ShooterConstants.*;

import java.util.function.DoubleSupplier;


public class Shooter extends SubsystemBase {
  public ShooterIO ShooterIO;
    public Shooter(ShooterIO io) {
        ShooterIO = io;
    }

    public Command runVoltage(DoubleSupplier voltage) {
      return new RunCommand(()->ShooterIO.setVoltage(voltage.getAsDouble()), this)
      .withName("Shooter Voltage");
    }


    public Command runRPM(DoubleSupplier rpm){
      return new RunCommand(()->ShooterIO.setRPM(rpm.getAsDouble()), this)
      .withName("Shooter RPM");
    }


    
}
