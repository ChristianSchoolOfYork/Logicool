package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.OptionalDouble;

public class Constants {
    public static Follower create(HardwareMap h) {
        // return new Follower(Drivetrain, Localizer, Foresight);
        return null;
    }
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("FL");
        c.frontRightName.set("FR");
        c.backLeftName.set("BL");
        c.backRightName.set("BR");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("odometry");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(1.9070739445723888);
        c.yPodOffset.set(-3.856299693190207);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.17372672979726836);
                Controller secondaryTranslationalForward = Controller.proportional(0.06418738459637637);
                Controller primaryTranslationalLateral = Controller.proportional(0.44371973896850075);
                Controller secondaryTranslationalLateral = Controller.proportional(0.16394258713907314);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.0165758581996342));
                c.brake.set(Controller.proportionalFeedforward(0.014089479469689069));

                c.headingFeedback.set(Controller.proportional(2.4587659420289083));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.040361590829590316, 0.007101701567909731));

                c.linearBrakeCoefficients.set(Matrix.diag(0.06460089573211987, 0.02693109890148349));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0015141211923833532, 0.0038261188168433142));

                c.maxAchievableForwardVelocity.set(63.61761813055451);
                c.maxAchievableStrafeVelocity.set(37.04740190128198);
                c.naturalForwardDeceleration.set(37.10209064275529);
                c.naturalStrafeDeceleration.set(40.42150497588483);
            }
    );
}