package src.main.java.frc.robot.subsystems.shooter;

public class shooter extends SubsystemBase {
    public Shooter(ShooterIO io){
        this.io = io;
    }

    //will finish commands here later
    public Command runVoltage(DoubleSupplier voltage){}
    public Command runRPM(DoubleSupplier rpm){}
}