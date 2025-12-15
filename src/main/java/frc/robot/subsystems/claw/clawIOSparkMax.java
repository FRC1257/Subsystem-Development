public class ClawIOSparkMax implements ClawIO {
    private final CanSparkFlex motor;
    private SparkAbsoluteEncoder motorEncoder;
    public ClawIOSparkMax() {
        motor = new CANSparkFlex(ClawConstants.deviceId, MotorType.kBrushless);
        motorEncoder = motor.getAbsoluteEncoder();
        @Override
        public void updateInputs(ClawIOInputs inputs) {
            inputs.CurrentAmps = motor.getOutputCurrent();
            inputs.appliedVoltage = motor
        }
        
        @Override
        public void open(double volts)
            motor.set(ClawConstants.Claw_Open_Speed);
        //Set voltage for opening
        
        @Override
        public void close(double volts)
            motor.set(ClawConstants.Claw_Close_Speed);
        //Set voltage for closing

        @Override
        public void stop() {
            motor.set(0);
        }
        //Stops the motor

    }
}