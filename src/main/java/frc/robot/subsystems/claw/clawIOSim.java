package frc.robot.subsystems.claw;
//Importing the package
import frc.robot.subsystems.claw.ClawConstants;
//Importing the ClawConstants
import edu.wpi.first.math.system.DCMotor;
//Motor connected to the simulation
import edu.wpi.first.math.system.plant.LinearSystemId;
//To run simulation tests
import edu.wpi.first.wpilib.simulation.NeoVortex;
//Neo Vortex Simulation Import


public class ClawIOSim implements ClawIO{
    private double appliedVoltage = 0.0;

    public ClawIOSim() {
        //No paramaters, creates instance of ClawIOSim
        private final NeoVortexSim sim = 
            new NeoVortexSim(
            LinearSystemId.createDCMotorSystem()
                DCMotor.getVortexNeo(1), 
                ClawConstants.MomentofInertia, 
                ClawConstants.ClawGearing);
    }

//Class implements IO
//New instance of NewVortexSim object created
//Through the LinearSystemID simulation, the DCMotorSystem() is created as a physics simulaton
//One VortexNeo motor referenced
//MomentofInertia, used to show rotational inertia of simulated motor
//ClawGearing, for gear between physical model

    @Override
    public void updateInputs(ClawIOInputs inputs) {
    //inputs are updated with the parameter
        sim.update(0.02);
    //The simulation is updated every 0.02 seconds
        inputs.appliedVoltage = appliedVoltage;
    //Applied voltage becomes the input
        inputs.currentAmps = new double {sim.getCurrentDrawAmps()};
    //New double of , logs and relays the electric current
        inputs.tempCelsius = new double {50};
    //Placeholder temperature
    }

    @Override
    public void open(double volts) {
        //storing voltage in a variable
        appliedVoltage = volts;
        //applying voltage to the sim
        sim.setInputVoltage(volts);
    }
    //Set voltage for opening

    @Override
    public void close(double volts) {
        appliedVoltage = volts;
        sim.setInputVoltage(volts);
    }
    //Set voltage for closing

    @Override
    public void stop() {
        appliedVoltage = 0.0
        sim.setInputVoltage(0.0);
    }
    //sets voltage to zero
}