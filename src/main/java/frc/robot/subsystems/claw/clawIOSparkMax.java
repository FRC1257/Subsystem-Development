package frc.robot.subsystems.claw;
//Idk how to do the auto importation and idk what to import

public class ClawIOSparkMax implements ClawIO {
    private final CANSparkFlex motor;
    private RelativeEncoder motorEncoder;
    public ClawIOSparkMax() {
        //create motor object with ID and motor type
        motor = new CANSparkFlex(ClawConstants.Claw_Motor_ID, MotorType.kBrushless);
        //get encoder from the motor
        motorEncoder = motor.getEncoder();
    }
    @Override
    public void updateInputs(ClawIOInputs inputs) {
        //read current amps
        inputs.currentAmps = motor.getOutputCurrent();
        // getAppliedOutput() returns duty cycle (-1.0 to 1.0)
        // Multiply by bus voltage to get actual volts
        inputs.appliedVoltage = motor.getAppliedOutput() * motor.getBusVoltage();
        //read motor temperature
        inputs.tempCelsius = motor.getMotorTemperature();
    }

    //Set voltage for opening
    @Override
    public void open(double volts) {
        motor.setVoltage(volts);
    }    
    //set voltage for closing
    @Override
    public void close(double volts) {
        motor.setVoltage(volts);
    }
    //set voltage to zero
    @Override
    public void stop() {
        motor.setVoltage(0);
    }
}