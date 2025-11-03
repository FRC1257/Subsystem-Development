package frc.robot.subsystems.Shooter;

import org.littletonrobotics.junction.AutoLog;


public interface ShooterIO {
    @AutoLog
    public static class ShooterIOInputs {
        public double voltage = 0.0;
    }


    default void setVoltage(double voltage) {}
    default double getVoltage() {
        return 0.0;
    }
    default void updateInputs(ShooterIOInputs inputs) {}
    default void setPIDGains(double kP, double kI, double kD) {}
    default void setRPM(double rpm){}
    default void setFeedForwardGains(double kS, double kV, double kA) {}
    default void stop() {}

    public default double getP() {
        return 0.0;
      }

      public default double getI() {
        return 0.0;
      }

      public default double getD() {
        return 0.0;
      }

      public default double getkS() {
        return 0.0;
      }
    

      public default double getkG() {
        return 0.0;
      }
 
      public default double getkV() {
        return 0.0;
      }

      public default double getkA() {
        return 0.0;
      }
    
      public default void setP(double kP) {}

      public default void setI(double kI) {}

      public default void setD(double kD) {}

      public default void setkS(double kS) {}

      public default void setkG(double kG) {}

      public default void setkV(double kV) {}

      public default void setkA(double kA) {}





}
