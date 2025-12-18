package frc.robot.subsystems.dropper;

import static frc.robot.Constants.NEO_CURRENT_LIMIT;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.system.plant.DCMotor;

public class DropperIOSim implements DropperIO {
    private  SparkMax leftMotor = new SparkMax(DropperConstants.LEFT_DROPPER_MOTOR_ID, MotorType.kBrushless);

    @Override
    public default void updateInputs(DropperIOInputs inputs) {
        sim.update(0.02); //updates
        inputs.appliedVoltage = leftMotor.getAppliedOutput() * leftMotor.getBusVoltage(); //from spark max
        inputs.angVelocityRadsPerSec = leftEncoder.getVelocity() * 2 * pi / 60;
        inputs.angleRads = leftEncoder.getPosition() * 2 * pi;
        inputs.angle = leftEncoder.getPosition() * 360;
        inputs.velocity = leftEncoder.getVelocity();
      }

    @Override
    public default double getAngle() {
        return DropperIOInputs.angle;
    }

    @Override
    public default double getAngVelocity() {
        return DropperIOInputs.velocity;
    }

    @Override
    public default void setVoltage(double voltage) {

    }
    
    @Override
    public default void setPosition(int position) {

    }
    
    @Override
    public default void stop() {}

    // PID

    @Override
    public default void setPIDGains(double kp, ki, kd) {
        
    }
    
    @Override
    public default double getP() {
        
    }
    
    @Override
    public default double getI() {
        
    }
    
    @Override
    public default double getD() {
        
    }
}
