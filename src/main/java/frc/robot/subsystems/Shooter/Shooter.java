package frc.robot.subsystems.Shooter;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Second;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.units.measure.MutDistance;
import edu.wpi.first.units.measure.MutLinearVelocity;
import edu.wpi.first.units.measure.MutVoltage;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import java.util.function.DoubleSupplier;
import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.inputs.LoggableInputs;
import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

import static frc.robot.subsystems.Shooter.ShooterIO.*;
import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.LoggedDashboardBoolean;

import static frc.robot.subsystems.Shooter.ShooterConstants.*;

import java.util.function.DoubleSupplier;


public class Shooter extends SubsystemBase {
  private final ShooterIOInputsAutoLogged inputs = new ShooterIOInputsAutoLogged();
  private final ShooterIO io;

  private LoggedNetworkNumber logP;
  private LoggedNetworkNumber logI;
  private LoggedNetworkNumber logD;

  private LoggedNetworkNumber logkS;
  private LoggedNetworkNumber logkG;
  private LoggedNetworkNumber logkV;
  private LoggedNetworkNumber logkA;

  public ShooterIO ShooterIO;
    public Shooter(ShooterIO io) {
        this.io = io;
        SmartDashboard.putData(getName(), this);
        logP = new LoggedNetworkNumber("/SmartDashboard/Shooter/kP", io.getP());
        logI = new LoggedNetworkNumber("/SmartDashboard/Shooter/kI", io.getI());
        logD = new LoggedNetworkNumber("/SmartDashboard/Shooter/kD", io.getD());
    
        logkS = new LoggedNetworkNumber("/SmartDashboard/Shooter/kS", io.getkS());
        logkG = new LoggedNetworkNumber("/SmartDashboard/Shooter/kG", io.getkG());
        logkV = new LoggedNetworkNumber("/SmartDashboard/Shooter/kV", io.getkV());
        logkA = new LoggedNetworkNumber("/SmartDashboard/Shooter/kA", io.getkA());
    }


    @Override
    public void periodic() {
      ShooterIO.updateInputs(inputs);
      Logger.processInputs("Shooter", inputs);
  
      if (logP.get() != ShooterIO.getP()) ShooterIO.setP(logP.get());
  
      if (logI.get() != ShooterIO.getI()) ShooterIO.setI(logI.get());
  
      if (logD.get() != ShooterIO.getD()) ShooterIO.setD(logD.get());
  
      if (logkS.get() != ShooterIO.getkS()) ShooterIO.setkS(logkS.get());
  
      if (logkG.get() != ShooterIO.getkG()) ShooterIO.setkG(logkG.get());
  
      if (logkV.get() != ShooterIO.getkV()) ShooterIO.setkV(logkV.get());
  
      if (logkA.get() != ShooterIO.getkA()) ShooterIO.setkA(logkA.get());
  
      // Log Inputs
      Logger.processInputs("Shooter", inputs);
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
