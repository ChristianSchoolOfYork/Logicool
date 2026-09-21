package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FrameData {
    private final Telemetry telemetry;
    private double lastFrame = 0;
    private double totalFrames = 0;
    private double cyclesPerSecond = 0;
    private double elapsed = 0;

    public FrameData(Telemetry telemetry){
        this.telemetry = telemetry;
    }

    public void update(double deltaTime){
        totalFrames++;
        elapsed+=deltaTime;

        if (elapsed >= 1) {
            cyclesPerSecond = totalFrames - lastFrame;
            lastFrame = totalFrames;
            elapsed = 0;
        }

        telemetry.addData("Total Frames", "%.2f", totalFrames);
        telemetry.addData("Cycles/sec", "%.2f Hz", cyclesPerSecond);
    }
}
