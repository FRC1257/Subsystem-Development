package frc.robot.subsystems.algaeIntake;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import org.littletonrobotics.junction.Logger;

public class ShooterIOSparkMax {

public void setVoltage(double voltage){
    Logger.recordOutput(voltage)
    motor.setVoltage(voltage)
}
public void setPIDGains(double Kp, double Ki, double Kd){
    pidController.setKp(Kp);
    pidController.setKi(Ki);
    pidController.setKd(Kd);
}

public void setRPM(double rpm){
    double output = pidController.calculate(encoder.getVelocity(), rpm);
motor.setVoltage(output);
}

public void setFeedforwardGains(double Ks, double Kv, double Ka) {
    pidController.setKs(Ks);
    pidController.setKv(Kv);
    pidController.setKa(Ka);
}

}