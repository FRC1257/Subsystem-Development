import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;




public class Elevator extends SubsystemBase{
    private final ElevatorIOInputsAutoLogged inputs = new ElevatorIOInputsAutoLogged();



private final ElevatorIO io;

@Override
public void periodic() {
  ElevatorIO.updateInputs(ElevatorInputs);
}

public Command runVoltageCommand(Supplier<Voltage> voltage) {
    return run(() -> ElevatorIO.setVoltage(voltage.get())).withName("Voltage");
  }

 public Command runRPMCommand(Supplier<AngularVelocity> rpm) {
    return run(() -> ElevatorIO.setRPM(rpm.get())).withName("RPM");
  }   




}