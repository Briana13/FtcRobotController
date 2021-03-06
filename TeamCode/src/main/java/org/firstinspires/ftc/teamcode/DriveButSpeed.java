package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/**
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */
@TeleOp(group = "drive")
public class DriveButSpeed extends LinearOpMode {



        private DcMotor intake;
        private DcMotorEx flyWheel = null;
        private Servo Krystal;
        private Servo Achlys;

        private DcMotor Coco;

    public void runOpMode() throws InterruptedException {
        intake = hardwareMap.get(DcMotor.class, "intake");
        flyWheel = hardwareMap.get(DcMotorEx.class,"flyWheel");

        Krystal = hardwareMap.get(Servo.class, "Krystal");
        Achlys = hardwareMap.get(Servo.class,"Achlys");

        Coco = hardwareMap.get(DcMotor.class, "Coco");


        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Coco.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        while (!isStopRequested()) {
            flyWheel.setVelocity(1560);
            if (this.gamepad1.dpad_right){
                intake.setPower(.8);
            }
            else if (!this.gamepad1.dpad_right){
                intake.setPower(-.8);
            }
            if (this.gamepad1.dpad_down){
                Coco.setPower(0.8);
            }
            else if (this.gamepad1.dpad_up){
                Coco.setPower(-0.8);
            }
            else {
               Coco.setPower(0);
            }
            if (this.gamepad1.b){
                Krystal.setPosition(.5);
            }
            if (this.gamepad1.a){
                Krystal.setPosition(0);
            }
            if (this.gamepad1.left_bumper){
                Achlys.setPosition(.4);
            }
            if (this.gamepad1.right_bumper){
                Achlys.setPosition(1);
            }

            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
        }
    }


}
