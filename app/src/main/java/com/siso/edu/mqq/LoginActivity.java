package com.siso.edu.mqq;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.User;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import utils.Constant;
import utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //控件
    private EditText mUsernameEditText, mPasswordEditText;
    private CheckBox mRememberPasswordCheckBox, mAutoLoginCheckBox, mShowPasswordCheckBox;
    private Button mLoginButton, mRegisterButton;


    //数据
    private String[] permissionList = new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.WAKE_LOCK,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private boolean password_state = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "36fac5a5bc89cb3ab75465a5f3d1edaa");
        setContentView(R.layout.activity_login);
        initView();//初始化UI视图控件

        //首先判断Bundle容器是否非空，如果非空就从中取值，并恢复数据
        if (savedInstanceState != null) {
            password_state = savedInstanceState.getBoolean(Constant.PASSWORD_STATE);
            if (password_state) {
                mPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//显示密码（visibility)
                mShowPasswordCheckBox.setChecked(password_state);
            } else {
                mPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//不显示密码(invisibility)
                mShowPasswordCheckBox.setChecked(password_state);
            }

        }

        initData();//初始化数据
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_rememberPwd:
                rememberPwd();
                break;
            case R.id.cb_autoLogin:
                autoLogin();
                break;
            case R.id.btn_login:
                login();//登录
                break;
            case R.id.btn_register:
                register();//注册
                break;
            case R.id.cb_showPassword:
                showPassword();//显示密码
                break;
            default:
                break;
        }
    }

    //记住密码
    private void rememberPwd() {
        if (!mRememberPasswordCheckBox.isChecked()) {
            mAutoLoginCheckBox.setChecked(false);
        }
    }

    //自动登录
    private void autoLogin() {
        if (mAutoLoginCheckBox.isChecked()) {
            mRememberPasswordCheckBox.setChecked(true);
        }
    }


    //此方法会在屏幕旋转时自动调用，用来保存一些必要数据，以致于在下次重新创建Activity实例的时候恢复一些必要数据
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(Constant.PASSWORD_STATE, password_state);//将是否显示密码的boolean值保存入Bundle容器
    }


    //初始化UI视图控件
    private void initView() {
        mUsernameEditText = (EditText) findViewById(R.id.et_username);
        mPasswordEditText = (EditText) findViewById(R.id.et_password);
        mRememberPasswordCheckBox = (CheckBox) findViewById(R.id.cb_rememberPwd);
        mAutoLoginCheckBox = (CheckBox) findViewById(R.id.cb_autoLogin);
        mShowPasswordCheckBox = (CheckBox) findViewById(R.id.cb_showPassword);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mRegisterButton = (Button) findViewById(R.id.btn_register);


        mRememberPasswordCheckBox.setOnClickListener(this);
        mAutoLoginCheckBox.setOnClickListener(this);
        mShowPasswordCheckBox.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);

    }


    private void initData() {

        for (int i=0;i<permissionList.length;i++){
            if (ContextCompat.checkSelfPermission(LoginActivity.this,permissionList[i])!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(LoginActivity.this,permissionList,i);
            }
        }



        Utils.initSP(LoginActivity.this);//初始化SP
        boolean rem_checked = Utils.sSharedPreferences.getBoolean("rem_checked", false);
        boolean auto_checked = Utils.sSharedPreferences.getBoolean("auto_checked", false);
        String username = Utils.sSharedPreferences.getString("username", "");
        String password = Utils.sSharedPreferences.getString("password", "");
        if (rem_checked) {
            //记住账号和密码
            mRememberPasswordCheckBox.setChecked(true);
            mUsernameEditText.setText(username);
            mPasswordEditText.setText(password);
        } else {
            //重置账号和密码
            mRememberPasswordCheckBox.setChecked(false);
            mUsernameEditText.setText("");
            mPasswordEditText.setText("");
        }

        if (auto_checked) {
            //自动登录
            mAutoLoginCheckBox.setChecked(true);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            //不自动登录
            mAutoLoginCheckBox.setChecked(false);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 0:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Utils.showToast(LoginActivity.this,"permission is ok");
                }else {
                    Utils.showToast(LoginActivity.this,"permission is denied");
                }
                break;
            case 1:
                if (grantResults.length>0 && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    Utils.showToast(LoginActivity.this,"permission is ok");
                }else {
                    Utils.showToast(LoginActivity.this,"permission is denied");
                }
                break;
            case 2:
                if (grantResults.length>0 && grantResults[2]==PackageManager.PERMISSION_GRANTED){
                    Utils.showToast(LoginActivity.this,"permission is ok");
                }else {
                    Utils.showToast(LoginActivity.this,"permission is denied");
                }
                break;
            case 3:
                if (grantResults.length>0 && grantResults[3]==PackageManager.PERMISSION_GRANTED){
                    Utils.showToast(LoginActivity.this,"permission is ok");
                }else {
                    Utils.showToast(LoginActivity.this,"permission is denied");
                }
                break;
            case 4:
                if (grantResults.length>0 && grantResults[4]==PackageManager.PERMISSION_GRANTED){
                    Utils.showToast(LoginActivity.this,"permission is ok");
                }else {
                    Utils.showToast(LoginActivity.this,"permission is denied");
                }
                break;
            case 5:
                if (grantResults.length>0 && grantResults[5]==PackageManager.PERMISSION_GRANTED){
                    Utils.showToast(LoginActivity.this,"permission is ok");
                }else {
                    Utils.showToast(LoginActivity.this,"permission is denied");
                }
                break;
                default:
        }
    }

    private void showPassword() {
        if (mShowPasswordCheckBox.isChecked()) {
            mPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            password_state = true;
        } else {
            mPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            password_state = false;
        }
    }

    //登录
    private void login() {
        queryData();//从User表中查询用户
    }

    //注册
    private void register() {
        String str_username = mUsernameEditText.getText().toString();
        String str_password = mPasswordEditText.getText().toString();
        if (str_username.isEmpty() || str_password.isEmpty()) {
            Utils.showToast(this, "输入不能为空");
        } else {
            //此时输入非空，向User表中注册新用户
            addData();
        }
    }


    //增加数据
    private void addData() {
        final String str_username = mUsernameEditText.getText().toString();
        final String str_password = mPasswordEditText.getText().toString();
        if (str_username.isEmpty() || str_password.isEmpty()) {
            Utils.showToast(this, "输入不能为空");
        } else {
            //此时输入非空，查询用户名是否存在
            BmobQuery query = new BmobQuery("User");
            query.addWhereEqualTo("username", str_username);
            query.setLimit(2);
            query.order("createdAt");
            query.findObjectsByTable(new QueryListener<JSONArray>() {
                @Override
                public void done(JSONArray jsonArray, BmobException e) {
                    if (e == null) {                           //此时查询数据成功

                        if (jsonArray.length() == 0) {          //此时用户不存在，可以注册

                            User user = new User();
                            user.setUsername(str_username);
                            user.setPassword(str_password);
                            user.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e == null) {
                                        Utils.showToast(LoginActivity.this, "注册成功，createdId=" + s);
                                    } else {
                                        Utils.showToast(LoginActivity.this, "注册失败，error=" + e.getMessage());
                                    }
                                }
                            });

                        } else {
                            Utils.showToast(LoginActivity.this, "用户名已存在");
                        }
                    } else {
                        Utils.showToast(LoginActivity.this, "查询数据失败，error:" + e.getMessage());
                    }
                }
            });
        }

    }


    //查询数据
    private void queryData() {
        final String str_username = mUsernameEditText.getText().toString();
        final String str_password = mPasswordEditText.getText().toString();
        if (str_username.isEmpty() || str_password.isEmpty()) {
            Utils.showToast(this, "输入不能为空");
        } else {
            //此时输入非空
            BmobQuery query = new BmobQuery("User");
            query.addWhereEqualTo("username", str_username);
            query.setLimit(2);
            query.order("createdAt");
            query.findObjectsByTable(new QueryListener<JSONArray>() {
                @Override
                public void done(JSONArray jsonArray, BmobException e) {
                    if (e == null) {
                        Log.e("query", "jsonArray= " + jsonArray.toString());
                        if (jsonArray.length() == 0) {
                            Utils.showToast(LoginActivity.this, "该用户不存在，请先注册");
                        } else {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String username = jsonObject.getString("username");
                                    String password = jsonObject.getString("password");
                                    Log.e("query", "username=" + username);
                                    Log.e("query", "password=" + password);
                                    boolean yes = username.equals(str_username) && password.equals(str_password);
                                    if (yes) {
                                        Utils.showToast(LoginActivity.this, "恭喜你，登录成功");

                                        //登录成功后，实现记住密码和自动登录
                                        SharedPreferences.Editor editor = Utils.sSharedPreferences.edit();
                                        if (mRememberPasswordCheckBox.isChecked()) {
                                            editor.putBoolean("rem_checked", true);
                                            editor.putString("username", str_username);
                                            editor.putString("password", str_password);
                                            editor.commit();
                                        } else {
                                            editor.putBoolean("rem_checked", false);
                                            editor.putString("username", "");
                                            editor.putString("password", "");
                                            editor.commit();
                                        }

                                        if (mAutoLoginCheckBox.isChecked()) {
                                            editor.putBoolean("auto_checked", true);
                                            editor.commit();
                                        } else {
                                            editor.putBoolean("auto_checked", false);
                                            editor.commit();
                                        }
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    } else {
                                        Utils.showToast(LoginActivity.this, "用户名或者密码错误");
                                    }
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }

                    } else {
                        Utils.showToast(LoginActivity.this, "查询数据失败" + e.getMessage());
                    }
                }
            });
        }

    }
}
