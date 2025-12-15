package frc.robot.subsystems.intake;
import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
 {
    public static class IntakeIOInputs {
    public double velocityRadsPerSec = 0.0;

    public double appliedVoltage = 0.0;
    public double[] currentAmps = new double[] {};
    public double[] tempCelcius = new double[] {};
  }
  public void setBrake(boolean brake) {}

  public void updateInputs(IntakeIOInputs inputs) {}

  public void setVoltage(double voltage) {}

  public double getVoltage() {
    return 0;
  }
  public void setDirection(boolean forward){}
}
