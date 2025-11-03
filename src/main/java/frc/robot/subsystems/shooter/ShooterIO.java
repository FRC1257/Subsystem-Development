package frc.robot.subsystems.algaeIntake;

package frc.robot.subsystems.Shooter;

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

public interface ShooterIO {

@Override
public void setVoltage(double voltage){
}
public void setPIDGains(double Kp, double Ki, double Kd){
}

public void setRPM(double rpm){
}
public void setFeedforwardGains(double Ks, double Kv, double Ka) {
}

}