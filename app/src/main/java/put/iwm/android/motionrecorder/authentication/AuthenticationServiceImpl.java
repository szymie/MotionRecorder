package put.iwm.android.motionrecorder.authentication;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import put.iwm.android.motionrecorder.asynctasks.LoginRequestAsyncTask;
import put.iwm.android.motionrecorder.exceptions.InvalidLoginRequestException;

/**
 * Created by Szymon on 2015-04-04.
 */
public class AuthenticationServiceImpl implements AuthenticationService, LoginResponseReceiver {

    private LoginRequestValidator loginRequestValidator;
    private LoginResponseReceiver loginResponseReceiver;
    private LoginRequestAsyncTask loginRequestAsyncTask;
    private Context context;

    public AuthenticationServiceImpl(Context context, LoginRequestValidator loginRequestValidator) {
        this.loginRequestValidator = loginRequestValidator;
        this.context = context;
    }

    public AuthenticationServiceImpl(Context context, LoginRequestValidator loginRequestValidator, LoginResponseReceiver loginResponseReceiver) {
        this.loginRequestValidator = loginRequestValidator;
        this.loginResponseReceiver = loginResponseReceiver;
        this.context = context;
    }

    @Override
    public void processLoginRequest(LoginRequest loginRequest) throws InvalidLoginRequestException {

        validateLoginRequest(loginRequest);

        executeSendLoginRequest(loginRequest);
    }

    public void validateLoginRequest(LoginRequest loginRequest) throws InvalidLoginRequestException {

        loginRequestValidator.validate(loginRequest);
    }

    public void executeSendLoginRequest(LoginRequest loginRequest) {

        loginRequestAsyncTask = new LoginRequestAsyncTask(context, this);
        loginRequestAsyncTask.execute(loginRequest);
    }

    @Override
    public void processLoginResponse(LoginResponse response) {
        loginResponseReceiver.processLoginResponse(response);
    }

    @Override
    public void setLoginResponseReceiver(LoginResponseReceiver loginResponseReceiver) {
        this.loginResponseReceiver = loginResponseReceiver;
    }
}
