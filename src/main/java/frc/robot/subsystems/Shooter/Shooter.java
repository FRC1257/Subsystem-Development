package frc.robot.subsystems.shooter;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import java.util.Optional;

import org.littletonrobotics.junction.AutoLog;
import org.photonvision.EstimatedRobotPose;
import org.photonvision.targeting.PhotonPipelineResult;

import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.LoggedDashboardBoolean;

import static frc.robot.subsystems.Shooter.ShooterConstants.*;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {
    private final ShooterIO io;

    public Shooter(ShooterIO io) {
        this.io = io;
        /**im not gonna pretend like i coded this cuz i didnt 
        i used the suggestions wpilib gave to me same with alot of the code 
        but just so you know i underestand what it does
        i can try to summarize it 
        so basically i think the idea is that the runVoltage command is setting the shooters voltage to whatever the supplire gives 
        and if that is interrupted set the voltage to 0 and the same thing is happenning for the RPM
        mb i didnt code it sam i still dont rly know the Java syntax*/
        Command runVoltage(DoubleSupplier voltage) {
            return new RunCommand(() -> io.setVoltage(voltage.getAsDouble()), this);
            finnallyDo(interrupted -> setVoltage(0));
        }
        Command runRPM(DoubleSupplier rpm) {
            return new RunCommand(
                () -> io.setRPM(rpm.getAsDouble()), this);
            finallyDoIn(interrupted -> setRPM(0));
        }

}

}