package org.firstinspires.ftc.teamcode.LogiControl;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

public class test {
    DcMotor FL = hardwareMap.get(DcMotor.class, "FL");
    DcMotor FR = hardwareMap.get(DcMotor.class, "FR");
    DcMotor BL = hardwareMap.get(DcMotor.class, "BL");
    DcMotor BR = hardwareMap.get(DcMotor.class, "BR");
    PID pid = new PID(1,0,0,)
}
