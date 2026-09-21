package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="Cycle Time Monitor")
public class Main extends LinearOpMode {
   private final ElapsedTime timer = new ElapsedTime();

   @Override
    public void runOpMode(){
       telemetry.addData("Status", "Initialized");
       telemetry.update();
       FrameData framedata = new FrameData(telemetry);
       waitForStart();

       timer.reset();


       while (opModeIsActive()) {
           framedata.update(timer.seconds());
           telemetry.update();
       }
   }
}
