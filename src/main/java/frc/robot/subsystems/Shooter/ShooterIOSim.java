package frc.robot.subsystems.Shooter;



import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.util.Units;

public class ShooterIOSim implements ShooterIO {
    public void updateInputs(ShooterIOInputs inputs) {
        leftSim.update(0.020);
        rightSim.update(0.020);
        if (leftSetpointRPM != null) {
            leftAppliedVolts =
            pidController.calculate(leftSim.getAngularVelocityRPM(), leftSetpointRPM)
                + leftFF.calculate(leftSetpointRPM);
            leftSim.setInputVoltage(MathUtil.clamp(leftAppliedVolts, -12.0, 12.0));
            Logger.recordOutput("Shooter Voltage Left", MathUtil.clamp(leftAppliedVolts, -12.0, 12.0));
        } // if the value is NOT null - calculate what the applied voltage should be and record it accordingly

        if (rightSetpointRPM != null) {
            rightAppliedVolts =
            pidController.calculate(rightSim.getAngularVelocityRPM(), rightSetpointRPM)
                + rightFF.calculate(rightSetpointRPM);
            rightSim.setInputVoltage(MathUtil.clamp(rightAppliedVolts, -12.0, 12.0));
            Logger.recordOutput("Shooter Voltage Right", MathUtil.clamp(rightAppliedVolts, -12.0, 12.0));
        } // if the value is NOT null - calculate what the applied voltage should be and record it accordingly
    }
    private double leftAppliedVolts = 0.0;
    private double rightAppliedVolts = 0.0;

    private Double leftSetpointRPM = null;
    private Double rightSetpointRPM = null;


    private ProfiledPIDController pidController = new ProfiledPIDController(
        ShooterConstants.kP,
        ShooterConstants.kI,
        ShooterConstants.kD,
        new TrapezoidProfile.Constraints(
            ShooterConstants.MAX_VELOCITY,
            ShooterConstants.MAX_ACCELERATION
        )
    );


    FlywheelSim rightSim = new FlywheelSim(system, DCMotor.getNeoVortex(1), 6.7);
    FlywheelSim leftSim = new FlywheelSim(null, DCMotor.getNeoVortex(1), 6.7);

    //what is a linear system + why do we need it


    private SimpleMotorFeedforward leftFF = new SimpleMotorFeedforward(0.0, 0.0, 0.0);
    private SimpleMotorFeedforward rightFF = new SimpleMotorFeedforward(0.0, 0.0, 0.0);

 
    @Override
    public void setFeedForwardGains(double kS, double kV, double kA) {
        leftFF = new SimpleMotorFeedforward(kS, kV, kA);
        rightFF = new SimpleMotorFeedforward(kS, kV, kA);
    }

    @Override
    public void setVoltage(double voltage) {
        rightSetpointRPM = null;
        rightAppliedVolts = MathUtil.clamp(voltage, -12.0, 12.0);
        rightSim.setInputVoltage(rightAppliedVolts);

        leftSetpointRPM = null;
        leftAppliedVolts = MathUtil.clamp(voltage, -12.0, 12.0);
        leftSim.setInputVoltage(rightAppliedVolts);
    }

    @Override
    public void setPIDGains(double kP, double kI, double kD) {
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
    }

    @Override
    public void setRPM(double rpm){
        leftSetpointRPM = rpm;
        rightSetpointRPM = rpm;
        //is it really this simple??
    }



}
