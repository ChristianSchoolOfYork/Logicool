package org.firstinspires.ftc.teamcode.LogiControl;

public class Pose2d {
    public double x;
    public double y;
    public double angle;

    public Pose2d(){
        this.x = 0;
        this.y = 0;
        this.angle = 0;
    }
    public Pose2d(double x, double y, double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    public Pose2d(Pose2d other){
        this.x = other.x;
        this.y = other.y;
        this.angle = other.angle;
    }
    public Pose2d add(Pose2d other){
        return new Pose2d(this.x + other.x, this.y + other.y, this.angle + other.angle);
    }
    public Pose2d subtract(Pose2d other){
        return new Pose2d(this.x - other.x, this.y - other.y, this.angle - other.angle);
    }
    public double getDistance(Pose2d other){
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    public double getAngle(Pose2d other){
        return Math.atan2(other.y - this.y, other.x - this.x);
    }
    public double getAngle(){
        return this.angle;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }


}
