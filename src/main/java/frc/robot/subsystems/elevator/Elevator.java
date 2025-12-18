import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;




public class Elevator extends SubsystemBase{
  
  
  private final ElevatorIO io;
  private final ElevatorIOInputsAutoLogged inputs = new ElevatorIOInputsAutoLogged();



  public Elevator(ElevatorIO io) {
    this.io = io;
  }


@Override
public void periodic() {
  io.updateInputs(ElevatorInputs);
}

public Command runVoltageCommand(Supplier<Voltage> voltage) {
    return run(() -> io.setVoltage(voltage.get())).withName("Voltage");
  }

 public Command runRPMCommand(Supplier<AngularVelocity> rpm) {
    return run(() -> io.setRPM(rpm.get())).withName("RPM");
  }   




}