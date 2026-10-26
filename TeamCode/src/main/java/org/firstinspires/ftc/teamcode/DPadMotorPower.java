package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Dpad Motor Power")
public class DPadMotorPower extends LinearOpMode {

    // Common REV values: 28.0 (Ungeared), 560.0 (20:1), 1120.0 (40:1)
    private static final double TICKS_PER_REV = 560.0;

    @Override
    public void runOpMode() {
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "motor");

        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        double magnitude = 0.0;
        double changeRate = 0.5;

        ElapsedTime timer = new ElapsedTime();

        waitForStart();
        timer.reset();

        while (opModeIsActive()) {
            double deltaTime = timer.seconds();
            timer.reset();

            // Stop motor power on Left or Right D-pad press
            if (gamepad1.dpad_left || gamepad1.dpad_right) {
                magnitude = 0.0;
            } else if (gamepad1.dpad_up) {
                magnitude += changeRate * deltaTime;
            } else if (gamepad1.dpad_down) {
                magnitude -= changeRate * deltaTime;
            }

            magnitude = Range.clip(magnitude, -1.0, 1.0);

            motor.setPower(magnitude);

            int currentTicks = motor.getCurrentPosition();

            // Total revolutions completed
            double revolutions = currentTicks / TICKS_PER_REV;

            // Modulo wrapping for 0° - 360° range
            double degrees = (revolutions * 360.0) % 360.0;
            if (degrees < 0) {
                degrees += 360.0;
            }

            telemetry.addData("Encoder Ticks", currentTicks);
            telemetry.addData("Position (Revolutions)", "%.2f revs", revolutions);
            telemetry.addData("Position (Degrees 0-360)", "%.1f°", degrees);
            telemetry.addData("Power", "%.2f", magnitude);
            telemetry.addData("Velocity (tps)", motor.getVelocity());
            telemetry.update();
        }
    }
}