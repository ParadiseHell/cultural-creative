package com.chengtao.culture.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;

import com.chengtao.culture.activity.UserInfoActivity;
import com.chengtao.culture.activityimpl.IMain;
import com.chengtao.culture.entity.CompanyUser;
import com.chengtao.culture.entity.PersonUser;
import com.chengtao.culture.request.CompanyUserRequest;
import com.chengtao.culture.request.LoginRequest;
import com.chengtao.culture.request.PersonUserRequest;
import com.chengtao.culture.response.IResponse;
import com.chengtao.culture.utils.SpUtils;
import com.chengtao.culture.utils.UserUtils;

/**
 * Created by ChengTao on 2017-01-01.
 */

@SuppressWarnings("FieldCanBeLocal")
public class MainPresenter extends IPresenter {
    private CompanyUser companyUser = null;
    private PersonUser personUser = null;
    private CompanyUserRequest companyUserRequest = null;
    private static final int COMPANY_USER_REQUEST = 1;
    private PersonUserRequest personUserRequest = null;
    private static final int PERSON_USER_REQUEST = 2;
    private IMain iMain;
    private static final String USER_ERROR_TIP ="获取用户信息失败";
    public MainPresenter(Context context, IMain iMain) {
        super(context);
        this.iMain = iMain;
    }

    @Override
    protected void onIRequestSuccess(int requestId, IResponse response) {
        switch (requestId){
            case PERSON_USER_REQUEST:
                if (response != null){
                    if (response.state()){
                        personUser = (PersonUser) response.getData();
                        iMain.initUserInfo(personUser);
                    }else {
                        iMain.tip(USER_ERROR_TIP);
                    }
                }else {
                    iMain.tip(USER_ERROR_TIP);
                }
                break;
            case COMPANY_USER_REQUEST:
                if (response != null){
                    if (response.state()){
                        companyUser = (CompanyUser) response.getData();
                        iMain.initUserInfo(companyUser);
                    }else {
                        iMain.tip(USER_ERROR_TIP);
                    }
                }else {
                    iMain.tip(USER_ERROR_TIP);
                }
                break;
        }
    }

    @Override
    protected void onIRequestFail(int requestId, Throwable throwable) {
        iMain.tip(USER_ERROR_TIP);
    }
    public void loadUserInfo(){
        switch (SpUtils.getUserType()){
            case UserUtils.PERSON_USER:
                personUserRequest = new PersonUserRequest(getContext(),new PersonUserRequest.PersonUserParam(SpUtils.getUserName(),SpUtils.getUserPassword()));
                personUserRequest.setRequestId(PERSON_USER_REQUEST);
                executeRequest(personUserRequest);
                break;
            case UserUtils.COMPANY_USER:
                companyUserRequest = new CompanyUserRequest(getContext(),new CompanyUserRequest.CompanyUserParam(SpUtils.getUserName(),SpUtils.getUserPassword()));
                companyUserRequest.setRequestId(COMPANY_USER_REQUEST);
                executeRequest(companyUserRequest);
                break;
        }
    }

    public void toUserInfoActivity(){
        switch (SpUtils.getUserType()){
            case UserUtils.PERSON_USER:
                UserInfoActivity.invoke(getContext(),personUser);
                break;
            case UserUtils.COMPANY_USER:
                UserInfoActivity.invoke(getContext(),companyUser);
                break;
        }
    }
}
