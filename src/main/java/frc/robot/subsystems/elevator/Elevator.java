import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;




public class Elevator extends SubsystemBase {
  
  
  private final ElevatorIO io;
  private final ElevatorIOInputsAutoLogged inputs = new ElevatorIOInputsAutoLogged();



  public Elevator(ElevatorIO io) {
    
    this.io = io;

    SysId =
    new SysIdRoutine(
        new SysIdRoutine.Config(
            Volts.per(Second).of(ElevatorConstants.SYSID_RAMP_RATE),
            Volts.of(ElevatorConstants.SYSID_STEP_VOLTAGE),
            Seconds.of(ElevatorConstants.SYSID_TIME),
            (state) -> Logger.recordOutput("Elevator/SysIdTestState", state.toString())),
        new SysIdRoutine.Mechanism(
            v -> io.setVoltage(v.in(Volts)),
            (sysidLog) -> {
              sysidLog
                  .motor("Elevator")
                  .voltage(m_appliedVoltage.mut_replace(inputs.appliedVoltage, Volts))
                  .linearPosition(m_position.mut_replace(inputs.positionMeters, Meters))
                  .linearVelocity(
                      m_velocity.mut_replace(inputs.velocityMetersPerSec, MetersPerSecond));
            },
            this));
}
  }


@Override
public void periodic() {
  io.updateInputs(ElevatorInputs);
}

// Sets the PID setpoint 
public void setPID(double setpoint) {
  if (setpoint > ElevatorConstants.ELEVATOR_MAX_HEIGHT)
    setpoint = ElevatorConstants.ELEVATOR_MAX_HEIGHT;
  else if (setpoint < ElevatorConstants.ELEVATOR_MIN_HEIGHT)
    setpoint = ElevatorConstants.ELEVATOR_MIN_HEIGHT;
  io.setSetpoint(setpoint);
}

public Command runVoltageCommand(Supplier<Voltage> voltage) {
    return run(() -> io.setVoltage(voltage.get())).withName("Voltage");
  }

 public Command runRPMCommand(Supplier<AngularVelocity> rpm) {
    return run(() -> io.setRPM(rpm.get())).withName("RPM");
  }   




