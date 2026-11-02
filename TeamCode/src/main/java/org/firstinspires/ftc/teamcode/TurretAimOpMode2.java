package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp(name = "AprilTag Reader", group = "Vision")
public class TurretAimOpMode2 extends LinearOpMode {

    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    @Override
    public void runOpMode() {
        // Initialize the AprilTag Processor using standard settings
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();

        // Build the VisionPortal. MJPEG formatting prevents CPU bottlenecking.
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(aprilTag)
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .build();

        // Let the driver know it is ready
        telemetry.addData("Status", "Camera Initialized. Press Play.");
        telemetry.update();

        waitForStart();

        // Main OpMode Loop
        while (opModeIsActive()) {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();


            telemetry.addData("# Tags Detected", currentDetections.size());

            // Iterate through all tags the camera currently sees
            for (AprilTagDetection detection : currentDetections) {

                // Ensure the tag is recognized by the FTC library before reading pose data
                if (detection.metadata != null && detection.ftcPose != null) {
                    telemetry.addLine(String.format("\n--- Tag ID %d (%s) ---", detection.id, detection.metadata.name));
                    telemetry.addData("Range (Distance)", "%.2f inches", detection.ftcPose.range);
                    telemetry.addData("Bearing (L/R Angle)", "%.2f degrees", detection.ftcPose.bearing);
                    telemetry.addData("Yaw (Tag Rotation)", "%.2f degrees", detection.ftcPose.yaw);
                }
                // Handle raw tags that aren't in the official field coordinate map
                else {
                    telemetry.addLine(String.format("\n--- Tag ID %d (Unknown/Unconfigured) ---", detection.id));
                }
            }

            // Push data to the Driver Station
            telemetry.update();

            // Brief sleep to allow background processes to run smoothly
            sleep(20);
        }

        // Properly close the camera when the user presses Stop
        visionPortal.close();
    }
}