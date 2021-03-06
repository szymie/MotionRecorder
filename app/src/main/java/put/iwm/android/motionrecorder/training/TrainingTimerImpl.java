package put.iwm.android.motionrecorder.training;

import android.os.Handler;

import org.apache.commons.lang3.time.StopWatch;

import put.iwm.android.motionrecorder.contracts.TimerObserver;

/**
 * Created by Szymon on 2015-05-03.
 */
public class TrainingTimerImpl implements TrainingTimer {

    private static final String TAG = TrainingTimerImpl.class.toString();
    private long startTime;
    private long stopTime;
    private long durationTime;
    private TimerObserver timerObserver;
    private StopWatch stopWatch;
    private Handler handler;
    private boolean nextUpdate;

    public TrainingTimerImpl() {
        stopWatch = new StopWatch();
        handler = new Handler();
    }

    public TrainingTimerImpl(TimerObserver timerObserver) {
        this();
        this.timerObserver = timerObserver;
    }

    private Runnable updateTimerThread = new Runnable() {

        @Override
        public void run() {

            durationTime = stopWatch.getTime();
            timerObserver.processTimerUpdate(durationTime);

            if(nextUpdate)
                handler.postDelayed(this, 200);
        }
    };

    @Override
    public void start() {

        stopWatch.reset();
        stopWatch.start();
        startTime = System.currentTimeMillis();
        startUpdateTimerThread();
    }

    @Override
    public void resume() {

        stopWatch.resume();
        startUpdateTimerThread();
    }

    private void startUpdateTimerThread() {

        nextUpdate = true;
        handler.postDelayed(updateTimerThread, 0);
    }

    @Override
    public void pause() {

        stopWatch.suspend();
        stopUpdateTimerThread();
    }

    @Override
    public void stop() {

        stopWatch.stop();
        stopTime = System.currentTimeMillis();
        stopUpdateTimerThread();
    }

    private void stopUpdateTimerThread() {
        nextUpdate = false;
    }

    @Override
    public long getDurationTime() {
        return durationTime;
    }

    public void setTimerObserver(TimerObserver timerObserver) {
        this.timerObserver = timerObserver;
    }
}
