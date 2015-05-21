package put.iwm.android.motionrecorder.training;

import android.location.Location;

import java.util.HashMap;
import java.util.Map;

import put.iwm.android.motionrecorder.contracts.LocationObserver;
import put.iwm.android.motionrecorder.contracts.RouteObserver;
import put.iwm.android.motionrecorder.contracts.TrainingObserver;
import put.iwm.android.motionrecorder.database.repository.TrainingRepository;
import put.iwm.android.motionrecorder.services.ServiceManager;

/**
 * Created by Szymon on 2015-04-24.
 */
public class TrainingManagerImpl implements TrainingManager, LocationObserver {

    private ServiceManager serviceManager;
    private TrainingTimer trainingTimer;
    private Training currentTraining;
    private TrainingObserver trainingObserver;
    private RouteObserver routeObserver;
    private TrainingRepository trainingRepository;

    public TrainingManagerImpl() {
        currentTraining = new Training();
    }

    public TrainingManagerImpl(ServiceManager serviceManager, TrainingTimer trainingTimer, TrainingRepository trainingRepository) {

        this();
        this.serviceManager = serviceManager;
        this.trainingTimer = trainingTimer;
        this.trainingRepository = trainingRepository;
    }

    @Override
    public void startTraining() {

        currentTraining = new Training();
        currentTraining.start();
        serviceManager.startLocationListenerService();
        trainingTimer.start();
    }

    @Override
    public void pauseTraining() {

        currentTraining.pause();
        serviceManager.stopLocationListenerService();
        trainingTimer.pause();
    }

    @Override
    public void resumeTraining() {

        currentTraining.resume();
        serviceManager.startLocationListenerService();
        trainingTimer.resume();
    }

    @Override
    public void finishTraining() {

        currentTraining.finish();
        serviceManager.stopLocationListenerService();
        trainingTimer.stop();
        trainingRepository.save(currentTraining);
    }

    @Override
    public void processLocationUpdate(Location location) {

        updateRoute(location);
        updateTrainingObserver();
        updateRouteObserver();
    }

    private void updateRoute(Location location) {

        RoutePoint routePoint = new RoutePoint();
        routePoint.setLatitude(location.getLatitude());
        routePoint.setLongitude(location.getLongitude());
        routePoint.setAltitude(location.getAltitude());
        routePoint.setMoveTime(location.getTime());
        currentTraining.appendRoutePointToRoute(routePoint);
    }

    private void updateTrainingObserver() {

        Map<String, String> responseModel = new HashMap<>();
        responseModel.put("distance", String.valueOf(currentTraining.getTotalDistance()));
        responseModel.put("speed", String.valueOf(currentTraining.getCurrentSpeed()));

        if(trainingObserver != null)
            trainingObserver.processTrainingUpdate(responseModel);
    }

    private void updateRouteObserver() {
        if(routeObserver != null)
            routeObserver.processRouteUpdate(currentTraining.getRoutePoints());
    }

    @Override
    public boolean isTrainingInProgress() {
        return currentTraining.isInProgress();
    }

    @Override
    public boolean isTrainingPaused() {
        return currentTraining.isPaused();
    }

    @Override
    public void setTrainingObserver(TrainingObserver trainingObserver) {
        this.trainingObserver = trainingObserver;
    }

    @Override
    public void setRouteObserver(RouteObserver routeObserver) {
        this.routeObserver = routeObserver;
    }

    @Override
    public void requestTrainingUpdate() {
        updateTrainingObserver();
    }

    @Override
    public void requestRouteUpdate() {
        updateRouteObserver();
    }
}
