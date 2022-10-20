package org.firstinspires.ftc.teamcode.mina.camera;

import android.annotation.SuppressLint;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.teamcode.mina.RWConfig;
import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.StartEvent;
import org.firstinspires.ftc.teamcode.mina.utils.Telemetrie;
import org.opencv.core.Rect;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;

public class RWOpenCV {

    public enum RWPipeType{
        NULL,
        CON,
        TAG;
    }


    //APRILTAG
    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    private static double fx = 578.272;
    private static double fy = 578.272;
    private static double cx = 402.145;
    private static double cy = 221.506;

    // UNITS ARE METERS
    private static double tagsize = 0.166;

    private static int numFramesWithoutDetection = 0;

    private static final float DECIMATION_HIGH = 3;
    private static final float DECIMATION_LOW = 2;
    private static final float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    private static final int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;
    //APRILTAG

    public static OpenCvCamera camera;

    public static RWPipeType pipeType = RWPipeType.NULL;

    private static ConDetectorPipeline conDetectorPipeline;
    private static AprilTagDetectorPipeline aprilTagDetectorPipeline;

    public static void init(){
        if(RWRobot.startType == StartEvent.StartType.CONTROL && !RWConfig.OPENCV_IN_CONTROL)
            return;
        pipeType = RWPipeType.NULL;

        conDetectorPipeline = new ConDetectorPipeline();
        aprilTagDetectorPipeline = new AprilTagDetectorPipeline(tagsize, fx, fy, cx, cy);

        if(RWConfig.DEBUG) {
            int cameraMonitorViewId = RWRobot.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", RWRobot.opMode.hardwareMap.appContext.getPackageName());
            camera = OpenCvCameraFactory.getInstance().createWebcam(RWConfig.webcamName, cameraMonitorViewId);
        }else {
            camera = OpenCvCameraFactory.getInstance().createWebcam(RWConfig.webcamName);
        }

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800, 448);
            }
            @Override
            public void onError(int errorCode)
            {
                Telemetrie.addTel(Telemetrie.TelType.ERR, "Camera OPENCV", "Nu s-a putut deschide");
            }
        });
    }

    public static void setPipeType(RWPipeType _pipeType){
        if(pipeType != _pipeType){
            pipeType = _pipeType;
            if(pipeType == RWPipeType.NULL){
                camera.setPipeline(null);
            }
            if(pipeType == RWPipeType.CON){
                camera.setPipeline(conDetectorPipeline);
            }
            if(pipeType == RWPipeType.TAG){
                camera.setPipeline(aprilTagDetectorPipeline);
            }
        }
    }

    public static void update(){
        if(RWRobot.startType == StartEvent.StartType.CONTROL && !RWConfig.OPENCV_IN_CONTROL)
            return;
        if(pipeType == RWPipeType.CON){
            Rect[] detections = conDetectorPipeline.getDetectionsUpdate();
            if(detections.length < 1)
                return;
            Rect det = detections[0];
            for (int i = 1; i < detections.length; i++) {
                if(detections[i].area() > det.area()){
                    det = detections[i];
                }
            }
            //TODO De pasat obiectul cel mai apropiat in propagare
        }else if(pipeType == RWPipeType.TAG){
            ArrayList<AprilTagDetection> detections = aprilTagDetectorPipeline.getDetectionsUpdate();
            if(detections != null)
            {
                Telemetrie.addTel("FPS", ""+camera.getFps());
                Telemetrie.addTel("Overhead ms", ""+camera.getOverheadTimeMs());
                Telemetrie.addTel("Pipeline ms", ""+camera.getPipelineTimeMs());

                if(detections.size() == 0)
                {
                    numFramesWithoutDetection++;
                    if(numFramesWithoutDetection >= THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION)
                    {
                        aprilTagDetectorPipeline.setDecimation(DECIMATION_LOW);
                    }
                }
                else
                {
                    if(detections.get(0).pose.z < THRESHOLD_HIGH_DECIMATION_RANGE_METERS)
                    {
                        aprilTagDetectorPipeline.setDecimation(DECIMATION_HIGH);
                    }

                    for(AprilTagDetection detection : detections)
                    {
                        Telemetrie.addTel("T-Tag:"+detection.id, String.format("X: %.2f m, Y: %.2f m, Z %.2f m", detection.pose.x, detection.pose.y, detection.pose.z));
                        Telemetrie.addTel("R-Tag:"+detection.id, String.format("Y: %.2f m, P: %.2f m, R %.2f m", Math.toDegrees(detection.pose.yaw), Math.toDegrees(detection.pose.pitch), Math.toDegrees(detection.pose.roll)));

                        //TODO De propagat eventul catre celelalte clase
                    }
                }
            }
        }
    }

}
