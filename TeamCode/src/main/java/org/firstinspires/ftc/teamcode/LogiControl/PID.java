package org.firstinspires.ftc.teamcode.LogiControl;

/**
 * A PID controller class, that can be used for literally anything that needs PID.
 * <br>Those using it need to watch out on their own for Initialization spike, and making sure that the dt is never 0.
 */
@SuppressWarnings("unused")
public class PID {
    private final double kp;
    private final double ki;
    private final double kd;
    private double prevError = 0;
    private double derivative = 0;
    private double integral = 0;
    final boolean usePV;

    /**
     * Derivative calculation mode for PID controller.
     * Switches whether it calculates based off of the error or the process variable.
     */
    public enum Mode {
        USE_PV,USE_ERROR
    }

    /**
     * Constructor for PID controller.
     * @param kp Proportional gain
     * @param ki Integral gain
     * @param kd Derivative gain
     * @param mode Derivative calculation mode
     */
    public PID(double kp, double ki, double kd, Mode mode){
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.usePV = mode == Mode.USE_PV;
    };

    private double calculate(double error, double dt, double pv){
        derivative = (pv - prevError)/dt;
        integral += error*dt;
        prevError = pv;
        return kp*error + ki*integral - kd*derivative;
    }

    private double calculate(double error, double dt) {
        /*
        Quick explanation of each part of PID
        Proportion: Uses the actual error in calculation.
        Integral: Uses the sum of all of the errors adjusted by time.
        Derivative: Uses the rate of change of the error.
        */
        derivative = (error - prevError)/dt;
        integral += error*dt;
        prevError = error;
        return kp*error + ki*integral + kd*derivative;
    }

    public void reset(){
        integral = 0;
        prevError = 0;
    }

    /**
     * Get PID output based off of mode.
     * @param dt Time since last call
     * @param pv Process variable
     * @param sp Setpoint
     * @return PID output
     */
    public double getPID(double dt, double pv, double sp){
        double error = sp - pv;
        return usePV ? calculate(error, dt, pv) : calculate(error, dt);
    }
}
