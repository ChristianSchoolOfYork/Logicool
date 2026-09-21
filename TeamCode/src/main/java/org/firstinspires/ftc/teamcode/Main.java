package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Cycle Time Monitor")
public class Main extends LinearOpMode {
   private final ElapsedTime timer = new ElapsedTime();

   @Override
    public void runOpMode(){
       telemetry.addData("Status", "Initialized");
       telemetry.update();

       waitForStart();

       timer.reset();

       double lastFrame = 0;
       double totalFrames = 0;
       double lastTime = timer.milliseconds();
       double framesPerSecond = 0;
       while (opModeIsActive()) {
           totalFrames++;

           if (timer.milliseconds() - lastFrame >= 1000) {
               lastFrame = timer.milliseconds();
               framesPerSecond = totalFrames - lastFrame;
               lastFrame = totalFrames;
           }
           double currentTime = timer.milliseconds();
           lastTime = currentTime;


           double endTime = timer.milliseconds();

           double cycleTime = endTime - currentTime;

           double cyclesPerSecond = 1000.0 / cycleTime;

           telemetry.addData("Total Frames", "%.2f", totalFrames);
           telemetry.addData("Frames/sec", "%.2f Hz", framesPerSecond);
           telemetry.addData("Cycle Time", "%.2f ms", cycleTime);
           telemetry.addData("Cycles/sec", "%.2f Hz", cyclesPerSecond);
           telemetry.update();
       }
   }
}
