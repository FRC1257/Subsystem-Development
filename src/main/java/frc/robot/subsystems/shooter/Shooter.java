package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {
    private final ShooterIO io;

    public Shooter(ShooterIO io) {
        this.io=io;
    }

    @Override public void periodic() {
    }


    public double getVoltage() {
        return io.getVoltage();
    }

    public Command runVoltage(DoubleSupplier voltage) {
        return new RunCommand(
                ()->io.setVoltage(voltage.getAsDouble()),
                this
        );
    }

    public Command runRPM(DoubleSupplier rpm) {
        return new RunCommand(
                ()->io.setRPM(rpm.getAsDouble()),
                this
        );
    }

    public void setPIDGains(double Kp, double Ki, double Kd) {
        io.setPIDGains(Kp,Ki,Kd);
    }
}
