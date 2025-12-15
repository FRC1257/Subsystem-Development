package frc.robot.subsystems.claw;
//Importing the package
import static frc.robot.subsystems.claw.ClawConstants;
//Importing the ClawConstants
import edu.wpi.first.math.system.DCMotor;
//Motor connected to the simulation
import edu.wpi.first.math.system.plant.LinearSystemId;
//To run simulation tests
import edu.wpi.first.wpilib.simulation.NeoVortex;
//Neo Vortex Simulation Import


private double appliedVoltage = 0.0;
//Set applied voltage to zero as placeholder
public class ClawIOSSim implements ClawIO{

    private final NeoVortexSim sim = 
        new NeoVortexSim(
        LinearSystemId.createDCMotorSystem()
            DCMotor.getVortexNeo(1), 
            kMomentofInertia, 
            kClawGearing);
    }       

//Class implements IO
//New instance of NewVortexSim object created
//Through the LinearSystemID simulation, the DCMotorSystem() is created as a physics simulaton
//One VortexNeo motor referenced
//kMomentofInertia, used to show rotational inertia of simulated motor
//kClawGearing, for gear between physical model

@Override
    public ClawIOSim() {}
    //No paramaters, creates instance of ClawIOSim

    public void updateInputs(ClawIOInputs inputs)
    //inputs are updated with the parameter
        sim.update(0.02)
    //The simulation is updated every 0.02 seconds
        inputs.velocityRadsPerSec = sim.AngularVelocityRadPerSec();
    //The inputs.velocityRads
        inputs.appliedVoltage = appliedVoltage;
    //Applied voltage becomes the input
        inputs.CurrentAmps = new double[] {sim.getCurrentDrawAmps()};
    //New double of , logs and relays the electric current
        inputs.tempCelsius = new double[] {50};
    //Placeholder temperature

    @Override
    public void setVoltage (double volts){
        appliedVoltage = volts;
        sim.setInputVoltage(volts);
    }
    //Setvoltage

    @Override
    public double getVelocity(){
        return sim.getAngularVelocityRadPerSec();
    }
    //Got angular velocity
    
    @Override
    public void open(double volts)
        setVoltage(-volts);
    //Set voltage for opening
    
    @Override
    public void close(double volts)
        setVoltage(volts);
    //Set voltage for closing