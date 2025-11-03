// Copyright 2021-2024 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot.subsystems.Shooter;

import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Second;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.MutAngle;
import edu.wpi.first.units.measure.MutAngularVelocity;
import edu.wpi.first.units.measure.MutVoltage;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import java.util.function.DoubleSupplier;
import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

public class Shooter extends subsystemBase {
    private final ShooterIOInputsAutoLogged inputs = new AlgaePivotIOInputsAutoLogged();
    private final ShooterIO io;

    private LoggedNetworkNumber logP;
    private LoggedNetworkNumber logI;
    private LoggedNetworkNumber logD;

    private LoggedNetworkNumber logkS;
    private LoggedNetworkNumber logkG;
    private LoggedNetworkNumber logkV;
    private LoggedNetworkNumber logkA;

    private LoggedNetworkNumber logActiveP;
    private LoggedNetworkNumber logActiveI;
    private LoggedNetworkNumber logActiveD;

    private LoggedNetworkNumber logActivekS;
    private LoggedNetworkNumber logActivekG;
    private LoggedNetworkNumber logActivekV;
    private LoggedNetworkNumber logActivekA;

    private final MutVoltage appliedVoltage = Volts.mutable(0);
    private final MutAngularVelocity velocity = RadiansPerSecond.mutable(0);
    private final SysIdRoutine sysIdRoutine;

    public static enum State {
        MANUAL,
        PID,
        SYSID
    }
    private State shooterState = State.MANUAL;
    private double manualSpeed = 0;
    private double setpoint = 0;
    private double setpointRPM = 0.0;
    private double manualPower = 0.0;

    public Shooter(ShooterIO io) {
        this.io = io;

        SmartDashboard.putData(getName(), this);

        logP = new LoggedNetworkNumber("/SmartDashboard/Shooter/P", io.getP());
        logI = new LoggedNetworkNumber("/SmartDashboard/Shooter/I", io.getI());
        logD = new LoggedNetworkNumber("/SmartDashboard/Shooter/D", io.getD());

        logkS = new LoggedNetworkNumber("/SmartDashboard/Shooter/kS", io.getkS());
        logkG = new LoggedNetworkNumber("/SmartDashboard/Shooter/kG", io.getkG());
        logkV = new LoggedNetworkNumber("/SmartDashboard/Shooter/kV", io.getkV());
        logkA = new LoggedNetworkNumber("/SmartDashboard/Shooter/kA", io.getkA());

        logActiveP = new LoggedNetworkNumber("/SmartDashboard/Shooter/Active P", io.getP());
        logActiveI = new LoggedNetworkNumber("/SmartDashboard/Shooter/Active I", io.getI());
        logActiveD = new LoggedNetworkNumber("/SmartDashboard/Shooter/Active D", io.getD());

        logActivekS = new LoggedNetworkNumber("/SmartDashboard/Shooter/Active kS", io.getkS());
        logActivekG = new LoggedNetworkNumber("/SmartDashboard/Shooter/Active kG", io.getkG());
        logActivekV = new LoggedNetworkNumber("/SmartDashboard/Shooter/Active kV", io.getkV());
        logActivekA = new LoggedNetworkNumber("/SmartDashboard/Shooter/Active kA", io.getkA());

        SysId =
        new SysIdRoutine(
            new SysIdRoutine.Config(
                Volts.per(Second).of(ShooterConstants.SYSID_RAMP_RATE),
                Volts.of(ShooterConstants.SYSID_STEP_VOLTAGE),
                Seconds.of(ShooterConstants.SYSID_TIME),
                (state) -> Logger.recordOutput("/Shooter/SysIdTestState", state.toString())),
            new SysIdRoutine.Mechanism(
                v -> io.setVoltage(v.in(Volts)),
                (sysidLog) -> {
                sysidLog
                    .motor("shooter")
                    .voltage(m_appliedVoltage.mut_replace(inputs.appliedVolts, Volts))
                    .angularVelocity(m_velocity.mut_replace(inputs.angVelocityRadsPerSec, RotationsPerSecond));
                },
                this));
    }
    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs(getName(), inputs);

        flywheelVis.setLength(0.2 + (inputs.velocityRPM / 6000.0) * 0.3);

        switch (shooterState) {
            case MANUAL: 
                move(manualSpeed);
                break;
            case PID: 
                runPID();
                break;
            default:
                break;
    }

    if (logP.get() != io.getP()) io.setP(logP.get());
    if (logI.get() != io.getI()) io.setI(logI.get());
    if (logD.get() != io.getD()) io.setD(logD.get());
    if (logkS.get() != io.getkS()) io.setkS(logkS.get());
    if (logkV.get() != io.getkV()) io.setkV(logkV.get());
    if (logkA.get() != io.getkA()) io.setkA(logkA.get());
    if (logActiveP.get() != io.getActiveP()) io.setActiveP(logActiveP.get());
    if (logActiveI.get() != io.getActiveI()) io.setActiveI(logActiveI.get());
    if (logActiveD.get() != io.getActiveD()) io.setActiveD(logActiveD.get());
    if (logActivekS.get() != io.getActivekS()) io.setActivekS(logActivekS.get());
    if (logActivekG.get() != io.getActivekG()) io.setActivekG(logActivekG.get());
    if (logActivekV.get() != io.getActivekV()) io.setActivekV(logActivekV.get());
    if (logActivekA.get() != io.getActivekA()) io.setActivekA(logActivekA.get());  
    }

    private void runPID() {
    if (setpoint > ShooterConstants.SHOOTER_MAX_ANGLE) {
        setpoint = ShooterConstants.SHOOTER_MAX_ANGLE;
    } else if (setpoint < ShooterConstants.SHOOTER_MIN_ANGLE) {
        setpoint = ShooterConstants.SHOOTER_MIN_ANGLE;
    }
    if ((io.getAngle() <= ShooterConstants.SHOOTER_MIN_ANGLE && io.getAngVelocity() < 0)
        || (io.getAngle() >= ShooterConstants.SHOOTER_MAX_ANGLE
            && io.getAngVelocity() > 0)) {
    io.setVoltage(0);
    } else {
    io.goToSetpoint();
    }
    }


    public void setManual(double power) {
    manualSpeed = speed;
    if (speed != 0) {
        shooterState = State.MANUAL;
        }
    }

    public void setPID(double rpm) {
        this.setpoint = setpoint;
        shooterState = State.PID;
        io.setSetpoint(setpoint);
        Logger.recordOutput("AlgaePivot/Setpoint", setpoint);
    }

    public void setMechanism(MechanismLigament2d mechanism) {
        shooterMechanism = mechanism;
    }
    
    public MechanismLigament2d append(MechanismLigament2d mechanism) {
        return shooterMechanism.append(mechanism);
    }

    public Command quasistaticForward() {
        shooterState = State.SYSID;
        return SYSID.quasistatic(Direction.kForward)
            .until(() -> io.getAngle() >= ShooterConstants.SHOOTER_MAX_ANGLE);
    }

    public Command quasistaticReverse() {
        shooterState = State.SYSID;
        return sysIdRoutine.quasistatic(Direction.kReverse)
            .until(() -> io.getAngle() <= ShooterConstants.SHOOTER_MIN_ANGLE);
    }

    public Command dynamicForward() {
    shooterState = State.SYSID;
    return SysId.dynamic(Direction.kForward)
        .until(() -> io.getAngle() >= AlgaePivotConstants.ALGAE_PIVOT_MAX_ANGLE);
    }

    public Command dynamicReverse() {
    shooterState = State.SYSID;
    return SysId.dynamic(Direction.kReverse)
        .until(() -> io.getAngle() <= AlgaePivotConstants.ALGAE_PIVOT_MIN_ANGLE);
    }

    public Command PIDCommand(double rpm) {
    return new InstantCommand(() -> setPID(rpm), this)
        .andThen(new WaitUntilCommand(() -> atSetpoint()));
    }

    public Command ManualCommand(DoubleSupplier speedSupplier) {
    return new RunCommand(() -> setManual(speedSupplier.getAsDouble()), this)
        .finallyDo(
            () -> {
            manualSpeed = 0;
            move(0);
            });
    }

    public Command runVoltage(DoubleSupplier voltageSupplier) {
        return new RunCommand(
            () -> io.setVoltage(voltageSupplier.getAsDouble()), 
            this
        ).finallyDo(
            () -> io.setVoltage(0)
    );
    }
}