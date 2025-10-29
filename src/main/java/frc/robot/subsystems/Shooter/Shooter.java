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
    public Shooter(ShooterIO io) {
        ShooterIO = io;
        //why is there an error?
    }

    public Command runVoltage(DoubleSupplier voltage) {
      //says there's an error bc setVoltage parameter is double not DoubleSupplier??
      return new FunctionalCommand(
        () -> setVoltage(voltage),
        () -> {setVoltage(voltage);},
        (interrupted) -> {
          if (interrupted) {
            ShooterIO.stop();
          }
        },
        () -> false,
        this
      );
    }


    public Command runRPM(DoubleSupplier rpm){
      //same issue as above i think
      //i thought setting it as a double would work
      double r = rpm.getAsDouble();
      return new FunctionalCommand(
        () -> setRPM(r),
        () -> {setRPM(r);},
        (interrupted) -> {
          if (interrupted) {
            ShooterIO.stop();
          }
        },
        () -> false,
        this
      );
    }
    
}
