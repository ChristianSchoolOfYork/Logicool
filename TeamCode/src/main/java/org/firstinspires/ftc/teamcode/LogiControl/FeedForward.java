package org.firstinspires.ftc.teamcode.LogiControl;

public class FeedForward {
    private final double ks;
    private final double kv;
    private final double ka;
    public FeedForward(double ks, double kv, double ka){
        this.ks = ks;
        this.kv = kv;
        this.ka = ka;
    }

    public double calculate(double targetVel, double targetAccel){
        return ks * Math.signum(targetVel) + kv * targetVel + ka * targetAccel;
    }
}
