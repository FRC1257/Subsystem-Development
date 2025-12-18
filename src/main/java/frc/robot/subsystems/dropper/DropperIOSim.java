package frc.robot.subsystems.dropper;

import static frc.robot.Constants.NEO_CURRENT_LIMIT;

import edu.wpi.first.math.controller.ProfiledPIDController;

public class DropperIOSim implements DropperIO {
    
    @Override
    public default void updateInputs(DropperIOInputs inputs) {

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
