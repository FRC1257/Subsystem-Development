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




