package.frc.robot.subsystems.shooter;


//overide RPM to set its referneces(idk how to do that)
// override setVolatege and set voltage for the Motors
//lwk i have no idea what to do for FF gains(figured it out i think)
// also i have no idea what to import(ty copilot)
// or what variable to declare to start coding the overides(firgured it out)
//HELP SAM PLEASE 😭

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import com.revrobotics.CANSparkMax;



public class ShooterIOSparkMax {
    public class ShooterIOSparkMax implements ShooterIO {
        // should all of thtese be final?
            private SimpleMotorFeedforward leftFeedforward;
            private SimpleMotorFeedforward rightFeedforward;
            
            private final CANSparkMax leftmotor;
            private final CANSparkMax rightmotor;

            private PIDController leftcontroller;
            private PIDController rightcontroller;

            private RelativeEncoder leftencoder;
            private final getEncoder(return leftmotor.getEncoder());
            private RelativeEncoder rightencoder;
            private final getEncoder(return rightmotor.getEncoder());
        
        public ShooterIOSparkMax { 
            CANSPARKMAX.leftmotor = new CANSparkMax(ShooterConstants.LeftMotorID, MotorType.kBrushless);
            CANSPARKMAX.rightmotor = new CANSparkMax(ShooterConstants.RightMotorID, MotorType.kBrushless);
            
            RelativeEncoder.leftencoder = leftmotor.getEncoder();
            RelativeEncoder.rightencoder = rightmotor.getEncoder(); 
            
            PIDController.leftcontroller = CANSPARKMAX.leftmotor();
            PIDController.rightcontroller = CANSPARKMAX.rightmotor();
            
            SimpleMotorFeedforward.leftFeedforward = new SimpleMotorFeedforward(0, 0, 0);
            SimpleMotorFeedforward.rightFeedforward = new SimpleMotorFeedforward(0, 0, 0);
            @Override
            public void setVoltage(double voltage) {
                leftmotor.setVoltage(voltage);
                rightmotor.setVoltage(voltage);
            }
           
            @Override
            public void setRPM(double velocityRadPerSec) {
                drivePIDController.setReference(Feedforward1, CANSparkMax.ControlType.kVelocity)
                drivePIDController.setReference(Feedforward2, CANSparkMax.ControlType.kVelocity);
              }
              //took this code from "drive" i have no idea how it works or if its right
          
            @Override setPIDGains(double Kp, double Ki, double Kd) {
                leftcontroller.setP(Kp);
                leftcontroller.setI(Ki);
                leftcontroller.setD(Kd);
                rightcontroller.setP(Kp);
                rightcontroller.setI(Ki);
                rightcontroller.setD(Kd);
    
            }
            
            @Override setFeedforwardGains(double Ks, double Kv, double Ka) {
                leftFeedforward.setFeedforwardGains(Ks, Kv, Ka);
                rightFeedforward.setFeedforwardGains(Ks, Kv, Ka);
    
                }

                public void stopMotors() {
                    leftmotor.set(0);
                    rightmotor.set(0);
                }
            }
        
}

}
